package com.example.scriptscout.controller;

import com.example.scriptscout.models.MetadataEditor;
import com.example.scriptscout.models.Transcript;
import com.example.scriptscout.models.Video;
import com.example.scriptscout.repository.MetadataEditorRepository;
import com.example.scriptscout.repository.TranscriptRepository;
import com.example.scriptscout.service.PythonAiService;
import com.example.scriptscout.service.UploadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UploadControllerTest {

    @Mock
    private UploadService uploadService;

    @Mock
    private PythonAiService pythonAiService;

    @Mock
    private TranscriptRepository transcriptRepository;

    @Mock
    private MetadataEditorRepository metadataEditorRepository;

    @InjectMocks
    private UploadController uploadController;

    private MultipartFile mockFile;
    private File createdFile;

    @BeforeEach
    void setUp() {
        mockFile = mock(MultipartFile.class);
    }

    @AfterEach
    void tearDown() {
        // Clean up any test file actually created on disk (if mock wasn't strict or transferTo occurred)
        if (createdFile != null && createdFile.exists()) {
            createdFile.delete();
        }
    }

    @Test
    void testUploadVideo_FullAiResponse() throws IOException {
        String fileName = "test-video.mp4";
        when(mockFile.getOriginalFilename()).thenReturn(fileName);

        Video initialVideo = new Video(1L, "Title", "Desc", "Category", fileName);
        when(uploadService.uploadVideo(any(Video.class))).thenReturn(initialVideo);

        PythonAiService.AiResponse aiResponse = new PythonAiService.AiResponse();
        aiResponse.setTranscript("Extracted transcript content");
        aiResponse.setMetadata("Raw AI metadata");
        when(pythonAiService.uploadAndAnalyze(any(File.class))).thenReturn(aiResponse);

        PythonAiService.ParsedMetadata parsedMetadata = new PythonAiService.ParsedMetadata();
        parsedMetadata.setSummary("Parsed summary");
        parsedMetadata.setGenre("Sci-Fi");
        parsedMetadata.setLanguage("German");
        parsedMetadata.setTags("space, tags");
        when(pythonAiService.parseMetadata("Raw AI metadata")).thenReturn(parsedMetadata);

        Video result = uploadController.uploadVideo(mockFile, "Title", "Desc", "Category");

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(mockFile).transferTo(any(File.class));
        verify(transcriptRepository).save(any(Transcript.class));
        verify(metadataEditorRepository).save(any(MetadataEditor.class));
        verify(uploadService, times(2)).uploadVideo(any(Video.class));
    }

    @Test
    void testUploadVideo_NullAiResponse() throws IOException {
        String fileName = "test-video.mp4";
        when(mockFile.getOriginalFilename()).thenReturn(fileName);

        Video initialVideo = new Video(1L, "Title", "Desc", "Category", fileName);
        when(uploadService.uploadVideo(any(Video.class))).thenReturn(initialVideo);

        when(pythonAiService.uploadAndAnalyze(any(File.class))).thenReturn(null);

        Video result = uploadController.uploadVideo(mockFile, "Title", "Desc", "Category");

        assertNotNull(result);
        assertEquals(initialVideo, result);
        verify(mockFile).transferTo(any(File.class));
        verify(transcriptRepository, never()).save(any(Transcript.class));
        verify(metadataEditorRepository, never()).save(any(MetadataEditor.class));
        verify(uploadService, times(1)).uploadVideo(any(Video.class));
    }

    @Test
    void testUploadVideo_PartialAiResponse_NoTranscriptNoMetadata() throws IOException {
        String fileName = "test-video.mp4";
        when(mockFile.getOriginalFilename()).thenReturn(fileName);

        Video initialVideo = new Video(1L, "Title", "Desc", "Category", fileName);
        when(uploadService.uploadVideo(any(Video.class))).thenReturn(initialVideo);

        PythonAiService.AiResponse aiResponse = new PythonAiService.AiResponse();
        // both transcript and metadata are null
        when(pythonAiService.uploadAndAnalyze(any(File.class))).thenReturn(aiResponse);

        Video result = uploadController.uploadVideo(mockFile, "Title", "Desc", "Category");

        assertNotNull(result);
        assertEquals(initialVideo, result);
        verify(transcriptRepository, never()).save(any(Transcript.class));
        verify(metadataEditorRepository, never()).save(any(MetadataEditor.class));
    }

    @Test
    void testUploadVideo_MetadataWithNullSummaryAndGenre() throws IOException {
        String fileName = "test-video.mp4";
        when(mockFile.getOriginalFilename()).thenReturn(fileName);

        Video initialVideo = new Video(1L, "Title", "Desc", "Category", fileName);
        when(uploadService.uploadVideo(any(Video.class))).thenReturn(initialVideo);

        PythonAiService.AiResponse aiResponse = new PythonAiService.AiResponse();
        aiResponse.setMetadata("Raw AI metadata");
        when(pythonAiService.uploadAndAnalyze(any(File.class))).thenReturn(aiResponse);

        PythonAiService.ParsedMetadata parsedMetadata = new PythonAiService.ParsedMetadata();
        parsedMetadata.setSummary(null);
        parsedMetadata.setGenre(null);
        parsedMetadata.setLanguage("English");
        parsedMetadata.setTags("cool");
        when(pythonAiService.parseMetadata("Raw AI metadata")).thenReturn(parsedMetadata);

        Video result = uploadController.uploadVideo(mockFile, "Title", "Desc", "Category");

        assertNotNull(result);
        verify(metadataEditorRepository).save(any(MetadataEditor.class));
        verify(uploadService, times(2)).uploadVideo(any(Video.class));
    }

    @Test
    void testUploadVideo_ThrowsIOException() throws IOException {
        when(mockFile.getOriginalFilename()).thenReturn("error-video.mp4");
        doThrow(new IOException("Disk full")).when(mockFile).transferTo(any(File.class));

        assertThrows(IOException.class, () -> {
            uploadController.uploadVideo(mockFile, "Title", "Desc", "Category");
        });

        verify(uploadService, never()).uploadVideo(any(Video.class));
    }
}
