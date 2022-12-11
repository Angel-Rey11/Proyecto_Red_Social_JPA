package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;


import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@Column(name = "id")
    private int id;
	@Column(name = "name")
    private String name;
	@Column(name = "nickname")
    private String nickname;
	@Column(name = "password")
    private String password;
	@Column(name = "biografia")
    private String biografia;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
    @JoinTable(name = "follow", joinColumns = {
    @JoinColumn(name = "id_user_follower", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
    @JoinColumn(name = "id_user_following", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private List<User> followers;
    @ManyToMany(mappedBy = "followers")
    private List<User> following;
    @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true
    )
    private List<Like> postsLikes;
    @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true
    )
    private List<Comment> postComments;

    public User(int id, String nickname, String name, String password, String biografia) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
        this.biografia = biografia;
    }
    public User(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getBiografia() {
        return biografia;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
    
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public List<User> getFollowers() {
		return followers;
	}
	
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	
	public List<User> getFollowing() {
		return following;
	}
	
	public void setFollowing(List<User> following) {
		this.following = following;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", password=" + password
				+ ", biografia=" + biografia +"]";
	}
}
