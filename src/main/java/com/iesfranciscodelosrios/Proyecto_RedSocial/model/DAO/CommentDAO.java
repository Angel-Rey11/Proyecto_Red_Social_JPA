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
public class CommentDAO {

	private static EntityManager manager;
	
	/**
	 * Método para añadir un comentario
	 * @return Si el comentario ha sido añadido
	 */
	public boolean create(Comment c) {
		boolean added = false;
		
		manager = Connection.getConnect().createEntityManager();
		manager.getTransaction().begin();
		manager.persist(c);
		added = true;
		manager.getTransaction().commit();
		manager.close();
		
		return added;
	}

	/**
	 * Método para borrar un comentario
	 * @return Si el comentario ha sido borrado
	 */
	public boolean delete(Comment c) {
		boolean removed = false;
		manager = Connection.getConnect().createEntityManager();
		c = manager.find(Comment.class, c.getId());
		if(manager.contains(c)) {
			manager.getTransaction().begin();
			manager.remove(c);
			manager.getTransaction().commit();
			removed = true;
			manager.close();
		}
		return removed;
	}

	/**
	 * Método para modificar un comentario
	 * @return Si el comentario ha sido modificado
	 */
	public boolean update(Comment c) {
		boolean updated = false;
		
		manager = Connection.getConnect().createEntityManager();
		manager.getTransaction().begin();
		c.setId(c.getId());
		c.setText(c.getText());
		c.setDate(c.getDate());
		manager.merge(c);
		updated = true;
		manager.getTransaction().commit();
		manager.close();
		
		return updated;
	}

	/**
	 * Método para buscar un comentario por su id
	 * @param id ID del comentario
	 * @return Comentario encontrado
	 */
	public Comment find(int id) {
		Comment c = null;
		
		manager = Connection.getConnect().createEntityManager();
		c = manager.find(Comment.class, id);
		manager.close();
		
		return c;
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
