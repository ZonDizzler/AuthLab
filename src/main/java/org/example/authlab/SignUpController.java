package org.example.authlab;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class SignUpController {


    @javafx.fxml.FXML
    private TextField emailTF;

    @javafx.fxml.FXML
    private PasswordField pwdTF;

    @javafx.fxml.FXML
    private Button homeButton;

    @javafx.fxml.FXML
    private Button signupButton;

    @javafx.fxml.FXML
    private PasswordField cpwdTF;

    @javafx.fxml.FXML
    private Label welcomeText;

    @javafx.fxml.FXML
    private void initialize() {
        DBManager.createUserTable();
    }

    @javafx.fxml.FXML
    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("home");
    }

    @javafx.fxml.FXML
    public void onSignUpButtonClick(ActionEvent actionEvent) throws IOException {
        String email = emailTF.getText();
        String password = pwdTF.getText();
        String confirmPassword = cpwdTF.getText();

        String validationError = Validator.validateCredentials(email, password, confirmPassword);

        if (validationError == null) { //Credentials are valid if validation error is null

            byte[] bsalt = PasswordManager.generateSalt();
            String strSalt = PasswordManager.byteArrayToString(bsalt);

            String passwordHash = PasswordManager.generatePasswordHash(password, bsalt);
            User newuser = new User(email, strSalt, passwordHash);
            DBManager.addUser(newuser);
            App.setRoot("login");
        } else { //Credentials are invalid, show an alert
            Platform.runLater(() -> {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setContentText(validationError);
                alert.showAndWait();

            });
        }
    }
}
