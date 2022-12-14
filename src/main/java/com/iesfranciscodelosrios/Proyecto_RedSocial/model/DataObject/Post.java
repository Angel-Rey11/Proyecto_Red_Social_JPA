package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "POST")
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;
	@Column(name = "creation_date")
	protected Timestamp creationDate;
	@Column(name = "text")
	protected String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	protected User user;
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "likes",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
	protected List<User> userLikes;
	@OneToMany(
	mappedBy = "post",
	cascade = CascadeType.ALL,
	orphanRemoval = true
	)
	protected List<Comment> comments;
	
	public Post() {
		this(-1,null,"",null);
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

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserLikes() {
		return userLikes;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setUserLikes(List<User> userLikes) {
		this.userLikes = userLikes;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Metodo para añadir likes al array de user en post
	 * @param u el usuario que ha dado like
	 * @return true si todo ha ido bien
	 */
	public boolean addLikes (User u) {
		boolean result = false;
		if(this.userLikes == null) {
			this.userLikes = new ArrayList<>();
		} 
		
		if(!this.userLikes.contains(u)) {
			this.userLikes.add(u);
			u.addPostLikes(this);
		}
		result = true;
		
		return result;
	}
	
	/**
	 * Metodo para eliminar likes al array de user en post
	 * @param u el usuario que ha dado like
	 * @return true si todo ha ido bien
	 */
	public boolean removeLikes (User u) {
		boolean result = false;
		if(this.userLikes != null) {
			if(this.userLikes.contains(u)) {
				this.userLikes.remove(u);
				u.removePostLikes(this);
			}
			result = true;
		} 	
		return result;
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
