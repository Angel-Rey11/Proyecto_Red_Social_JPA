package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IFollowDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Follow;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

public class FollowDAO {

	// CONSULTAS DE MariaDB
	private final static String INSERT = "INSERT INTO Follow (id_user_follower, id_user_following) VALUES (?, ?)";
	private final static String UPDATE = "UPDATE Follow SET id_user_follower = ?, id_user_following = ? WHERE id = ?";
	private final static String DELETE = "DELETE FROM Follow WHERE id_user_follower = ? AND id_user_following = ?";
	private final static String FIND = "SELECT id, id_user_follower, id_user_following FROM Follow WHERE id = ?";
	// FIN DE LAS CONSULTAS
	
	private Connection con;
	public static EntityManager manager;
	public static EntityManagerFactory emf;

	/**
	 * Este método nos sirve para insertar en la tabla Follow los seguidores y
	 * seguidos de dicho usuario. 
	 * @return true si se añade a los seguidores yseguidos, false si no se han insertado correctamente.
	 */
	public boolean create() {
		if (con.insert(this)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Este método nos sirve para eliminar de la tabla Follow los seguidores y
	 * seguidos. 
	 * @return true si se elimina correctamente los seguidores y seguidos, false si no se ha eliminado.
	 */
	public boolean delete() {
		if (con.delete(this)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Este método nos sirve para actualizar los seguidores y seguidos 
	 * por la id de la tabla Follow. 
	 * @return true si se ha actualizado la tabla Follow, false si no se ha actualizado correctamente.
	 */
	public boolean update() {
		if (con.update(this)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Este método se encarga de obtener de la tabla Follow los seguidores y
	 * seguidos por la id principal de dicha tabla.
	 * 
	 * @param id el valor del campo por el que se obtiene. 
	 * @return seguidores y seguidos obtenidos o null si no existen.
	 */
	public FollowDAO find() {
		FollowDAO follow = (FollowDAO) con.find(this);
		return follow;
	}
}
