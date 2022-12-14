package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion.Connection;

import javax.persistence.EntityManager;

public class DAO<T> {
    private static EntityManager manager;

    public boolean create(T o) {
        boolean added = false;
        manager = Connection.getConnect().createEntityManager();
        if(!manager.contains(o)) {
            try {
                manager.getTransaction().begin();
                manager.persist(o);
                manager.getTransaction().commit();
                manager.close();
                added = true;
            } catch (Exception e) {
                e.printStackTrace();
                added = false;
            }
        }
        return added;
    }
    public boolean update(T o) {
        boolean updated = false;
        manager = Connection.getConnect().createEntityManager();
        if(manager.contains(o)) {
            try {
                manager.getTransaction().begin();
                manager.merge(o);
                manager.getTransaction().commit();
                manager.close();
                updated = true;
            } catch (Exception e) {
                e.printStackTrace();
                updated = false;
            }
        }
        return updated;
    }
    public boolean delete(T o, Class<T> c,int id) {
        boolean removed = false;
        manager = Connection.getConnect().createEntityManager();
            o=manager.find(c,id);
        if(manager.contains(o)){
            try {
                manager.getTransaction().begin();
                manager.remove(o);
                manager.getTransaction().commit();
                manager.close();
                removed = true;
            } catch (Exception e) {
                e.printStackTrace();
                removed = false;
            }
        }
        return removed;

    }
    public T find(int id, Class<T> c) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            T o = manager.find(c, id);
            manager.getTransaction().commit();
            manager.close();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
