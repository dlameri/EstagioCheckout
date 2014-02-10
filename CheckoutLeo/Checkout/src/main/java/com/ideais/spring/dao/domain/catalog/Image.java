package com.ideais.spring.dao.domain.catalog;

public class Image {
	private Long id;
	private String path;
	private String alt;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getAlt() {
		return alt;
	}
	
	public void setAlt(String alt) {
		this.alt = alt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}