package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import java.util.List;

/**
 * Interfaz IUserDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey
 *
 */
public interface IUserDAO {
    boolean insert();
    boolean delete();
    boolean update();
    List<User> getAllFollower();
    List<User> getAllFollowing();
    boolean follow(User u);
    boolean unfollow(User u);
    boolean like(User u, IPostDAO p);
    boolean unlike(User u, IPostDAO p);
    UserDAO find(int id);

}
