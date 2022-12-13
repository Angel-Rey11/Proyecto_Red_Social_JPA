package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserController extends DataService {
	@FXML
	private Label name;
	private User u;
	@FXML
	private Button follow;
	@FXML
	private Button unfollow;

	/**
	 * Metodo para setear el nombre del usuario en la vista
	 * @param user
	 */
	public void setData(User user) {
		name.setText(user.getNickname());
		this.u = user;
	}
	
	@FXML
	/**
	 * Metodo para seguir a un usuario se ejecuta al pulsar el boton
	 * Una vez sigas al usuario se deshabilitara el boton de seguir y se habilitara el de dejar de seguir
	 */
	private void follow() {
		if(userLogeado.getFollowing().add(u)) {
			unfollow.setVisible(true);
			follow.setVisible(false);
			unfollow.setDisable(false);
			follow.setDisable(true);
		}
		Loggers.LogsInfo("HAS SEGUIDO A UN USUARIO");
	}
	
	@FXML
	/**
	 * Metodo para dejar de seguir a un usuario se ejecuta al pulsar el boton
	 * Una vez dejes de seguir al usuario se deshabilitara el boton de dejar de seguir y se habilitara el de seguir
	 */
	private void unfollow() {
		if(userLogeado.getFollowing().remove(u)) {
			unfollow.setVisible(false);
			follow.setVisible(true);
			unfollow.setDisable(true);
			follow.setDisable(false);
		}
		Loggers.LogsSevere("HAS DEJADO DE SEGUIR A UN USUARIO");
	}

	/**
	 * Metodo para comprobar si el usuario logeado sigue al usuario que se muestra en la vista
	 * Y mostrara los botones correspondientes en cualquiera de los casos
	 */
	public void initializePrivado(){
		unfollow.setVisible(false);
		follow.setVisible(true);
		unfollow.setDisable(true);
		follow.setDisable(false);
		uDAO.getAllFollowing(userLogeado).forEach((user)->{
			if(user.getId() == this.u.getId()) {
				unfollow.setVisible(true);
				follow.setVisible(false);
				unfollow.setDisable(false);
				follow.setDisable(true);
			}
		});
	}
}
