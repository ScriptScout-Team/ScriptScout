package com.example.scriptscout.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
            System.err.println("Error calling Python AI service: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ParsedMetadata parseMetadata(String metadataStr) {
        ParsedMetadata parsed = new ParsedMetadata();
        if (metadataStr == null || metadataStr.trim().isEmpty()) {
            return parsed;
        }

        String trimmed = metadataStr.trim();
        if (trimmed.startsWith("{")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(trimmed);
                if (root.has("summary")) {
                    parsed.setSummary(root.get("summary").asText());
                }
                if (root.has("genre")) {
                    parsed.setGenre(root.get("genre").asText());
                } else if (root.has("category")) {
                    parsed.setGenre(root.get("category").asText());
                }
                if (root.has("tags")) {
                    JsonNode tagsNode = root.get("tags");
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
                if (root.has("language")) {
                    parsed.setLanguage(root.get("language").asText());
                }
                return parsed;
            } catch (Exception e) {
                System.err.println("JSON parse failed, falling back to line parsing: " + e.getMessage());
            }
        }

        // Plain text parsing (e.g. "Summary:\n<summary>\n\nGenre:\n<genre>\n\nTags:\n- tag1\n")
        try {
            String[] lines = metadataStr.split("\n");
            String currentKey = null;
            StringBuilder summaryBuilder = new StringBuilder();
            StringBuilder genreBuilder = new StringBuilder();
            List<String> tagsList = new ArrayList<>();
            StringBuilder languageBuilder = new StringBuilder();

            for (String line : lines) {
                String lineTrimmed = line.trim();
                String lineLower = lineTrimmed.toLowerCase();

                if (lineLower.startsWith("summary:")) {
                    currentKey = "summary";
                    String rest = lineTrimmed.substring(8).trim();
                    if (!rest.isEmpty()) {
                        summaryBuilder.append(rest);
                    }
                } else if (lineLower.startsWith("genre:")) {
                    currentKey = "genre";
                    String rest = lineTrimmed.substring(6).trim();
                    if (!rest.isEmpty()) {
                        genreBuilder.append(rest);
                    }
                } else if (lineLower.startsWith("category:")) {
                    currentKey = "genre";
                    String rest = lineTrimmed.substring(9).trim();
                    if (!rest.isEmpty()) {
                        genreBuilder.append(rest);
                    }
                } else if (lineLower.startsWith("tags:")) {
                    currentKey = "tags";
                    String rest = lineTrimmed.substring(5).trim();
                    if (!rest.isEmpty()) {
                        tagsList.add(rest);
                    }
                } else if (lineLower.startsWith("language:")) {
                    currentKey = "language";
                    String rest = lineTrimmed.substring(9).trim();
                    if (!rest.isEmpty()) {
                        languageBuilder.append(rest);
                    }
                } else if (lineTrimmed.startsWith("-") && "tags".equals(currentKey)) {
                    tagsList.add(lineTrimmed.substring(1).trim());
                } else if (!lineTrimmed.isEmpty()) {
                    // Append to current key's builder
                    if ("summary".equals(currentKey)) {
                        if (summaryBuilder.length() > 0) summaryBuilder.append(" ");
                        summaryBuilder.append(lineTrimmed);
                    } else if ("genre".equals(currentKey)) {
                        if (genreBuilder.length() > 0) genreBuilder.append(" ");
                        genreBuilder.append(lineTrimmed);
                    } else if ("language".equals(currentKey)) {
                        if (languageBuilder.length() > 0) languageBuilder.append(" ");
                        languageBuilder.append(lineTrimmed);
                    }
                }
            }

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
        } catch (Exception e) {
            System.err.println("Line parsing failed: " + e.getMessage());
        }

        return parsed;
    }
}
