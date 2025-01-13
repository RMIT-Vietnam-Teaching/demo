package com.example.demo;

import controller.CustomerController;
import controller.DeliverymanController;
import controller.ItemController;
import controller.OrderController;
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
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        CustomerController.read();
        ItemController.read();
        OrderController.read();
        DeliverymanController.read();
        launch(args);
        CustomerController.store();
        ItemController.store();
        DeliverymanController.store();
        OrderController.store();
    }
}