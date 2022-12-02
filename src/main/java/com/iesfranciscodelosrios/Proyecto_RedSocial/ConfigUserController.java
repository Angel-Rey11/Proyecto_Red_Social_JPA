package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Dialog;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ConfigUserController implements Initializable {
	
	@FXML
	private AnchorPane editPerfil;
	@FXML
	private TextField nameUser;
	@FXML
	private TextArea biography;
	@FXML
	private TextField nickname;
	@FXML
	private Button update;
	@FXML
	private Button modifyPassword;
	
	@FXML
	private AnchorPane changePwd;
	@FXML
	private PasswordField passwordUser;
	@FXML
	private Button accept;
	
	@FXML
	private AnchorPane passwordNewModify;
	@FXML
	private PasswordField newPassword;
	@FXML
	private PasswordField repeatNewPassword;
	@FXML
	private Button changeNewPassword;
	
	@FXML
	private Button home;
	@FXML
	private Button profile;
	@FXML
	private MenuButton plus;
	@FXML
	private MenuItem settings;
	@FXML
	private MenuItem sign_off;
	
	/**
	 * Metodo para cuando cambiemos a esta pestaña, setee en los campos la información del usuario logeado
	 * para luego poder cambiar
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nameUser.setText(DataService.userLogeado.getName());
		biography.setText(DataService.userLogeado.getBiografia());
		nickname.setText(DataService.userLogeado.getNickname());
	}
	
	/**
	 * Metodo para modificar un usuario, aquí podremos modificar la biografia, nickname y nombre
	 * La contraseña podremos cambiarla en otra vista
	 * Loggers y alertas para la confirmación y errores
	 * @throws IOException
	 */
	@FXML
	private void modifyUser() throws IOException {
		
		String name = nameUser.getText();
		String bio = biography.getText();
		String nick = nickname.getText();
		
		if (DataService.userLogeado != null) {
			if (!nameUser.getText().isEmpty() && !nickname.getText().isEmpty()) {
				DataService.userLogeado = new UserDAO(DataService.userLogeado.getId(), nick, name , DataService.userLogeado.getPassword(), bio);
				DataService.userLogeado.update();
				Dialog.showConfirm("Message", "CAMBIOS REALIZADOS CON ÉXITO", "EL USUARIO HA SIDO MODIFICADO CORRECTAMENTE");
				Loggers.LogsInfo("USUARIO MODIFICADO");
			}else {
				Dialog.showError("ERROR", "FALLO AL INTRODUCIR DATOS", "TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
				Loggers.LogsSevere("TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
			}
		}else {
			Dialog.showError("Message", "ERROR. NO EXISTE EL USUARIO ", "DEBES MOSTRAR EL USUARIO LOGUEADO");
			Loggers.LogsSevere("EL USUARIO NO EXISTE");
		}
		
	}
	
	/**
	 * Metodo para poner visible el anchorpane para cambiar la contraseña
	 * @throws IOException
	 */
	@FXML
	private void changePassword() throws IOException {
		editPerfil.setVisible(false);
		changePwd.setVisible(true);
	}
	
	/**
	 * Metodo para saber si la contraseña es la de ese usuario, para luego poder cambiarla
	 * @throws IOException
	 */
	@FXML
	private void insertPasswordByChange() throws IOException {
		String pass = DigestUtils.sha256Hex(passwordUser.getText());
		
		if (!pass.isEmpty()) {
			if(pass.equals(DataService.userLogeado.getPassword())) {
				this.AnchorPanePasswordNewModify();
			} else {
				Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "CONTRASEÑA ERRÓNEA");
				Loggers.LogsSevere("LA CONTRASEÑA NO COINCIDE");
			}
		}else {
			Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "EL CAMPO CONTRASEÑA DEBE SER COMPLETADO");
			Loggers.LogsSevere("EL ÚNICO CAMPO QUE EXISTE DEBE SER COMPLETADO");
		}
	}
	
	/**
	 * Metodo para poner visible el anchorpane para introducir la nueva contraseña
	 * @throws IOException
	 */
	private void AnchorPanePasswordNewModify() throws IOException {
		changePwd.setVisible(false);
		passwordNewModify.setVisible(true);
	}
	
	/**
	 * Metodo para cambiar la contrasena, escribimos dos veces la nueva contraseña y si es correcta
	 * lo actualiza en la base de datos con los nuevos cambios
	 * @throws IOException
	 */
	@FXML
	private void modifyPasswordUser() throws IOException {

		String pass = newPassword.getText();
		String passN = repeatNewPassword.getText();
		
		if (DigestUtils.sha256Hex(pass).equals(DataService.userLogeado.getPassword())) {
			System.out.println("true");;
		}
		
		
		if (!pass.isEmpty() && !passN.isEmpty()) {
			String newPass = pass;
			newPass = DigestUtils.sha256Hex(newPass);
			if (pass.contentEquals(passN)) {
				System.out.println(newPass);
				System.out.println(DataService.userLogeado.getPassword());
				UserDAO u = new UserDAO(DataService.userLogeado.getId(), DataService.userLogeado.getName(), DataService.userLogeado.getNickname(), newPass, DataService.userLogeado.getBiografia());
				u.update();
				Dialog.showConfirm("OPERACIÓN EXITOSA", "CAMBIOS REALIZADOS CON ÉXITO", "LA CONTRASEÑA HA SIDO MODIFICADA CORRECTAMENTE");
				App.setRoot("MenuPrincipal");
				Loggers.LogsInfo("CONTRASEÑA MODIFICADA");
			} else {
				Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "LOS CAMPOS NO COINCIDEN");
				Loggers.LogsSevere("LOS CAMPOS NO COINCIDEN");
			}
		} else {
			Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
			Loggers.LogsSevere("TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
		}
	}
	
	/**
	 * Metodo para cambiar al menu principal
	 * @throws IOException
	 */
	@FXML
	private void switchToHome() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	/**
	 * Metodo para cambiar al perfil
	 * @throws IOException
	 */
	@FXML
	 private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
	
	/**
	 * Metodo para cambiar a configuracion
	 * @throws IOException
	 */
	@FXML
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	/**
	 * Metodo para cambiar al login
	 * @throws IOException
	 */
	@FXML
	private void switchToLogin() throws IOException {
		DataService.userLogeado = null;
		App.setRoot("Login");
	}
}
