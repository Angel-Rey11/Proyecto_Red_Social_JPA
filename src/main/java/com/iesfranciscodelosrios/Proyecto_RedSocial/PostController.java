package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Dialog;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.LikeDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PostController implements Initializable {
	private PostDAO post;
	private LikeDAO like;
	private CommentDAO comment;
	@FXML
	private Label name;
	@FXML
	private Label post2;
	@FXML
	private Label id;
	@FXML
	private ImageView img1; //Corazon vacio
	@FXML
	private ImageView img2; //Corazon lleno
	@FXML
	private Button mg; //Me gusta
	@FXML
	private Button dmg; //No me gusta
	@FXML
	private Label fecha;
	@FXML
	private Label nLikes;
	@FXML
	private Label nComments;

	@FXML
	/**
	 * Metodo para dar me gusta a un post se ejecuta al pulsar el boton
	 */
	private void mg() {
		like = new LikeDAO(-1,DataService.userLogeado,post.find(this.post.getId()));
		if(like.create()){
			mg.setDisable(true);
			dmg.setDisable(false);
			img1.setVisible(false);
			img2.setVisible(true);
		}
		Loggers.LogsInfo("HAS DADO LIKE A UNA PUBLICACIÓN");
	}

	@FXML
	/**
	 * Metodo para quitar un like de un post se ejecuta al pulsar el boton
	 */
	private void dmg() {
		like = new LikeDAO(-1,DataService.userLogeado,this.post);
		if(like.delete()){
			mg.setDisable(false);
			dmg.setDisable(true);
			img1.setVisible(true);
			img2.setVisible(false);
		}
		Loggers.LogsInfo("HAS DADO DISLIKE A UNA PUBLICACIÓN");
	}

	/**
	 * Setea los datos del post que has seleccionado en el menu principal
	 * @param post
	 */
	public void setData(PostDAO post) {
		name.setText(post.getUser().getNickname());
		post2.setText(post.getText());
		String s = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(post.getCreationDate());
		fecha.setText(s);
		this.post = post;
	}

	@FXML
	/**
	 * Metodo que permite volver a la ventana de comentarios
	 */
	private void switchToComments() throws IOException {
		DataService.p = this.post;
		App.setRoot("CommentView");
	}

	@FXML
	/**
	 * Metodo que permite volver a la vista de inicio
	 */
	private void switchToProfile() throws IOException {
		DataService.pAux = this.post;

		if(DataService.userLogeado.getId() == this.post.getUser().getId()) {
			App.setRoot("Perfil");
		}else{
			App.setRoot("PerfilAux");
		}
	}

	/**
	 * Metodo que Muestra el boton de like o dislike dependiendo de si el usuario ha dado like o no
	 */
	public void initializePrivado(){
		like = new LikeDAO();
		comment= new CommentDAO();
		boolean encontrado= false;
		List<LikeDAO> likes = this.like.getAllLikesbyPost();
		nLikes.setText(like.countLikes(this.post.getId())+"");
		nComments.setText(comment.getCommentsCount(this.post.getId())+"");
		for (LikeDAO likeDAO : likes) {
			if(likeDAO.getUser().getId()==DataService.userLogeado.getId()){
				encontrado=true;
			}
		}
		if(encontrado){
			mg.setDisable(true);
			dmg.setDisable(false);
			img1.setVisible(false);
			img2.setVisible(true);
		}else{
			img1.setVisible(true);
			img2.setVisible(false);
			mg.setDisable(false);
			dmg.setDisable(true);
		}
	}
	
	@FXML
	/**
	 * Metodo Para eliminar un post
	 */
	private void deletePost() {
		if(DataService.userLogeado.getId()==this.post.getUser().getId()) {
			post.delete();
		} else {
			Dialog.showError("ERROR", "ERROR AL ELIMINAR", "NO PUEDES ELIMINAR ESTE POST");
			Loggers.LogsSevere("ERROR");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
			this.initializePrivado();
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}
