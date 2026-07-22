package com.example.scriptscout.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	private String tagName;
    private String keyword;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Tag(Long id, String tagName, String keyword) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.keyword = keyword;
	}
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

}
