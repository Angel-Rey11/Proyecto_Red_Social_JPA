package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;

import javafx.fxml.FXML;

public class ConfController extends DataService {
	/**
	 * Metodo para cambiar a la vista de editar perfil
	 * @throws IOException
	 */
	@FXML
	private void switchToEditProfile() throws IOException {
		App.setRoot("ConfigUser");
	}
	
	/**
	 * Metodo para cambiar a la vista de menu principal
	 * @throws IOException
	 */
	@FXML
	private void switchToHome() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	/**
	 * Metodo para cambiar a la vista de perfil
	 * @throws IOException
	 */
	@FXML
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
	
	/**
	 * Metodo para cambiar a la vista de configuracion
	 * @throws IOException
	 */
	@FXML
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	/**
	 * Metodo para cambiar a la vista de Login
	 * @throws IOException
	 */
	@FXML
	private void switchToLogin() throws IOException {
		userLogeado = null;
		App.setRoot("Login");
	}
}
