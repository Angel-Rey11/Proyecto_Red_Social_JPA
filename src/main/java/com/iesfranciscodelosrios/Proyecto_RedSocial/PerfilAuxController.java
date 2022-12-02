package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.FollowDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class PerfilAuxController implements Initializable{
	private FollowDAO fDAO;
	@FXML
	private Label nickname;
	@FXML
	private GridPane postGrid;
	@FXML
	private Button follow;
	@FXML
	private Button unfollow;
	@FXML
	private TextArea bio;
	@FXML 
	private Label nFollower;
	@FXML
	private Label nFollowing;
	@FXML 
	private Label nPost;
	private List<PostDAO> posts;

	@Override
	/**
	 * Inicializa el controlador rellena todos los campos con los datos del usuario que hemos seleccionado
	 * y comprueba si estamos siguiendo a ese usuario o no en funcion de si lo seguimos o no mostrara un boton u otro
	 */
	public void initialize(URL location, ResourceBundle resources) {
		nickname.setText(DataService.pAux.getUser().getNickname());
		bio.setText(DataService.pAux.getUser().getBiografia());
		bio.setEditable(false);
		User pDAO = DataService.pAux.getUser();
		DataService.uAux = (UserDAO) pDAO;
		nFollower.setText(String.valueOf(DataService.uAux.getAllFollower().size()));
		nFollowing.setText(String.valueOf(DataService.uAux.getAllFollowing().size()));
		List<PostDAO> listPost = PostDAO.getPostsByUser(DataService.uAux.getId());
		nPost.setText(String.valueOf(listPost.size()));
		unfollow.setVisible(false);
		follow.setVisible(true);
		unfollow.setDisable(true);
		follow.setDisable(false);
		DataService.userLogeado.getAllFollowing().forEach((u)->{
			if(u.getId() == DataService.pAux.getUser().getId()) {
				unfollow.setVisible(true);
				follow.setVisible(false);
				unfollow.setDisable(false);
				follow.setDisable(true);
			}
		});
		
		posts = new ArrayList<>(posts());
		
		int columns = 0;
		int row = 1;
		try {
			for (int i = 0; i < posts.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("post.fxml"));
				AnchorPane an = fxmlLoader.load();
				PostController post = fxmlLoader.getController();
				post.setData(posts.get(i));
				post.initializePrivado();
				if(columns == 1) {
					columns = 0;
					++row;
				}
				
				postGrid.add(an, columns++, row);
				GridPane.setMargin(an, new Insets(10));
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	private List<PostDAO> posts() {
		List<PostDAO> ls = PostDAO.getPostsByUser(DataService.pAux.getUser().getId());
		return ls;
	}
	
	@FXML
	/**
	 * Metodo que permite seguir a un usuario.
	 */
	private void follow() {
		fDAO = new FollowDAO(-1, DataService.userLogeado, DataService.pAux.getUser());
		if(fDAO.create()) {
			unfollow.setVisible(true);
			follow.setVisible(false);
			unfollow.setDisable(false);
			follow.setDisable(true);
			Loggers.LogsInfo("HAS SEGUIDO A UN USUARIO");
		}

	}
	
	@FXML
	/**
	 * Metodo que permite dejar de seguir a un usuario.
	 */
	private void unfollow() {
		fDAO = new FollowDAO(-1, DataService.userLogeado, DataService.pAux.getUser());
		if(fDAO.delete()) {
			unfollow.setVisible(false);
			follow.setVisible(true);
			unfollow.setDisable(true);
			follow.setDisable(false);
			Loggers.LogsInfo("HAS DEJADO DE SEGUIR A UN USUARIO");
		}
	}
	
	@FXML
	/**
	 * Metodo que permite volver a la pagina principal.
	 */
	private void switchToMain() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	@FXML
	/**
	 * Metodo que permite ir a la pagina de configuracion.
	 */
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	@FXML
	/**
	 * Metodo que permite ir a la pagina de Login.
	 */
	private void switchToLogin() throws IOException {
		DataService.userLogeado = null;
		App.setRoot("Login");
	}
	
	@FXML
	/**
	 * Metodo que permite ir a la pagina de Perfil.
	 */
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
}
