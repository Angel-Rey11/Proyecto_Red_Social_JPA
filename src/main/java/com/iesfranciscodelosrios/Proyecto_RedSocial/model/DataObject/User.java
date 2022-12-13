package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(mappedBy = "userLikes")
    private List<Post> postsLikes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> postComments;

    public User(int id, String nickname, String name, String password, String biografia) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
        this.biografia = biografia;
    }
    public User(){
    	this(-1,"","","","");
    }

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
		if (posts == null) return;
		for (Post p: posts) {
			this.addPosts(p);
		};
	}
	
	public boolean addPosts(Post p) {
		boolean result = false;
		if(this.posts == null) {
			this.posts = new ArrayList<>();
			this.posts.add(p);
			result = true;
		} else {
			this.posts.add(p);
			result = true;
		}
		return result;
	}
	
	public List<User> getFollowers() {
		return followers;
	}
	
	public void setFollowers(List<User> followers) {
		if (followers == null) return;
		for (User u: followers) {
			this.addFollowers(u);
		};
	}
	
	public boolean addFollowers(User u) {
		boolean result = false;
		if(this.followers == null) {
			this.followers = new ArrayList<>();
			this.followers.add(u);
			result = true;
		} else {
			this.followers.add(u);
			result = true;
		}
		return result;
	}
	
	public List<User> getFollowing() {
		return following;
	}
	
	public void setFollowing(List<User> following) {
		if (following == null) return;
		for (User u: following) {
			this.addFollowing(u);
		};
	}
	
	public boolean addFollowing(User u) {
		boolean result = false;
		if(this.following == null) {
			this.following = new ArrayList<>();
			this.following.add(u);
			result = true;
		} else {
			this.following.add(u);
			result = true;
		}
		return result;
	}
	
	public List<Post> getPostsLikes() {
		return postsLikes;
	}
	
	public void setPostsLikes(List<Post> postsLikes) {
		if (postsLikes == null) return;
		for (Post l: postsLikes) {
			this.addPostLikes(l);
		};
	}
	
	public boolean addPostLikes(Post l) {
		boolean result = false;
		if(this.postsLikes == null) {
			this.postsLikes = new ArrayList<>();
			this.postsLikes.add(l);
			result = true;
		} else {
			this.postsLikes.add(l);
			result = true;
		}
		return result;
	}
	
	public List<Comment> getPostComments() {
		return postComments;
	}
	
	public void setPostComments(List<Comment> postComments) {
		if (postComments == null) return;
		for (Comment c: postComments) {
			this.addPostsComments(c);
		};
	}
	
	public boolean addPostsComments(Comment c) {
		boolean result = false;
		if(this.postComments == null) {
			this.postComments = new ArrayList<>();
			this.postComments.add(c);
			result = true;
		} else {
			this.postComments.add(c);
			result = true;
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nickname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(nickname, other.nickname);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", password=" + password
				+ ", biografia=" + biografia +"]";
	}
}
