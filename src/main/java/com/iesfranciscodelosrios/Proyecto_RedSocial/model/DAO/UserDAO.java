package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IPostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IUserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDAO {

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

    /**
     * Inserta un usuario en la base de datos
     * @return true si se ha insertado correctamente
     */
    public boolean insert(User user) {
    	manager = Connection.getConnect().createEntityManager();
    	boolean result=false;
    	if(!manager.contains(user)) {
    		 manager.getTransaction().begin();
             manager.persist(user);
             result = true;
             manager.getTransaction().commit();
             manager.close();
    	}
    	return result;
    }

    /**
     * Elimina un usuario de la base de datos
     * @return true si se ha eliminado correctamente
     */
    public boolean delete(User user) {
    	manager = Connection.getConnect().createEntityManager();
    	boolean result=false;
    	if(!manager.contains(user)) {
    		 manager.getTransaction().begin();
             manager.remove(user);
             result = true;
             manager.getTransaction().commit();
             manager.close();
    	}
    	return result;
    }

    /**
     * Actualiza un usuario de la base de datos
     * @return true si se ha actualizado correctamente
     */
    public boolean update(User user) {
    	manager = Connection.getConnect().createEntityManager();
    	boolean result=false;
    	if(!manager.contains(user)) {
    		 manager.getTransaction().begin();
             user.setNickname(user.getNickname());
             user.setName(user.getName());
             user.setBiografia(user.getBiografia());
             user.setPassword(user.getPassword());
             manager.merge(user);
             result = true;
             manager.getTransaction().commit();
             manager.close();
    	}
    	return result;
    }

    /**
     * Si los parametros coinciden con algun usuario de la base de datos permite entrar al programa
     * @param nickname nombre de usuario
     * @param password contraseña
     * @return true si se ha logueado correctamente
     */
    public boolean login(String nickname, String password) {
    	manager = Connection.getConnect().createEntityManager();
       return false;
    }

    /**
     * Se trae todos los seguidores de un usuario
     * @return La lista con todos los seguidores
     */
    public List<UserDAO> getAllFollower() {
    	manager = Connection.getConnect().createEntityManager();
    	return con.getList(GETALLFOLLOWER);
    }

    /**
     * Se trae todos los usuarios que sigue un usuario
     * @return La lista con todos los usuarios que sigue
     */
    public List<UserDAO> getAllFollowing() {
    	manager = Connection.getConnect().createEntityManager();
    	return con.getList(GETALLFOLLOWING);
    }
    
    /**
     * Busca a un usuario en la base de datos por su id
     * @param id id del usuario
     * @return El usuario encontrado
     */
    public User find(int id) {
    	manager = Connection.getConnect().createEntityManager();
        User aux = manager.find(User.class, id);
        manager.close();
        return aux;
    }

    /**
     * Funcion que trae una lista con usuarios random para mostrar en la pagina de sugerencias
     * @return La lista con los usuarios random
     */
    public List<UserDAO> getRandomUsers(){
    	manager = Connection.getConnect().createEntityManager();
    	emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
        manager.getTransaction().begin();
		List<UserDAO> randomsUsers = manager.createQuery(RANDOMUSER).getResultList();
        manager.getTransaction().commit();
		manager.close();
		return randomsUsers;
    }
}
