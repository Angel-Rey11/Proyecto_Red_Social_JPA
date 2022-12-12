package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;

public class PostDAO {
	
	//CONSULTAS DE MariaDB
	private final static String FINDALLBYFOLLOWER = "SELECT p.* FROM Post as p, user as u, follow as f WHERE (p.id_user=f.id_user_following and f.id_user_follower=u.id and u.id="+String.valueOf(DataService.userLogeado.getId())+")) OR (p.id_user=u.id and u.id="+String.valueOf(DataService.userLogeado.getId())+") GROUP BY p.id Order by p.creation_date desc";
	private final static String FINDALLBYUSER="SELECT id,text,creation_date,id_user from Post where id_user="+String.valueOf(DataService.userLogeado.getId())+" GROUP BY creation_date DESC";
	//FIN DE LAS CONSULTAS
	private static EntityManager manager;
	
	/**
	 * Este método nos sirve para insertar todos los campos en la tabla Post.
	 * @return true si se añade los post, false si no se han insertado correctamente.
	 */
	public boolean create(Post post) {
		manager = Connection.getConnect().createEntityManager();
		boolean result = false;
		if(manager.contains(post)) {
			manager.getTransaction().begin();
		    manager.persist(post);
		    result=true;
		    manager.getTransaction().commit();
		    manager.close();
		}
		 return result;
	}

	/**
	 * Este método nos sirve para eliminar de la tabla Post, todos los post por la id
	 * principal.
	 * @return true si se elimina correctamente todos los post, false si no se ha eliminado.
	 */
	public boolean delete(Post post) {
		manager = Connection.getConnect().createEntityManager();
		boolean result = false;
		if(manager.contains(post)) {
			manager.getTransaction().begin();
		    manager.remove(post);
		    result=true;
		    manager.getTransaction().commit();
		    manager.close();
		}
		 return result;
		
	}

	/**
	 * Este método nos sirve para actualizar todos los campos de la tabla Post.
	 * @return true si se han actualizado todos los campos de la tabla Post, false
	 * si no se han actualizado.
	 */
	public boolean update(Post post) {
		manager = Connection.getConnect().createEntityManager();
		boolean result = false;
		if(manager.contains(post)) {
			manager.getTransaction().begin();
		    manager.merge(post);
		    result=true;
		    manager.getTransaction().commit();
		    manager.close();
		}
		 return result;
		
	}

	/**
	 * Este método se encarga de obtener de la tabla Post, todos los campos
	 * de dicha tabla por la id principal de dicha tabla.
	 * 
	 * @param id el valor del campo por el que se obtiene. 
	 * @return el post obtenido o null si no existe.
	 */
	public Post find(int id) {
		manager = Connection.getConnect().createEntityManager();
        Post post = null;
        post = manager.find(Post.class,id);
        manager.close();
        return post;
	}
	
	/**
	 * Este método se encarga de obtener una lista de todos los campos de
	 * la tabla Post.
	 * @return el post de la lista obtenida por sus campos.
	 */
	public List<Post> findAllByFollower() {
		List<Post> misPost = new ArrayList<Post>();
		manager = Connection.getConnect().createEntityManager();
		misPost = manager.createQuery(FINDALLBYFOLLOWER).getResultList();
		manager.close();
		return misPost;
	}
	
	/**
	 * Este método se encarga de obtener una lista de post por la id del usuario dado.
	 * @param id el valor del campo por el que se obtiene. 
	 * @return el post de la lista obtenida por el usuario.
	 */
	public List<Post> getPostsByUser() {
		List<Post> misPost = new ArrayList<Post>();
		manager = Connection.getConnect().createEntityManager();
		misPost = manager.createQuery(FINDALLBYUSER).getResultList();
		manager.close();
		return misPost;
	}

}
