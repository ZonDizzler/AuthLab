package org.example.authlab;

import javafx.event.Event;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainmenuController {

    @javafx.fxml.FXML
    private Button logoutButton;

    @javafx.fxml.FXML
    public void onLogoutButtonClick(Event event) throws IOException {
        App.setRoot("home");
    }

}
