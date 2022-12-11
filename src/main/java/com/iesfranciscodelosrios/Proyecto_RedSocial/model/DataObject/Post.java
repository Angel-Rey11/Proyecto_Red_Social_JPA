package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "POST")
public class Post {
	
	@Id
	@Column(name = "id")
	protected int id;
	@Column(name = "creation_date")
	protected Timestamp creationDate;
	@Column(name = "text")
	protected String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	protected User user;
	@OneToMany(
	mappedBy = "post",
	cascade = CascadeType.ALL,
	orphanRemoval = true
	)
	protected List<Like> userLikes;
	@OneToMany(
	mappedBy = "post",
	cascade = CascadeType.ALL,
	orphanRemoval = true
	)
	protected List<Comment> userComments;
	
	public Post() {
		super();
	}

	public Post(int id, Timestamp creationDate, String text, User user) {
		this.id = id;
		this.creationDate = creationDate;
		this.text = text;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(UserDAO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", creationDate=" + creationDate + ", modificationDate=" 
				+ ", text=" + text + ", user=" + user + "]";
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
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
