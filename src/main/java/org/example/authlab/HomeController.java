package org.example.authlab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button signupButton;

    @FXML
    private Button loginButton;

    public void initialize() {

    }

    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void onSignupButtonClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("signup");
    }
}
