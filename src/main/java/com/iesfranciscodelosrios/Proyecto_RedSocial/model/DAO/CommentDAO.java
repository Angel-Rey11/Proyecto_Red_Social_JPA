package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;

/**
 * Clase CommentDAO que hereda de Comment e implementa ICommentDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class CommentDAO extends DAO{

	private static EntityManager manager;
	
	/**
	 * Método para añadir un comentario
	 * @return Si el comentario ha sido añadido
	 */
	public boolean create(Comment c) {
		return super.create(c);
	}

	/**
	 * Método para borrar un comentario
	 * @return Si el comentario ha sido borrado
	 */
	public boolean delete(Comment c) {
		return super.delete(c, Comment.class, c.getId());
	}

	/**
	 * Método para modificar un comentario
	 * @return Si el comentario ha sido modificado
	 */
	public boolean update(Comment c) {
		return super.update(c);
	}

	/**
	 * Método para buscar un comentario por su id
	 * @param id ID del comentario
	 * @return Comentario encontrado
	 */
	public Comment find(int id) {
		return (Comment) super.find(id, Comment.class);
	}
	
	/**
	 * Método para obtener todos los comentarios por la ID del post
	 * @param id ID del post
	 * @return Lista de comentarios del post
	 */
	public List<Comment> getAllCommentsByIdPost(Post p) {
		manager = Connection.getConnect().createEntityManager();
		p = manager.find(Post.class, p.getId());
		p.getComments().size();
		manager.close();
		p.getComments().sort(Comparator.comparing(Comment::getDate).reversed());
		return p.getComments();
	}
	
	public int getCommentsCount(Post p) {
		manager = Connection.getConnect().createEntityManager();
		p = manager.find(Post.class, p.getId());
		int count = p.getComments().size();
		manager.close();
		return count;
	}
}
