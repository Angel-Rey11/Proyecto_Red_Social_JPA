package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ICommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

/**
 * Clase CommentDAO que hereda de Comment e implementa ICommentDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class CommentDAO extends Comment implements ICommentDAO {
	private UserDAO uDAO;
	private PostDAO pDAO;
	private Connection con;
	
	private final static String INSERT = "INSERT INTO Comments (text, date, id_user, id_post) VALUES (?, ?, ?, ?)";
	private final static String DELETE = "DELETE FROM Comments WHERE id = ?";
	private final static String UPDATE = "UPDATE Comments SET text = ?, date = ?, id_user = ?, id_post = ? WHERE id = ?";
	private final static String FIND = "SELECT id, text, date, id_user, id_post FROM Comments WHERE id = ?";
	private final static String GETALLBYPOST = "SELECT id, text, date, id_user FROM Comments WHERE id_post = ? ORDER BY date DESC";
	private final static String GETCOUNT = "SELECT COUNT(*) FROM Comments WHERE id_post = ?";

	/**
	 * Constructor
	 */
	public CommentDAO() {
		uDAO = new UserDAO();
		pDAO = new PostDAO();
		con = new Connection<CommentDAO>();
	}
	
	/**
	 * Constructor 
	 * @param id ID del comentario
	 * @param text Texto del comentario
	 * @param date Fecha del comentario
	 * @param u Usuario del comentario
	 * @param p Post del comentario
	 */
	public CommentDAO(int id, String text, Timestamp date, User u, Post p) {
		super(id, text, date,u,p);
	}
	
	/**
	 * Constructor
	 * @param c Comentario
	 */
	public CommentDAO(Comment c) {
		this(c.getId(), c.getText(), c.getDate(), c.getUser(), c.getPost());
	}
	
	/**
	 * Constructor
	 * @param id ID del comentario
	 */
	public CommentDAO(int id) {
		this.find(id);
	}

	/**
	 * Método para añadir un comentario
	 * @return Si el comentario ha sido añadido
	 */
	@Override
	public boolean create() {
		if(con.insert(this)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Método para borrar un comentario
	 * @return Si el comentario ha sido borrado
	 */
	@Override
	public boolean delete() {
		if(con.delete(this)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Método para modificar un comentario
	 * @return Si el comentario ha sido modificado
	 */
	@Override
	public boolean update() {
		if (con.update(this)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Método para buscar un comentario por su id
	 * @param id ID del comentario
	 * @return Comentario encontrado
	 */
	@Override
	public CommentDAO find(int id) {
		CommentDAO c = (CommentDAO) con.find(id,this.getClass());
		return c;
	}
	
	/**
	 * Método para obtener todos los comentarios por la ID del post
	 * @param id ID del post
	 * @return Lista de comentarios del post
	 */
	@Override
	public List<CommentDAO> getAllCommentsByIdPost(int id) {
		return con.getList(GETALLBYPOST);
	}
	public int getCommentsCount(int id_post) {
		return 0;
	}
}
