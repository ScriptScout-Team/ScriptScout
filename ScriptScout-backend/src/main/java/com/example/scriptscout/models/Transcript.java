package com.example.scriptscout.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transcript {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long videoId;
    private String transcriptText;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public String getTranscriptText() {
		return transcriptText;
	}
	public void setTranscriptText(String transcriptText) {
		this.transcriptText = transcriptText;
	}
	public Transcript(Long id, Long videoId, String transcriptText) {
		super();
		this.id = id;
		this.videoId = videoId;
		this.transcriptText = transcriptText;
	}
	public Transcript() {
		super();
	}
}
