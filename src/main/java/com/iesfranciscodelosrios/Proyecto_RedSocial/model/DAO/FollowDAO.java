package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;



import javax.persistence.EntityManager;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Follow;

public class FollowDAO {

	private static EntityManager manager;
	
	/**
	 * Este método nos sirve para insertar en la tabla Follow los seguidores y
	 * seguidos de dicho usuario. 
	 * @return true si se añade a los seguidores yseguidos, false si no se han insertado correctamente.
	 */
	public boolean create(Follow f) {
		manager = Connection.getConnect().createEntityManager();
		boolean result=false;
    	if(!manager.contains(f)) {
    		 manager.getTransaction().begin();
             manager.persist(f);
             result = true;
             manager.getTransaction().commit();
             manager.close();
    	}
    	return result;
	}

	/**
	 * Este método nos sirve para eliminar de la tabla Follow los seguidores y
	 * seguidos. 
	 * @return true si se elimina correctamente los seguidores y seguidos, false si no se ha eliminado.
	 */
	public boolean delete(Follow f) {
		manager = Connection.getConnect().createEntityManager();
		manager = Connection.getConnect().createEntityManager();
		boolean result=false;
    	if(!manager.contains(f)) {
    		 manager.getTransaction().begin();
             manager.remove(f);
             result = true;
             manager.getTransaction().commit();
             manager.close();
    	}
    	return result;
	}

	/**
	 * Este método nos sirve para actualizar los seguidores y seguidos 
	 * por la id de la tabla Follow. 
	 * @return true si se ha actualizado la tabla Follow, false si no se ha actualizado correctamente.
	 */
	public boolean update(Follow f) {
		manager = Connection.getConnect().createEntityManager();
    	boolean result=false;
    	if(!manager.contains(f)) {
    		 manager.getTransaction().begin();
             manager.merge(f);
             result = true;
             manager.getTransaction().commit();
             manager.close();
    	}
    	return result;
	}

	/**
	 * Este método se encarga de obtener de la tabla Follow los seguidores y
	 * seguidos por la id principal de dicha tabla.
	 * 
	 * @param id el valor del campo por el que se obtiene. 
	 * @return seguidores y seguidos obtenidos o null si no existen.
	 */
	public Follow find(int id) {
		manager = Connection.getConnect().createEntityManager();
        Follow aux = manager.find(Follow.class, id);
        manager.close();
        return aux;
	}
}
