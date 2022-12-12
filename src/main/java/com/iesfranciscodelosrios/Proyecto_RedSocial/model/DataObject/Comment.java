package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENT")
public class Comment {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "text")
	private String text;
	@Column(name = "date")
	private Timestamp date;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("postId")
	private Post post;
	@EmbeddedId
	private CommentId idC;
	
	public Comment() {
		
	}
	
	public Comment(int id, String text, Timestamp date, User user, Post post) {
		super();
		this.id = id;
		this.text = text;
		this.date = date;
		this.user = user;
		this.post = post;
		this.idC = new CommentId(user.getId(),post.getId());
	}
	

	public CommentId getIdC() {
		return idC;
	}

	public void setIdC(CommentId idC) {
		this.idC = idC;
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
