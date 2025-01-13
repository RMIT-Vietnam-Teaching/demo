module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires static lombok;
    requires com.fasterxml.jackson.databind;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports entity;
}