module org.example.authlab {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;

    opens org.example.authlab to javafx.fxml;
    exports org.example.authlab;
}