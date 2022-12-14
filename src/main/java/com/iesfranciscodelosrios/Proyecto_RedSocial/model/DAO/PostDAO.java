package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

public class PostDAO extends DAO{
	
	//CONSULTAS DE MariaDB
	//FIN DE LAS CONSULTAS
	private static EntityManager manager;
	
	/**
	 * Este método nos sirve para insertar todos los campos en la tabla Post.
	 * @return true si se añade los post, false si no se han insertado correctamente.
	 */
	public boolean create(Post post) {
		return super.create(post);
	}

	/**
	 * Este método nos sirve para eliminar de la tabla Post, todos los post por la id
	 * principal.
	 * @return true si se elimina correctamente todos los post, false si no se ha eliminado.
	 */
	public boolean delete(Post post) {
		return super.delete(post, Post.class, post.getId());
	}

	/**
	 * Este método nos sirve para actualizar todos los campos de la tabla Post.
	 * @return true si se han actualizado todos los campos de la tabla Post, false
	 * si no se han actualizado.
	 */
	public boolean update(Post post) {
		return super.update(post);
	}

	/**
	 * Este método se encarga de obtener de la tabla Post, todos los campos
	 * de dicha tabla por la id principal de dicha tabla.
	 * 
	 * @param id el valor del campo por el que se obtiene. 
	 * @return el post obtenido o null si no existe.
	 */
	public Post find(int id) {
		return (Post) super.find(id, Post.class);
	}
	
	/**
	 * Este método se encarga de obtener una lista de todos los campos de
	 * la tabla Post.
	 * @return el post de la lista obtenida por sus campos.
	 */
	public List<Post> findAllByFollower(User user) {
		List<Post> allPosts = new ArrayList<>();
		manager = Connection.getConnect().createEntityManager();
		user = manager.find(User.class,user.getId());
		user.getPosts().size();
		allPosts = user.getPosts();
		for (User u : user.getFollowing()) {
				for (Post p : u.getPosts()) {
					allPosts.add(p);
				}
		}
		allPosts.sort(Comparator.comparing(Post::getCreationDate).reversed());
		manager.close();
		return allPosts;
	}
	
	/**
	 * Este método se encarga de obtener una lista de post por la id del usuario dado.
	 * @param id el valor del campo por el que se obtiene. 
	 * @return el post de la lista obtenida por el usuario.
	 */
	public List<Post> getPostsByUser(User user) {
		manager = Connection.getConnect().createEntityManager();
		user = manager.find(User.class,user.getId());
		user.getPosts().size();
		manager.close();
		return user.getPosts();
	}
	
	/**
	 * Metodo para añadir like de un post con un usuario
	 * @param post que le damos like
	 * @param u con el cual le damos like
	 * @return true si todo va bien
	 */
	public boolean addLike(Post post, User u) {
		boolean result = false;
		manager = Connection.getConnect().createEntityManager();
		post = manager.find(Post.class, post.getId());
		u = manager.find(User.class, u.getId());
		manager.getTransaction().begin();
    	post.getUserLikes().size();
    	u.getPostsLikes().size();
    	post.addLikes(u);
        manager.persist(post);
        manager.getTransaction().commit();
        manager.close();
        result = true;
		return result;
	}
	
	/**
	 * Metodo para eliminar un like
	 * @param post del que queremos eliminar un like
	 * @param u usuario con el que le dimos like
	 * @return true si lo ha eliminado
	 */
	public boolean removeLike(Post post, User u) {
		boolean result = false;
		manager = Connection.getConnect().createEntityManager();
		post = manager.find(Post.class, post.getId());
		u = manager.find(User.class, u.getId());
		manager.getTransaction().begin();
    	post.getUserLikes().size();
    	u.getPostsLikes().size();
    	post.removeLikes(u);
        manager.persist(post);
        manager.getTransaction().commit();
        manager.close();
        result = true;
		return result;
	}
	
	/**
	 * Metodo para contar el numero de likes y setearlo en un label
	 * @param p que queremos contar likes
	 * @return el entero con el numero de likes
	 */
	public int getCountLikes(Post p) {
		manager = Connection.getConnect().createEntityManager();
		p = manager.find(Post.class, p.getId());
		int count = p.getUserLikes().size();
		manager.close();
		return count;
	}
	
	/**
	 * Metodo que comprueba si el usuario ha dado me gusta o no
	 * @param p post del que queremos comprobar
	 * @param u usuario que queremos ver si ha dado like
	 * @return true si existe el usuario en el array de likes
	 */
	public boolean validatePost(Post p, User u) {
		boolean result = false;
		manager = Connection.getConnect().createEntityManager();
		p = manager.find(Post.class, p.getId());
		p.getUserLikes().size();
		for(User user : p.getUserLikes()) {
			if(user.getId()==u.getId()) {
				result = true;
			}
		}
		manager.close();
		return result;
	}

}
