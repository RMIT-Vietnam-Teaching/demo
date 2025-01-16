package com.example.demo;

import controllers.CustomerController;
import controllers.DeliverymanController;
import controllers.ItemController;
import controllers.OrderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Restaurant management system");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        CustomerController.read();
        DeliverymanController.read();
        ItemController.read();
        OrderController.read();
        launch();
        CustomerController.store();
        DeliverymanController.store();
        ItemController.store();
        OrderController.store();
    }
}