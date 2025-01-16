package com.example.demo;

import controllers.CustomerController;
import controllers.DeliverymanController;
import controllers.ItemController;
import controllers.OrderController;
import entities.Customer;
import entities.Deliveryman;
import entities.Item;
import javafx.collections.FXCollections;
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
    public TextArea res;
    public TextField cI, cN, cP, cA;
    public TextField dI, dN, dP;
    public TextField iI, iN, iP;
    public  TextField oI;
    public DatePicker oD;
    public ComboBox<Customer> cL = new ComboBox<>();
    public ComboBox<Deliveryman> dL = new ComboBox<>();
    public ListView<Item> iL = new ListView<>();

    public void initialize() {
        res.setWrapText(true);
        cL.setItems(FXCollections.observableArrayList(CustomerController.getCustomers().values()));
        iL.setItems(FXCollections.observableArrayList(ItemController.getItems().values()));
        dL.setItems(FXCollections.observableArrayList(DeliverymanController.getDeliverymen().values()));
        iL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void cAU() {
        res.setText(CustomerController.addUpdate(Integer.parseInt(cI.getText()), cN.getText(), cA.getText(), cP.getText()));
    }
    public void cSN() {
        res.setText(CustomerController.searchName(cN.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
    public void cSP() {
        res.setText(CustomerController.searchPhone(cP.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
    public void dAU() {
        res.setText(DeliverymanController.addUpdate(Integer.parseInt(dI.getText()), dN.getText(), dP.getText()));
    }
    public void delD() {
        res.setText(DeliverymanController.delete(Integer.parseInt(dI.getText())));
    }
    public void dSN() {
        res.setText(DeliverymanController.searchName(dN.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
    public void dSP() {
        res.setText(DeliverymanController.searchPhone(dP.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
    public void iAU() {
        res.setText(ItemController.addUpdate(Integer.parseInt(iI.getText()), iN.getText(), Double.parseDouble(iP.getText())));
    }
    public void delI() {
        res.setText(ItemController.delete(Integer.parseInt(iI.getText())));
    }
    public void iSN() {
        res.setText(ItemController.searchName(iN.getText(), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
    public void iSP() {
        res.setText(ItemController.searchPrice(Double.parseDouble(iP.getText()), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
    public void oAU() {
        res.setText(OrderController.addUpdate(Integer.parseInt(oI.getText()), Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), cL.getValue(), dL.getValue(), iL.getSelectionModel().getSelectedItems().toArray(Item[]::new)));
    }
    public void delO() {
        res.setText(OrderController.delete(Integer.parseInt(oI.getText())));
    }
    public void oSI() {
        res.setText(OrderController.searchId(Integer.parseInt(oI.getText()), asc.isSelected()).toString());
    }
    public void oSD() {
        res.setText(OrderController.searchDate(Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), asc.isSelected()).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
}
