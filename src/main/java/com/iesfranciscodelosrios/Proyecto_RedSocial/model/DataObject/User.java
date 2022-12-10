package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;


import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

import java.sql.Connection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@Column
    private int id;
	@Column
    private String name;
	@Column
    private String nickname;
	@Column
    private String password;
	@Column
    private String biografia;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
    @ManyToMany
    @JoinTable(name = "FOLLOW", joinColumns = @JoinColumn(name = "user_id_follower"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> followers;
    private List<User> following;

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
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", password=" + password
				+ ", biografia=" + biografia +"]";
	}
}
