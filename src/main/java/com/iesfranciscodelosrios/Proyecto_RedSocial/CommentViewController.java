package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Dialog;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * Clase CommentViewController
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class CommentViewController extends DataService implements Initializable {
	@FXML
	private Label name;
	
	@FXML
	private Label post;

	@FXML
	private GridPane commentGrid;
	
	@FXML
	private AnchorPane a1;
	
	@FXML
	private TextArea text;
	
	private List<Comment> comment;
	
	/**
	 * Método para cambiar al FXML de 'Main'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToMain() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	/**
	 * Método para cambiar al FXML de 'Profile'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
	
	/**
	 * Método para cambiar al FXML de 'Login'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	/**
	 * Método para cambiar al FXML de 'Login'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToLogin() throws IOException {
		DataService.userLogeado = null;
		App.setRoot("Login");
	}
	
	/**
	 * Método para mostrar el AnchorPane para añadir un comentario
	 */
	@FXML
	private void showAddComment() {
		a1.setVisible(true);
		Loggers.LogsInfo("CREANDO NUEVO COMENTARIO");
	}
	
	public void paintComment(List<Comment> comments) {
		int columns = 0;
		int row = 1;
		commentGrid.getChildren().clear();
		try {
			for (int i = 0; i < comment.size(); i++) {
				FXMLLoader f = new FXMLLoader();
				f.setLocation(getClass().getResource("comment.fxml"));
				AnchorPane a = f.load();
				CommentController c = f.getController();
				c.setData(comment.get(i));
				if (columns == 1) {
					columns = 0;
					++row;
				}
				
				commentGrid.add(a, columns++, row);
				GridPane.setMargin(a, new Insets(10));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para crear un nuevo comentario 
	 */
	@FXML
	private void AddComment() {
		if (!text.getText().equals("")) {
			String message = text.getText();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Comment c = new Comment(-1, message, timestamp, userLogeado, p);
			cDAO.create(c);
			p.getUserComments().add(c);
			userLogeado.getPostComments().add(c);
			comment.add(0,c);
			paintComment(comment);
			a1.setVisible(false);
		}else {
			Dialog.showError("ERROR", "CAMPO INCOMPLETO", "El campo texto debe ser rellenado");
			Loggers.LogsSevere("ERROR. NO SE HA ENCONTRADO TEXTO.");
		}
	}
	
	/**
	 * Método que ejecuta todas las instrucciones contenidas al inicializar
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comment = new ArrayList<>(comments());
		
		int columns = 0;
		int row = 1;
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {
			List<Comment> ls = cDAO.getAllCommentsByIdPost(p);
			if(comment.size()!=ls.size()) {
				//actualizar list
				comment = new ArrayList<>(ls);
				//actualizar el 
				paintComment(comment);
			}	
	    }));
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();
		
		try {
			for (int i = 0; i < comment.size(); i++) {
				FXMLLoader f = new FXMLLoader();
				f.setLocation(getClass().getResource("comment.fxml"));
				AnchorPane a = f.load();
				CommentController c = f.getController();
				c.setData(comment.get(i));
				
				if (columns == 1) {
					columns = 0;
					++row;
				}
				
				commentGrid.add(a, columns++, row);
				GridPane.setMargin(a, new Insets(10));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		name.setText(p.getUser().getNickname());
		post.setText(p.getText());
	}
	
	/**
	 * Método para obtener una lista de comentarios de un post en concreto
	 * @return Lista de comentarios
	 */
	private List<Comment> comments() {
		List<Comment> list = cDAO.getAllCommentsByIdPost(p);
		return list;
	}
}
