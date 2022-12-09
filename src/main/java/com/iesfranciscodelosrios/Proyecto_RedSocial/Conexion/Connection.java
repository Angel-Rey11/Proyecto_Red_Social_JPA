package com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Connection {
	public static EntityManager manager;
	public static EntityManagerFactory emf;

	public Connection() {
		emf = javax.persistence.Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
	}

	public boolean insert(Object o) {
		try {
			manager.getTransaction().begin();
			manager.persist(o);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean update(Object o) {
		try {
			manager.getTransaction().begin();
			manager.merge(o);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(Object o) {
		try {
			manager.getTransaction().begin();
			manager.remove(o);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Object find(Object o) {
		try {
			manager.getTransaction().begin();
			manager.find(o.getClass(), o.getClass().getDeclaredField("id"));
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
