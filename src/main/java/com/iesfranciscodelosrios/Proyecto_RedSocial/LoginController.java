package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Utils;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	private UserDAO uDAO;
	private Utils u;

	@FXML
	private AnchorPane loginPane;
	@FXML
	private AnchorPane signupPane;
	@FXML
	private AnchorPane passwordPane;
	@FXML
	private TextField nicknameField, nicknameFieldsignup, nameField;
	@FXML
	private PasswordField passwordField, passwordFieldsignup, confirnmPasswordField;

	public LoginController() {
		u = new Utils();
		uDAO = new UserDAO();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginPane.setVisible(true);
		signupPane.setVisible(false);
		passwordPane.setVisible(false);
	}

	/**
	 * Este método se encarga de logearser con cualquier usuario que esté registrado
	 * en nuestra Base de Datos para poder acceder a nuestra App.
	 * 
	 * @throws IOException: controla todo tipo de excepción en el caso de que no
	 *                      inicies correctamente con el usuario indicado.
	 */
	@FXML
	private void eventLogin() throws IOException {
		loginPane.setVisible(true);
		signupPane.setVisible(false);
		passwordPane.setVisible(false);

		if (!nicknameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
			String nickname = nicknameField.getText();
			String pass = DigestUtils.sha256Hex(passwordField.getText());
			if (uDAO.login(nickname, pass)) {
				u.mostrarInfo("Login", "Login correcto", "Bienvenido " + nickname);
				DataService.userLogeado = uDAO.find(nickname);
				App.setRoot("MenuPrincipal");
			} else {
				u.mostrarAlerta("Login", "Login incorrecto", "Usuario o contraseña incorrectos");
			}
		} else {
			u.mostrarAlerta("Error", "Error", "Rellena todos los campos");
		}
	}

	/**
	 * Este método se encarga de mostrar la ventana de registrar usuario
	 * 
	 * @throws IOException: controla todo tipo de excepción en el caso de que no te
	 *                      registres correctamente con el usuario.
	 */
	@FXML
	private void eventSignUp2() throws IOException {
		loginPane.setVisible(false);
		signupPane.setVisible(true);
	}

	/**
	 * Este método se encarga de completar todos los campos para poder registrarte
	 * con un usuario dado.
	 */
	@FXML
	private void eventSignup() {
		if (!nicknameFieldsignup.getText().isEmpty() && !passwordFieldsignup.getText().isEmpty()
				&& !nameField.getText().isEmpty()) {
			signupPane.setVisible(false);
			passwordPane.setVisible(true);
		} else {
			u.mostrarAlerta("Error", "Campos vacios", "Rellene todos los campos");
		}
	}

	/**
	 * Este método se encarga de confirmar la contraseña para completar el registro
	 * del usuario con el que te vayas a loguear en la App. Te llevaría a la
	 * pantalla de login y accedes a la App con ese usuario registrado y si se
	 * loguea bien pues te muestra un mensaje de bienvenida.
	 * 
	 * @throws IOException: controla todo tipo de excepción en el caso de que no
	 *                      insertes correctamente la contraseña de dicho usuario.
	 */
	@FXML
	private void eventSignUpConfirm() throws IOException {
		UserDAO userDAO;
		if (!confirnmPasswordField.getText().isEmpty()) {
			String pass = DigestUtils.sha256Hex(confirnmPasswordField.getText());
			String nickname = nicknameFieldsignup.getText();
			String name = nameField.getText();
			if (confirnmPasswordField.getText().equals(passwordFieldsignup.getText())) {
				userDAO = new UserDAO(-1, nickname, name, pass, "");
				DataService.userLogeado = userDAO;
				if (DataService.userLogeado.insert()) {
					u.mostrarInfo("Registro", "Registro correcto", "Bienvenido " + nickname);
					loginPane.setVisible(true);
					signupPane.setVisible(false);
					passwordPane.setVisible(false);
				} else {
					u.mostrarAlerta("Error", "Error", "Error al registrar");
				}
			} else {
				u.mostrarAlerta("Error", "Contraseñas no coinciden", "Las contraseñas no coinciden");
				nicknameFieldsignup.setText("");
				nameField.setText("");
				passwordFieldsignup.setText("");
				confirnmPasswordField.setText("");
				signupPane.setVisible(true);
				passwordPane.setVisible(false);
			}
		} else {
			u.mostrarAlerta("Error", "Error", "Rellena todos los campos");
		}
	}

	/**
	 * Este método se encarga de volver a la pantalla de Login.
	 */
	@FXML
	private void returnBack() {
		nicknameFieldsignup.setText("");
		nameField.setText("");
		passwordFieldsignup.setText("");
		loginPane.setVisible(true);
		signupPane.setVisible(false);
		passwordPane.setVisible(false);
	}
}
