package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ILikeDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Like;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

/**
 * Clase LikeDAO que hereda de Like e implenta ILikeDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class LikeDAO extends Like implements ILikeDAO {
	private final static String INSERT = "INSERT INTO Likes (id_user, id_post) VALUES (?, ?)";
	private final static String DELETE = "DELETE FROM Likes WHERE id_post = ? and id_user = ?";
	private final static String GETALLLIKES = "SELECT * FROM Likes WHERE id_post = ?";
	private final static String FIND = "SELECT * FROM Likes WHERE id = ?";
	private final static String COUNTLIKES = "SELECT COUNT(*) FROM likes WHERE id_post=?";
	
	/**
	 * Constructor
	 */
	public LikeDAO() {
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
	
	/**^
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
	public LikeDAO(int id) {
		this.find(id);
	}

	/**
	 * Método para añadir un like
	 * @return Si el like ha sido añadido
	 */
	@Override
	public boolean create(int id_post) {
		
	}

	/**
	 * Método para borrar un like
	 * @return Si el like ha sido borrado
	 */
	@Override
	public boolean delete(int id_post) {
		
	}
	
	/**
	 * Método para obtener todos los likes por la ID del post
	 * @param id_post ID del post
	 * @return Lista de likes del post
	 */
	@Override
	public List<LikeDAO> getAllLikesbyPost(int id_post) {
		
	}
	
	/**
	 * Método para buscar un like por su id
	 * @param id ID del like
	 * @return Like encontrado
	 */
	@Override
	public LikeDAO find(int id) {
		
	}
	public int countLikes(int id_post) {
		
	}
}
