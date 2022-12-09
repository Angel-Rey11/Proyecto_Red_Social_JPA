package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

public class Pruebas {
    public static void main(String[] args) {
        UserDAO u = new UserDAO(-1,"Pepe", "pepito","1234","Buenas Tardes");
        u.insert();
    }
}
