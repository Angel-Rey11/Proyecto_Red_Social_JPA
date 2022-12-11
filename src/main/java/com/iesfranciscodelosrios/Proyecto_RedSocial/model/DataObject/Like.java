package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import javax.persistence.*;

@Entity
@Table(name = "LIKE")
public class Like {
	
	@EmbeddedId
	private LikeId idL;
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
	private Post post;
	@Column(name = "id")
	private int id;
	
	public Like() {
		
	}
	
	public Like(LikeId idL, User user, Post post,int id) {
		super();
		this.id = id;
		this.idL = new LikeId(user.getId(),post.getId());
		this.user = user;
		this.post = post;
	}

	public LikeId getIdL() {
		return idL;
	}

	public void setIdL(LikeId idL) {
		this.idL = idL;
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
