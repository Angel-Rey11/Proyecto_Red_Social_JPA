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
	
	public List<User> getFollowers() {
		return followers;
	}
	
	public List<Comment> getPostComments() {
		return postComments;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	
	public void setPostComments(List<Comment> postComments) {
		this.postComments = postComments;
	}
	
	public void setFollowing(List<User> following) {
		this.following = following;
	}
	/**
	 * Metodo para añadir el usuario que seguimos a su lista de seguidores
	 * @param u usuario logeado
	 * @return true si todo lo ha hecho bien
	 */
	public boolean addFollowers(User u) {
		boolean result = false;
		if(this.followers == null) {
			this.followers = new ArrayList<>();
		} 
		
		if(!this.followers.contains(u))
			this.followers.add(u);
		result = true;
		
		return result;
	}
	
	/**
	 * Metodo para eliminar el usuario logeado de la lista de seguidos
	 * @param u usuario logeado
	 */
	public void removeFollowers(User u) {
		this.followers.remove(u);
	}
	
	public List<User> getFollowing() {
		return following;
	}
	
	/**
	 * Metodo para añadir a la lista de siguiendo del usuario logeado
	 * @param u usuario que vamos a seguir
	 * @return true si todo va bien
	 */
	public boolean addFollowing(User u) {
		boolean result = false;
		if(this.following == null) {
			this.following = new ArrayList<>();
		} 
		
		if(!this.following.contains(u)) {
			u.addFollowers(this);
			this.following.add(u);
		}
		result = true;
		
		return result;
	}
	
	/**
	 * Metodo para eliminar el usuario de la lista de siguiendo del usuario logeado
	 * @param u usuario que eliminamos
	 * @return true si todo va bien
	 */
	public boolean removeFollowing(User u){
		boolean result = false;
		if(this.following != null) {
			if(this.following.contains(u)) {
				this.following.remove(u);
				u.removeFollowers(this);
			}
			result = true;
		} 	
		return result;
	}
	
	public List<Post> getPostsLikes() {
		return postsLikes;
	}
	
	public void setPostsLikes(List<Post> postsLikes) {
		this.postsLikes = postsLikes;
	}
	
	/**
	 * Metodo para añadir likes al array de likes del usuario
	 * @param p que queremos introducir
	 * @return true si todo esta correcto
	 */
	public boolean addPostLikes(Post p) {
		boolean result = false;
		if(this.postsLikes == null) {
			this.postsLikes = new ArrayList<>();
		} 
		
		if(!this.postsLikes.contains(p)) {
			this.postsLikes.add(p);
			p.addLikes(this);
		}
		result = true;
		
		return result;
	}
	
	/**
	 * Metodo para eliminar likes al array de likes del usuario
	 * @param p que queremos eliminar
	 * @return true si ha podido eliminarlo
	 */
	public boolean removePostLikes(Post p) {
		boolean result = false;
		if(this.postsLikes != null) {
			if(this.postsLikes.contains(p)) {
				this.postsLikes.remove(p);
				p.removeLikes(this);
				result = true;
			}
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
