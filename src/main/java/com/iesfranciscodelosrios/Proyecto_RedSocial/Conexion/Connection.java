package com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Connection<T> {
	public static EntityManager manager;
	public static EntityManagerFactory emf;

	public Connection() {
		emf = javax.persistence.Persistence.createEntityManagerFactory("sql");
		manager = emf.createEntityManager();
	}

	public boolean insert(T o) {
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
	public boolean update(T o) {
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
	public boolean delete(T o) {
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
	public T find(int id, Class<T> c) {
		try {
			manager.getTransaction().begin();
			T o = manager.find(c, id);
			manager.getTransaction().commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public T find(String nickname, Class<T> c) {
		try {
			manager.getTransaction().begin();
			T o = manager.find(c, nickname);
			manager.getTransaction().commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<T> getList(String query) {
		try {
			manager.getTransaction().begin();
			List<T> lista = manager.createQuery(query).getResultList();
			manager.getTransaction().commit();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<T>();
		}
	}
}
