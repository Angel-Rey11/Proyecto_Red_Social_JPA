package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ILikeDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Like;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

/**
 * Clase LikeDAO que hereda de Like e implenta ILikeDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class LikeDAO extends Like {
	private final static String GETALLLIKES = "SELECT * FROM Likes WHERE id_post = ";
	private final static String FIND = "SELECT * FROM Likes WHERE id = ?";
	private final static String COUNTLIKES = "SELECT COUNT(*) FROM likes WHERE id_post=?";
	
	private Connection con;
	public static EntityManager manager;
	public static EntityManagerFactory emf;
	
	/**
	 * Constructor
	 */
	public LikeDAO() {
		con = new Connection();
	}
	
	/**
	 * Constructor
	 * @param id ID del like
	 * @param user Usuario del like
	 * @param post Post del like
	 */
	public LikeDAO(int id, User user, Post post) {
		super(id);
	}
	
	/**
	 * Constructor
	 * @param l Like
	 */
	public LikeDAO(Like l) {
		this(l.getId(), l.getUser(), l.getPost());
	}
	
	/**
	 * Constructor
	 * @param id ID del like
	 */
	/*
	public LikeDAO(int id) {
		this.find(id);
	}
	*/

	/**
	 * Método para añadir un like
	 * @return Si el like ha sido añadido
	 */
	public boolean create() {
		if (con.insert(this)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método para borrar un like
	 * @return Si el like ha sido borrado
	 */
	public boolean delete() {
		if (con.delete(this)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método para obtener todos los likes por la ID del post
	 * @param id_post ID del post
	 * @return Lista de likes del post
	 */
	public List<LikeDAO> getAllLikesbyPost() {
		manager = emf.createEntityManager();
		List<LikeDAO> likes = manager.createQuery(GETALLLIKES).getResultList();
		manager.close();
		return likes;
	}
	
	/**
	 * Método para buscar un like por su id
	 * @param id ID del like
	 * @return Like encontrado
	 */
	public LikeDAO find() {
		LikeDAO like = (LikeDAO) con.find(this.getId(),this.getClass());
		return like;
	}
	
	public int countLikes(int id_post) {
		emf = Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
		int count = manager.createQuery(COUNTLIKES).getMaxResults();
		return count;
	}
}
