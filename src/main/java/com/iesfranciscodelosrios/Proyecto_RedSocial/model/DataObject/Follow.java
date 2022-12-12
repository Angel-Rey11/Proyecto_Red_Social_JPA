package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import javax.persistence.*;


public class Follow {
	
	
	protected int id;
	protected User follower;
	protected User following;

	public Follow() {
		super();
	}
	
	public Follow(int id, User follower, User following) {
		this.id = id;
		this.follower = follower;
		this.following = following;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}

	@Override
	public String toString() {
		return "Follow [id=" + id + ", follower=" + follower + ", following=" + following + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Follow other = (Follow) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
