package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import javax.persistence.*;

@Entity
@Table(name = "LIKE")
public class Like {
	@Id
	@Column
	private int id;
	@Column
	private User user;
	@Column
	@ManyToOne
	private Post post;
	
	public Like() {
		
	}
	
	public Like(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		return "Like [id=" + id + ", user=" + user + ", post=" + post + "]";
	}
}
