package com.iesfranciscodelosrios.Proyecto_RedSocial.Assets;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;


public class DataService {
	public static UserDAO userLogeado = new UserDAO();
	public static UserDAO uAux = new UserDAO();
	public static PostDAO p = new PostDAO();
	public static PostDAO pAux = new PostDAO();
}
