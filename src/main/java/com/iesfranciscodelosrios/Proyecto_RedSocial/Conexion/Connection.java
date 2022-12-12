package com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;

import java.util.ArrayList;
import java.util.List;

public class Connection {
	public static EntityManagerFactory emf;
	private static Connection _newInstance;

	private Connection() {
        emf = Persistence.createEntityManagerFactory("sql");
        if(emf==null) {
            Loggers.LogsSevere("No se puede establecer la conexion");
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
