package org.example.authlab;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @javafx.fxml.FXML
    private TextField emailTF;


    @javafx.fxml.FXML
    private Button loginButton;

    @javafx.fxml.FXML
    private PasswordField pwdTF;

    @javafx.fxml.FXML
    private Button homeButton;

    @javafx.fxml.FXML
    private Label welcomeText;

    @javafx.fxml.FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {

        String email = emailTF.getText();
        String enteredPassword = pwdTF.getText();
        boolean isAuthentifcated = PasswordManager.verifyPassword(email, enteredPassword);
        if (isAuthentifcated) {
            System.out.println("Login successful");
            App.setRoot("mainmenu");
        } else {
            System.out.println("Invalid email or password");
        }
    }

    @javafx.fxml.FXML
    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("home");
    }
}
