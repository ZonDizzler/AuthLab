package org.example.authlab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.security.auth.login.AppConfigurationEntry;
import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    /**
     * Entry point to JavaFX application
     * Sets up the main window (stage) of the application
     * Is non-static because it refers to an particular instance of a JavaFX thread
     *
     * @param stage main window of the application
     * @throws IOException
     */
    public void start(Stage stage) throws IOException {

        //set the scene (layout or view of the application) to the newly creates scene
        stage.setScene(scene);

        //set window size
        stage.setWidth(500);
        stage.setHeight(500);

        //display the window on the screen
        stage.show();

    }

    /**
     * Change the content of the current scene by loading a new FXML file and setting it as the root of the scene
     * @param fxml name of the fxml file to load
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Load the FXML file, and return the root Parent element of the loaded FXML
     * @param fxml
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println("Loading fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        try {
            //create a new scene using LoadFXML to load home.fxml
            scene = new Scene(loadFXML("home"));
            launch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
