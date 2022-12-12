package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SuggestUserController extends DataService implements Initializable {
	private List<User> users;
	@FXML
	private GridPane postGrid;
	private User u = new User();
	
	@FXML
	/**
	 * Metodo para cambiar de escena a la de Perfil
	 */
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
	@FXML
	/**
	 * Metodo para cambiar de escena a la de Configuracion
	 */
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	@FXML
	/**
	 * Metodo para cambiar de escena a la de Login
	 */
	private void switchToLogin() throws IOException {
		DataService.userLogeado = null;
		App.setRoot("Login");
	}
	@FXML
	/**
	 * Metodo para cambiar de escena a la de MenuPrincipal
	 */
	private void switchToHome() throws IOException {
		App.setRoot("MenuPrincipal");
	}

	/**
	 * Metodo para traerte de la base de datos usuarios como sugerencia para seguir
	 * @return Lista de usuarios
	 */
	private List<User> users() {
		List<User> ud = uDAO.getRandomUsers();
		return ud;
	}
	@Override
	/**
	 * Metodo para incrustar dentro del menu principal un fxml que es user.fxml con cada usuario de la lista
	 */
	public void initialize(URL location, ResourceBundle resources) {
		int columns = 0;
		int row = 1;
		users = new ArrayList<>(users());
		try {
			for (int i = 0; i < users.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("user.fxml"));
				AnchorPane an = fxmlLoader.load();
				UserController user = fxmlLoader.getController();
				user.setData(users.get(i));
				user.initializePrivado();
				if(columns == 1) {
					columns = 0;
					++row;
				}
				
				postGrid.add(an, columns++, row);
				GridPane.setMargin(an, new Insets(10,10,10,90));
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
}
