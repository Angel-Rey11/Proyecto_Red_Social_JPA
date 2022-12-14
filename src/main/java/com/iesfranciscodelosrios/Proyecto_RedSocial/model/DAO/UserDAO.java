package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;


import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDAO extends DAO{

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    /**
     * Inserta un usuario en la base de datos
     * @return true si se ha insertado correctamente
     */
    public boolean insert(User user) {
        return super.create(user);
    }

    /**
     * Elimina un usuario de la base de datos
     * @return true si se ha eliminado correctamente
     */
    public boolean delete(User user) {
        return super.delete(user, User.class, user.getId());
    }

    /**
     * Actualiza un usuario de la base de datos
     * @return true si se ha actualizado correctamente
     */
    public boolean update(User user) {
        return super.update(user);
    }
    /**
     * Busca a un usuario en la base de datos por su id
     * @param id id del usuario
     * @return El usuario encontrado
     */
    public User find(int id) {
        return (User) super.find(id, User.class);
    }

    /**
     * Si los parametros coinciden con algun usuario de la base de datos permite entrar al programa
     * @param nickname nombre de usuario
     * @param password contraseña
     * @return true si se ha logueado correctamente
     */
    public boolean login(String nickname, String password) {
    	manager = Connection.getConnect().createEntityManager();
    	boolean result = false;
    	User encontrado = null;
    	Query q = manager.createNativeQuery("SELECT * FROM user WHERE nickname = ? AND password = ?",User.class);
    	q.setParameter(1, nickname);
    	q.setParameter(2, password);
    	encontrado = (User) q.getSingleResult();
    	if(encontrado != null) {
    		result = true;
    	}
    	return result;
    }

    /**
     * Se trae todos los seguidores de un usuario
     * @return La lista con todos los seguidores
     */
    public List<User> getAllFollower(User u) {
    	manager = Connection.getConnect().createEntityManager();
    	u = manager.find(User.class, u.getId());
    	u.getFollowers().size();
    	manager.close();
    	return u.getFollowers();
    }

    /**
     * Se trae todos los usuarios que sigue un usuario
     * @return La lista con todos los usuarios que sigue
     */
    public List<User> getAllFollowing(User u) {
    	manager = Connection.getConnect().createEntityManager();
    	u = manager.find(User.class, u.getId());
    	u.getFollowing().size();
    	manager.close();
    	return u.getFollowing();
    }
    
    /**
     * Busca a un usuario en la base de datos por el nickname
     * @param nickname del usuario
     * @return El usuario encontrado
     */
    public User find(String nickname) {
    	List<User> misUsers = new ArrayList<User>();
    	manager = Connection.getConnect().createEntityManager();
    	Query q = manager.createNativeQuery("SELECT * FROM user WHERE nickname = ?",User.class);
    	q.setParameter(1, nickname);
    	misUsers = q.getResultList();
    	User aux = misUsers.get(0);
        manager.close();
        return aux;
    }

    /**
     * Funcion que trae una lista con usuarios random para mostrar en la pagina de sugerencias
     * @return La lista con los usuarios random
     */
    public List<User> getRandomUsers(){
    	List<User> misUsers = new ArrayList<User>();
    	manager = Connection.getConnect().createEntityManager();
    	Query q = manager.createNativeQuery("SELECT * FROM `user` WHERE id NOT IN (?) ORDER BY RAND()*(25-10)+10 LIMIT 6",User.class);
    	q.setParameter(1, DataService.userLogeado.getId());
    	misUsers = q.getResultList();
        manager.close();
        return misUsers;
    }
    /**
     * 
     * @param user_follower el usuario que va a ser follower
     * @param user_followed el usuario al que vas a seguir
     * @return true si todo ok
     */
    public boolean addFollow(User user_follower, User user_followed) {
    	boolean result = false;
    	manager = Connection.getConnect().createEntityManager();
    	//nuevo manager para crear modificaciones
    	User followerManaged = manager.merge(user_follower);
    	
    	List<User> misFollows = followerManaged.getFollowing();
    	manager.getTransaction().begin();
    	user_follower.addFollowing(user_followed);
        //manager.persist(u);
        manager.getTransaction().commit();
        manager.close();
        result = true;
    	return result;
    }
    
    /**
     * Metodo para borrar follow
     * @param u user logeado
     * @param aux user que vamos a dejar de seguir
     * @return true si todo lo ha hecho bien
     */
    public boolean removeFollow(User u, User aux) {
    	boolean result = false;
    	manager = Connection.getConnect().createEntityManager();
    	u = manager.find(User.class, u.getId());
    	if(manager.contains(u)) {
    		List<User> misFollows = u.getFollowing();
        	manager.getTransaction().begin();
        	misFollows.remove(aux);
        	u.setFollowing(misFollows);
            manager.getTransaction().commit();
            manager.close();
            result = true;
    	}
    	return result;
    }
}
