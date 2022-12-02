package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import java.util.List;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.LikeDAO;

/**
 * Interfaz ILikeDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey
 *
 */
public interface ILikeDAO {
    boolean create(int id_post);
    boolean delete(int id_post);
    List<LikeDAO> getAllLikesbyPost(int id_post);
    LikeDAO find(int id);
}
