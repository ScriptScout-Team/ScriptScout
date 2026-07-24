package com.example.scriptscout.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PythonAiService {

    private static final Logger logger = LoggerFactory.getLogger(PythonAiService.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String SUMMARY = "summary";
    private static final String GENRE = "genre";
    private static final String LANGUAGE = "language";

    private final RestTemplate restTemplate = new RestTemplate();
    private final String pythonServiceUrl = "https://1e1a90661b718c.lhr.life/upload";

    public static class AiResponse {
        private String transcript;
        private String metadata;

        public String getTranscript() {
            return transcript;
        }

        public void setTranscript(String transcript) {
            this.transcript = transcript;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }
    }

    public static class ParsedMetadata {
        private String summary;
        private String genre;
        private String tags;
        private String language = "English";

        public String getSummary() { return summary; }
        public void setSummary(String summary) { this.summary = summary; }

        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }

        public String getTags() { return tags; }
        public void setTags(String tags) { this.tags = tags; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
    }

    public AiResponse uploadAndAnalyze(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            return restTemplate.postForObject(pythonServiceUrl, requestEntity, AiResponse.class);
        } catch (Exception e) {
            logger.error("Error calling Python AI service: {}", e.getMessage(), e);
            return null;
        }
    }

    public ParsedMetadata parseMetadata(String metadataStr) {
        ParsedMetadata parsed = new ParsedMetadata();
        if (metadataStr == null || metadataStr.trim().isEmpty()) {
            return parsed;
        }

        String trimmed = metadataStr.trim();
        if (trimmed.startsWith("{") && tryParseJsonMetadata(trimmed, parsed)) {
            return parsed;
        }

        parseLineMetadata(metadataStr, parsed);
        return parsed;
    }

    private boolean tryParseJsonMetadata(String jsonStr, ParsedMetadata parsed) {
        try {
            JsonNode root = MAPPER.readTree(jsonStr);
            extractJsonFields(root, parsed);
            return true;
        } catch (Exception e) {
            logger.error("JSON parse failed, falling back to line parsing: {}", e.getMessage());
            return false;
        }
    }

    private void extractJsonFields(JsonNode root, ParsedMetadata parsed) {
        if (root.has(SUMMARY)) {
            parsed.setSummary(root.get(SUMMARY).asText());
        }
        if (root.has(GENRE)) {
            parsed.setGenre(root.get(GENRE).asText());
        } else if (root.has("category")) {
            parsed.setGenre(root.get("category").asText());
        }
        if (root.has("tags")) {
            extractJsonTags(root.get("tags"), parsed);
        }
        if (root.has(LANGUAGE)) {
            parsed.setLanguage(root.get(LANGUAGE).asText());
        }
    }

    private void extractJsonTags(JsonNode tagsNode, ParsedMetadata parsed) {
        if (tagsNode.isArray()) {
            List<String> tagsList = new ArrayList<>();
            for (JsonNode t : tagsNode) {
                tagsList.add(t.asText());
            }
            parsed.setTags(String.join(", ", tagsList));
        } else {
            parsed.setTags(tagsNode.asText());
        }
    }

    private void parseLineMetadata(String metadataStr, ParsedMetadata parsed) {
        try {
            LineParseContext context = new LineParseContext();
            String[] lines = metadataStr.split("\n");
            for (String line : lines) {
                processLine(line, context);
            }
            context.applyTo(parsed);
        } catch (Exception e) {
            logger.error("Line parsing failed: {}", e.getMessage());
        }
    }

    private void processLine(String line, LineParseContext context) {
        String lineTrimmed = line.trim();
        String lineLower = lineTrimmed.toLowerCase();

        if (checkKeyPrefixes(lineTrimmed, lineLower, context)) {
            return;
        }

        if (lineTrimmed.startsWith("-") && "tags".equals(context.currentKey)) {
            context.tagsList.add(lineTrimmed.substring(1).trim());
        } else if (!lineTrimmed.isEmpty()) {
            appendToCurrentKeyBuilder(lineTrimmed, context);
        }
    }

    private boolean checkKeyPrefixes(String lineTrimmed, String lineLower, LineParseContext context) {
        if (lineLower.startsWith("summary:")) {
            context.currentKey = SUMMARY;
            appendRestIfNotEmpty(lineTrimmed.substring(8), context.summaryBuilder);
            return true;
        }
        if (lineLower.startsWith("genre:")) {
            context.currentKey = GENRE;
            appendRestIfNotEmpty(lineTrimmed.substring(6), context.genreBuilder);
            return true;
        }
        if (lineLower.startsWith("category:")) {
            context.currentKey = GENRE;
            appendRestIfNotEmpty(lineTrimmed.substring(9), context.genreBuilder);
            return true;
        }
        if (lineLower.startsWith("tags:")) {
            context.currentKey = "tags";
            appendRestIfNotEmptyToList(lineTrimmed.substring(5), context.tagsList);
            return true;
        }
        if (lineLower.startsWith("language:")) {
            context.currentKey = LANGUAGE;
            appendRestIfNotEmpty(lineTrimmed.substring(9), context.languageBuilder);
            return true;
        }
        return false;
    }

    private void appendRestIfNotEmpty(String rest, StringBuilder sb) {
        String trimmedRest = rest.trim();
        if (!trimmedRest.isEmpty()) {
            sb.append(trimmedRest);
        }
    }

    private void appendRestIfNotEmptyToList(String rest, List<String> list) {
        String trimmedRest = rest.trim();
        if (!trimmedRest.isEmpty()) {
            list.add(trimmedRest);
        }
    }

    private void appendToCurrentKeyBuilder(String lineTrimmed, LineParseContext context) {
        if (SUMMARY.equals(context.currentKey)) {
            appendWithSpace(context.summaryBuilder, lineTrimmed);
        } else if (GENRE.equals(context.currentKey)) {
            appendWithSpace(context.genreBuilder, lineTrimmed);
        } else if (LANGUAGE.equals(context.currentKey)) {
            appendWithSpace(context.languageBuilder, lineTrimmed);
        }
    }

    private void appendWithSpace(StringBuilder sb, String text) {
        if (sb.length() > 0) {
            sb.append(" ");
        }
        sb.append(text);
    }

    private static class LineParseContext {
        private String currentKey;
        private final StringBuilder summaryBuilder = new StringBuilder();
        private final StringBuilder genreBuilder = new StringBuilder();
        private final List<String> tagsList = new ArrayList<>();
        private final StringBuilder languageBuilder = new StringBuilder();

        public void applyTo(ParsedMetadata parsed) {
            if (summaryBuilder.length() > 0) {
                parsed.setSummary(summaryBuilder.toString());
            }
            if (genreBuilder.length() > 0) {
                parsed.setGenre(genreBuilder.toString());
            }
            if (!tagsList.isEmpty()) {
                parsed.setTags(String.join(", ", tagsList));
            }
            if (languageBuilder.length() > 0) {
                parsed.setLanguage(languageBuilder.toString());
            }
        }
    }
}
