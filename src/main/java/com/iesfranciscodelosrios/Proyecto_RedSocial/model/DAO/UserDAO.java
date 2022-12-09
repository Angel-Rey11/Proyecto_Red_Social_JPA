package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IPostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IUserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDAO extends User{

    private final static String INSERT = "INSERT INTO `user` (`id`, `nickname`, `name`, `password`, `biografia`) VALUES (NULL,?,?,?,'')";
    private final static String DELETE = "DELETE FROM User WHERE id = ?";
    private final static String UPDATE = "UPDATE User SET nickname = ?,  name = ?, password = ?, biografia = ? WHERE id = ?";
    private final static String GETALLFOLLOWER = "SELECT * FROM User WHERE id IN (SELECT id_user_follower FROM Follow WHERE id_user_following = ?)";
    private final static String GETALLFOLLOWING = "SELECT * FROM User WHERE id IN (SELECT id_user_following FROM Follow WHERE id_user_follower = ?)";
    private final static String FIND = "SELECT id, nickname, name, password, biografia FROM user WHERE id = ?";
    private final static String FINDBYNICKNAME = "SELECT id, nickname, name, password, biografia FROM user WHERE nickname = ?";
    private final static String LOGIN = "SELECT * FROM user WHERE nickname = ? AND password = ?";
    private final static String MODIFYBIO = "UPDATE `user` SET `biografia` = ? WHERE `user`.`id` = ?";
    private final static String RANDOMUSER = "SELECT * FROM `user` WHERE id NOT IN (?) ORDER BY RAND()*(25-10)+10 LIMIT 6";
    private static EntityManager manager;
	private static EntityManagerFactory emf;
    
    public UserDAO(){}
    public UserDAO(int id, String name, String nickname, String password, String biografia) {
        super(id, name, nickname, password, biografia);
    }
    public UserDAO(User u){
        this(u.getId(), u.getNickname(), u.getName(), u.getPassword(), u.getBiografia());
    }
    public UserDAO(int id) {
        this.find(id);
    }


    /**
     * Inserta un usuario en la base de datos
     * @return true si se ha insertado correctamente
     */
    public boolean insert() {
    	emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(this);
		manager.getTransaction().commit();
		manager.close();
    }

    /**
     * Elimina un usuario de la base de datos
     * @return true si se ha eliminado correctamente
     */
    public boolean delete() {
    	emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(this);
		manager.getTransaction().commit();
		manager.close();
    }

    /**
     * Actualiza un usuario de la base de datos
     * @return true si se ha actualizado correctamente
     */
    public boolean update() {
    	emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.getTransaction().commit();
		manager.close();
    }

    /**
     * Si los parametros coinciden con algun usuario de la base de datos permite entrar al programa
     * @param nickname nombre de usuario
     * @param password contraseña
     * @return true si se ha logueado correctamente
     */
    public boolean login(String nickname, String password) {
        
    }

    /**
     * Se trae todos los seguidores de un usuario
     * @return La lista con todos los seguidores
     */
    public List<UserDAO> getAllFollower() {
    	emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
		List<UserDAO> misUsuarios = manager.createQuery(GETALLFOLLOWER).getResultList();
		manager.close();
		return misUsuarios;
    }

    /**
     * Se trae todos los usuarios que sigue un usuario
     * @return La lista con todos los usuarios que sigue
     */
    public List<UserDAO> getAllFollowing() {
    	emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
		List<UserDAO> misUsuarios = manager.createQuery(GETALLFOLLOWING).getResultList();
		manager.close();
		return misUsuarios;
    }
    /**
     * Busca a un usuario en la base de datos por su id
     * @param id id del usuario
     * @return El usuario encontrado
     */
    public UserDAO find(int id) {
        
    }

    /**
     * Busca a un usuario en la base de datos por su nickname
     * @param nickname nickname del usuario
     * @return El usuario encontrado
     */
    public UserDAO find(String nickname) {
        
    }

    /**
     * Funcion que trae una lista con usuarios random para mostrar en la pagina de sugerencias
     * @return La lista con los usuarios random
     */
    public List<UserDAO> getRandomUsers(){
    	emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
		List<UserDAO> randomsUsers = manager.createQuery(RANDOMUSER).getResultList();
		manager.close();
		return randomsUsers;
    }
}
