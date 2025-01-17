package com.example.demo;

import controller.CustomerController;
import controller.DeliverymanController;
import controller.ItemController;
import controller.OrderController;
import entities.Customer;
import entities.Deliveryman;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

public class HelloController {
    public ToggleButton asc;
    public TextArea res;
    public TextField cI, cN, cA, cP;
    public TextField dI, dN, dP;
    public TextField iI, iN, iP;
    public TextField oI;
    public DatePicker oD;
    public ComboBox<Customer> cL = new ComboBox<>();
    public ComboBox<Deliveryman> dL = new ComboBox<>();
    public ListView<Item> iL = new ListView<>();

    public void initialize() {
        res.setWrapText(true);
        cL.setItems(FXCollections.observableArrayList(CustomerController.getCustomers().values()));
        iL.setItems(FXCollections.observableArrayList(ItemController.getItems().values()));
        dL.setItems(FXCollections.observableArrayList(DeliverymanController.getDeliverymen().values()));
    }
    public void addC() {
        res.setText(CustomerController.add(cN.getText(), cA.getText(), cP.getText()));
        initialize();
    }
    public void upC() {
        res.setText(CustomerController.update(Integer.parseInt(cI.getText()), cN.getText(), cA.getText(), cP.getText()));
        initialize();
    }
    public void searchCN() {
        res.setText(CustomerController.searchName(cN.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
        initialize();
    }
    public void searchCP() {
        res.setText(CustomerController.searchPhone(cP.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
        initialize();
    }

    public void addD() {
        res.setText(DeliverymanController.add(dN.getText(), dP.getText()));
        initialize();
    }
    public void upD() {
        res.setText(DeliverymanController.update(Integer.parseInt(dI.getText()), dN.getText(), dP.getText()));
        initialize();
    }
    public void delD() {
        res.setText(DeliverymanController.delete(Integer.parseInt(dI.getText())));
        initialize();
    }
    public void searchDN() {
        res.setText(DeliverymanController.searchName(dN.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
        initialize();
    }
    public void searchDP() {
        res.setText(DeliverymanController.searchPhone(dP.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
        initialize();
    }
    public void addO() {
        res.setText(OrderController.add(Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), cL.getValue(), dL.getValue(), iL.getSelectionModel().getSelectedItems().toArray(new Item[0])));
        initialize();
    }
    public void upO() {
        res.setText(OrderController.update(Integer.parseInt(oI.getText()), Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), cL.getValue(), dL.getValue(), iL.getSelectionModel().getSelectedItems().toArray(new Item[0])));
        initialize();
    }
    public void delO() {
        res.setText(OrderController.delete(Integer.parseInt(oI.getText())));
        initialize();
    }
    public void searchOI() {
        res.setText(OrderController.searchId(Integer.parseInt(oI.getText())).toString());
        initialize();
    }
    public void searchOD() {
        res.setText(OrderController.searchDate(Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
        initialize();
    }
    public void addI() {
        res.setText(ItemController.add(iN.getText(), Double.parseDouble(iP.getText())));
        initialize();
    }
    public void upI() {
        res.setText(ItemController.update(Integer.parseInt(iI.getText()), iN.getText(), Double.parseDouble(iP.getText())));
        initialize();
    }
    public void delI() {
        res.setText(ItemController.delete(Integer.parseInt(iI.getText())));
        initialize();
    }
    public void searchIN() {
        res.setText(ItemController.searchName(iN.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
        initialize();
    }
    public void searchIP() {
        res.setText(ItemController.searchPrice(Double.parseDouble(iP.getText()), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
        initialize();
    }
}