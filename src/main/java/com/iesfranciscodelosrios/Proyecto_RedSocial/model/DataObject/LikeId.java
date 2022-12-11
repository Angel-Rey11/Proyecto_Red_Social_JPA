package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LikeId implements Serializable{
	
	@Column(name = "id_user")
	private int userId;
	
	@Column(name = "id_post")
	private int postId;

	public LikeId(int userId, int postId) {
		super();
		this.userId = userId;
		this.postId = postId;
	}

	public LikeId() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeId other = (LikeId) obj;
		return postId == other.postId && userId == other.userId;
	}
	
	
}