package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;

/**
 * Interfaz IPostDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey
 *
 */
public interface IPostDAO {
    boolean create();
    boolean delete();
    boolean update();
    PostDAO find(int id);
}
