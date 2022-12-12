package com.iesfranciscodelosrios.Proyecto_RedSocial.Assets;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.*;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;


public class DataService {
	public static User userLogeado = new User();
	public static User uAux = new User();
	public static Post p = new Post();
	public static Post pAux = new Post();
	public static PostDAO pDAO = new PostDAO();
	public static UserDAO uDAO = new UserDAO();
	public static CommentDAO cDAO = new CommentDAO();
	public static LikeDAO lDAO = new LikeDAO();
	public static FollowDAO fDAO = new FollowDAO();
}
