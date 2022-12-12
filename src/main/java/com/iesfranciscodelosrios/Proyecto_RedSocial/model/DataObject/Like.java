package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "LIKE")
public class Like implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
	private Post post;
	@EmbeddedId
	private LikeId idL;
	
	
	public Like() {
		this(-1,null,null);
	}
	
	public Like(int id) {
		super();
		this.id = id;
	}
	
	public Like(int id, User user, Post post) {
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
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", user=" + user + ", post=" + post + "]";
	}
}
