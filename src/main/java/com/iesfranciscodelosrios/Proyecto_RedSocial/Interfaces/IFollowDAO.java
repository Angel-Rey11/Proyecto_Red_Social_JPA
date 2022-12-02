package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.FollowDAO;

/**
 * Interfaz IFollowDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey
 *
 */
public interface IFollowDAO {
    boolean create();
    boolean delete();
    boolean update();
    FollowDAO find(int id);

}
