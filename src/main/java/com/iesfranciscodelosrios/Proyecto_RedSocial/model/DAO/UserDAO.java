package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;


import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO {

	
    private final static String RANDOMUSER = "SELECT * FROM `user` WHERE id NOT IN ("+DataService.userLogeado.getId()+") ORDER BY RAND()*(25-10)+10 LIMIT 6";

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    /**
     * Inserta un usuario en la base de datos
     * @return true si se ha insertado correctamente
     */
    public boolean insert(User user) {
    	emf = Persistence.createEntityManagerFactory("sql");
        manager = emf.createEntityManager();
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
    	boolean result = false;
    	User encontrado = null;
    	encontrado = (User) manager.createQuery("SELECT * FROM user WHERE nickname = "+nickname+" AND password ="+password).getSingleResult();
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
    	List<User> allFollowers = new ArrayList<>();
    	allFollowers = manager.createNativeQuery("SELECT * FROM User WHERE id IN (SELECT id_user_follower FROM Follow WHERE id_user_following = "+u.getId()+")").getResultList();
    	return allFollowers;
    }

    /**
     * Se trae todos los usuarios que sigue un usuario
     * @return La lista con todos los usuarios que sigue
     */
    public List<User> getAllFollowing(User u) {
    	manager = Connection.getConnect().createEntityManager();
    	List<User> allFollowing = new ArrayList<User>();
    	allFollowing = manager.createNativeQuery("SELECT * FROM User WHERE id IN (SELECT id_user_following FROM Follow WHERE id_user_follower = "+u.getId()+")").getResultList();
    	return allFollowing;
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
     * Busca a un usuario en la base de datos por el nickname
     * @param nickname del usuario
     * @return El usuario encontrado
     */
    public User find(String nickname) {
    	manager = Connection.getConnect().createEntityManager();
        User aux = manager.find(User.class, nickname);
        manager.close();
        return aux;
    }

    /**
     * Funcion que trae una lista con usuarios random para mostrar en la pagina de sugerencias
     * @return La lista con los usuarios random
     */
    public List<User> getRandomUsers(){
    	manager = Connection.getConnect().createEntityManager();
    	List<User> userRandoms = new ArrayList<User>();
    	userRandoms = manager.createNativeQuery(RANDOMUSER).getResultList();
    	return userRandoms;
    }
}
