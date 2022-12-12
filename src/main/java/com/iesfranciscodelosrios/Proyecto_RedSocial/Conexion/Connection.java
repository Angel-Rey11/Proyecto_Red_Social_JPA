package com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Connection {
	public static EntityManagerFactory emf;
	private static Connection _newInstance;

	private Connection() {
        emf = Persistence.createEntityManagerFactory("MySQL");
        if(emf==null) {
            System.out.println("NULL");
        }
    }
	
	public static EntityManagerFactory getConnect() {
        if(_newInstance==null) {
            _newInstance=new Connection();
        }
        return emf;
    }
	
	public static void close() {
        if(emf != null) {
            emf.close();
        }
    }
}
