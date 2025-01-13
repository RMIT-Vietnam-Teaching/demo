package com.example.demo;

import controller.CustomerController;
import controller.DeliverymanController;
import controller.ItemController;
import controller.OrderController;
import entity.Customer;
import entity.Deliveryman;
import entity.Item;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

public class HelloController {
    public ToggleButton asc;
    @FXML
    private TextArea res;
    @FXML
    TextField cI, cA, cN, cP;
    @FXML
    TextField dI, dN, dP;
    @FXML
    TextField iI, iN, iP;
    @FXML
    TextField oI;
    @FXML
    DatePicker oD;
    @FXML
    ComboBox<Customer> cL;
    @FXML
    ComboBox<Deliveryman> dL;
    @FXML
    ListView<Item> iL;

    public void initialize() {
        res.setWrapText(true);
        cL.setItems(FXCollections.observableArrayList(CustomerController.getCustomers().values()));
        iL.setItems(FXCollections.observableArrayList(ItemController.getItems().values()));
        dL.setItems(FXCollections.observableArrayList(DeliverymanController.getDeliverymen().values()));
        iL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void cAU() {
        try {
            CustomerController.addUpdate(Integer.parseInt(cI.getText()), cN.getText(), cA.getText(), cP.getText());
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
        initialize();
    }
    public void cSN() {
        try {
            res.setText(CustomerController.searchName(cN.getText(), asc.isSelected()).stream()
                    .map(Customer::toString)
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void cSP() {
        try {
            res.setText(CustomerController.searchPhone(cP.getText(), asc.isSelected()).stream()
                    .map(Customer::toString)
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void dAU() {
        try {
            DeliverymanController.addUpdate(Integer.parseInt(dI.getText()), dN.getText(), dP.getText());
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void dSN() {
        try {
            res.setText(DeliverymanController.searchName(dN.getText(), asc.isSelected()).stream()
                    .map(Deliveryman::toString)
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void dSP() {
        try {

            res.setText(DeliverymanController.searchPhone(dP.getText(), asc.isSelected()).stream()
                    .map(Deliveryman::toString)
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void iAU() {
        try {
            ItemController.addUpdate(Integer.parseInt(iI.getText()), iN.getText(), Double.parseDouble(iP.getText()));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void delD() {
        try {
            DeliverymanController.delete(Integer.parseInt(dI.getText()));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void delI() {
        try {
            ItemController.delete(Integer.parseInt(iI.getText()));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void iSN() {
        try {

            res.setText(ItemController.searchName(iN.getText(), asc.isSelected()).stream()
                    .map(Item::toString)
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void iSP() {
        try {

            res.setText(ItemController.searchPrice(Double.parseDouble(dP.getText()), asc.isSelected()).stream()
                    .map(Item::toString)
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void oAU() {
        try {
            OrderController.addUpdate(Integer.parseInt(oI.getText()), Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), cL.getSelectionModel().getSelectedItem().getId(), dL.getSelectionModel().getSelectedItem().getId(), iL.getSelectionModel().getSelectedItems().toArray(Item[]::new));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void delO() {
        try {
            OrderController.delete(Integer.parseInt(oI.getText()));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void oSD() {
        try {

            res.setText(OrderController.searchDate(Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), asc.isSelected()).stream()
                    .map(Order::toString)
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void oSI() {
        try {

            res.setText(OrderController.searchId(Integer.parseInt(oI.getText())).toString());
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
}