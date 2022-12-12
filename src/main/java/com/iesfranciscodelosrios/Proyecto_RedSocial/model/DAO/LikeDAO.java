package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Like;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;

/**
 * Clase LikeDAO que hereda de Like e implenta ILikeDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class LikeDAO {
	
	public static EntityManager manager;
	/**
	 * Método para añadir un like
	 * @return Si el like ha sido añadido
	 */
	public boolean create(Like l) {
		boolean added = false;
		
		manager = Connection.getConnect().createEntityManager();
		manager.getTransaction().begin();
		manager.persist(l);
		added = true;
		manager.getTransaction().commit();
		manager.close();
		
		return added;
	}

	/**
	 * Método para borrar un like
	 * @return Si el like ha sido borrado
	 */
	public boolean delete(Like l) {
		boolean removed = false;
		
		manager = Connection.getConnect().createEntityManager();
		manager.getTransaction().begin();
		manager.remove(l);
		removed = true;
		manager.getTransaction().commit();
		manager.close();
		
		return removed;
	}
	
	/**
	 * Método para obtener todos los likes por la ID del post
	 * @param id_post ID del post
	 * @return Lista de likes del post
	 */
	public List<Like> getAllLikesbyPost(Post p) {
		List<Like> list = new ArrayList<Like>();
		
		manager = Connection.getConnect().createEntityManager();
		list = manager.createQuery("SELECT * FROM Likes WHERE id_post = " + p.getId()).getResultList();
		manager.close();
		
		return list;
	}
	
	/**
	 * Método para buscar un like por su id
	 * @param id ID del like
	 * @return Like encontrado
	 */
	public Like find(int id) {
		Like l = null;
		
		manager = Connection.getConnect().createEntityManager();
		l = manager.find(Like.class, id);
		manager.close();
		
		return l;
	}
	
	public int countLikes(Post p) {
		int count = 0;
		
		manager = Connection.getConnect().createEntityManager();
		count = manager.createQuery("SELECT COUNT(*) FROM likes WHERE id_post = " + String.valueOf(p.getId())).getMaxResults();
		manager.close();
		
		return count;
	}
}
