package com.example.demo;

import controller.CustomerController;
import controller.DeliverymanController;
import controller.ItemController;
import controller.OrderController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Customer;
import model.Deliveryman;
import model.Item;

import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    TextArea res;
    @FXML
    ToggleButton asc;
    @FXML TextField cI, iI, dI, oI;
    @FXML TextField cN, dN, iN;
    @FXML TextField cA;
    @FXML TextField cP, dP, iP;
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
        iL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        iL.setItems(FXCollections.observableArrayList(ItemController.getItems().values()));
        dL.setItems(FXCollections.observableArrayList(DeliverymanController.getDeliverymen().values()));
    }

    public void cAU() {
        try {
            CustomerController.addUpdate(Integer.parseInt(cI.getText()), cN.getText(), cA.getText(), cP.getText());
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void cSN() {
        try {
            res.setText(CustomerController.searchName(cN.getText(), asc.isSelected()).toString());
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void cSP() {
        try {
            res.setText(CustomerController.searchPhone(cP.getText(), asc.isSelected()).toString());
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
    public void delD() {
        try {
            DeliverymanController.deleteDeliveryman(Integer.parseInt(dI.getText()));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void dSN() {
        try {
            res.setText(DeliverymanController.searchName(dN.getText(), asc.isSelected()).toString());
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void dSP() {
        try {
            res.setText(DeliverymanController.searchPhone(dP.getText(), asc.isSelected()).toString());
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
    public void delI() {
        try {
            ItemController.deleteItem(Integer.parseInt(iI.getText()));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void iSN() {
        try {
            res.setText(ItemController.searchName(iN.getText(), asc.isSelected()).toString());
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void iSP() {
        try {
            res.setText(ItemController.searchPrice(Double.parseDouble(iP.getText()), asc.isSelected()).toString());
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void oAU() {
        try {
            OrderController.addUpdate(Integer.parseInt(oI.getText()), Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), cL.getValue().getId(), dL.getValue().getId(), iL.getSelectionModel().getSelectedItems().toArray(new Item[0]));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void delO() {
        try {
            OrderController.deleteOrder(Integer.parseInt(oI.getText()));
            res.setText("Operation successful.");
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void oSI() {
        try {
            res.setText(OrderController.searchId(Integer.parseInt(oI.getText()), asc.isSelected()).toString());
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
    public void oSD() {
        try {
            String result = OrderController.searchDate(Date.from(oD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), asc.isSelected()).stream()
                            .map(String::valueOf)
                                    .collect(Collectors.joining("\n"));
            res.setText(result);
        } catch (Exception e) {
            res.setText(e.getMessage());
        }
    }
}