package com.example.scriptscout.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PythonAiServiceTest {

    private final PythonAiService pythonAiService = new PythonAiService();

    @Test
    void testParseMetadataJson() {
        String jsonMetadata = "{\n" +
                "  \"summary\": \"A short recording of counting the numbers one, two, and three.\",\n" +
                "  \"genre\": \"Educational\",\n" +
                "  \"tags\": [\"counting\", \"numbers\", \"basic math\"],\n" +
                "  \"language\": \"English\"\n" +
                "}";

        PythonAiService.ParsedMetadata parsed = pythonAiService.parseMetadata(jsonMetadata);

        assertEquals("A short recording of counting the numbers one, two, and three.", parsed.getSummary());
        assertEquals("Educational", parsed.getGenre());
        assertEquals("counting, numbers, basic math", parsed.getTags());
        assertEquals("English", parsed.getLanguage());
    }

    @Test
    void testParseMetadataYaml() {
        String yamlMetadata = "Summary:\n" +
                "A video overview of the project.\n" +
                "\n" +
                "Genre:\n" +
                "Technology\n" +
                "\n" +
                "Tags:\n" +
                "- tech\n" +
                "- tutorial\n" +
                "\n" +
                "Language:\n" +
                "English";

        PythonAiService.ParsedMetadata parsed = pythonAiService.parseMetadata(yamlMetadata);

        assertEquals("A video overview of the project.", parsed.getSummary());
        assertEquals("Technology", parsed.getGenre());
        assertEquals("tech, tutorial", parsed.getTags());
        assertEquals("English", parsed.getLanguage());
    }

    @Test
    void testParseMetadataNullOrEmpty() {
        PythonAiService.ParsedMetadata parsedNull = pythonAiService.parseMetadata(null);
        assertNotNull(parsedNull);
        assertNull(parsedNull.getSummary());
        assertEquals("English", parsedNull.getLanguage());

        PythonAiService.ParsedMetadata parsedEmpty = pythonAiService.parseMetadata("   ");
        assertNotNull(parsedEmpty);
        assertNull(parsedEmpty.getSummary());
    }

    @Test
    void testParseMetadataJsonCategoryFallbackAndNonArrayTags() {
        String jsonMetadata = "{\n" +
                "  \"summary\": \"Fallback test.\",\n" +
                "  \"category\": \"Sci-Fi\",\n" +
                "  \"tags\": \"space, aliens\",\n" +
                "  \"language\": \"Spanish\"\n" +
                "}";

        PythonAiService.ParsedMetadata parsed = pythonAiService.parseMetadata(jsonMetadata);

        assertEquals("Fallback test.", parsed.getSummary());
        assertEquals("Sci-Fi", parsed.getGenre());
        assertEquals("space, aliens", parsed.getTags());
        assertEquals("Spanish", parsed.getLanguage());
    }

    @Test
    void testParseMetadataMalformedJsonFallbackToLineParse() {
        // Starts with '{' but is malformed JSON, so should fall back to line parsing
        String malformedJson = "{\n" +
                "  summary: Line parsed summary\n" +
                "  category: Line parsed genre\n" +
                "  tags:\n" +
                "  - lineTag1\n" +
                "  - lineTag2\n" +
                "}";

        PythonAiService.ParsedMetadata parsed = pythonAiService.parseMetadata(malformedJson);

        assertEquals("Line parsed summary", parsed.getSummary());
        assertEquals("Line parsed genre", parsed.getGenre());
        assertEquals("lineTag1, lineTag2", parsed.getTags());
    }

    @Test
    void testLineParsingDetailedPaths() {
        String lineMetadata = "random line here\n" +
                "category:Action-Sci-Fi\n" +
                "tags: dramatic, high-energy\n" +
                "some other value\n" +
                "language: French";
        PythonAiService.ParsedMetadata parsed = pythonAiService.parseMetadata(lineMetadata);
        assertEquals("Action-Sci-Fi", parsed.getGenre());
        assertEquals("dramatic, high-energy", parsed.getTags());
        assertEquals("French", parsed.getLanguage());
    }

    @Test
    void testUploadAndAnalyzeSuccess() {
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        PythonAiService serviceWithMock = new PythonAiService(mockRestTemplate);

        PythonAiService.AiResponse expectedResponse = new PythonAiService.AiResponse();
        expectedResponse.setTranscript("Test transcript");
        expectedResponse.setMetadata("Test metadata");

        when(mockRestTemplate.postForObject(anyString(), any(HttpEntity.class), eq(PythonAiService.AiResponse.class)))
                .thenReturn(expectedResponse);

        File dummyFile = new File("dummy.txt");
        PythonAiService.AiResponse actualResponse = serviceWithMock.uploadAndAnalyze(dummyFile);

        assertNotNull(actualResponse);
        assertEquals("Test transcript", actualResponse.getTranscript());
        assertEquals("Test metadata", actualResponse.getMetadata());
    }

    @Test
    void testUploadAndAnalyzeException() {
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        PythonAiService serviceWithMock = new PythonAiService(mockRestTemplate);

        when(mockRestTemplate.postForObject(anyString(), any(HttpEntity.class), eq(PythonAiService.AiResponse.class)))
                .thenThrow(new RestClientException("Connection timeout"));

        File dummyFile = new File("dummy.txt");
        PythonAiService.AiResponse actualResponse = serviceWithMock.uploadAndAnalyze(dummyFile);

        assertNull(actualResponse);
    }
}

