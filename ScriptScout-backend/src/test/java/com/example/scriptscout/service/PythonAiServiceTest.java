package com.example.scriptscout.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PythonAiServiceTest {

    private final PythonAiService pythonAiService = new PythonAiService();

    @Test
    public void testParseMetadataJson() {
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
    public void testParseMetadataYaml() {
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
}
