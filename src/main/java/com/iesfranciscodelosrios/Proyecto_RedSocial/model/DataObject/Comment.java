package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Comment {
	private int id;
	private String text;
	private Timestamp date;
	private User user;
	private Post post;
	
	public Comment() {
		
	}
	
	public Comment(int id, String text, Timestamp date, User user, Post post) {
		super();
		this.id = id;
		this.text = text;
		this.date = date;
		this.user = user;
		this.post = post;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", date=" + date + ", user=" + user + ", post=" + post + "]";
	}
}
