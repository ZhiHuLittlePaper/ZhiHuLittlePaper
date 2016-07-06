package io.github.zhihulittlepaper.entity;

import java.io.Serializable;

public class News implements Serializable {
	private String id;
	private String title;
	private String image;
	private String date;
	private String body;
	
	public News() {
	}
	public News(String id, String title, String image, String date) {
		this.id = id;
		this.title = title;
		this.image = image;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
