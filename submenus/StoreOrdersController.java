package com.example.pizzabypatel.submenus;

import com.example.pizzabypatel.MainMenuController;
import com.example.pizzabypatel.Utility;
import com.example.pizzabypatel.pizzastore.Order;
import com.example.pizzabypatel.pizzastore.StoreOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

/**
 * This is the controller for the store orders menu view
 * @author Dharmik Patel and Krish Patel
 */
public class StoreOrdersController {
    private MainMenuController mainMenuController;
    @FXML
    private ComboBox<String> orderNumPicker;
    @FXML
    private TextField orderTotal;
    @FXML
    private ListView<String> pizzasInCurrOrder;

    private StoreOrder storeOrders;
    private ObservableList<String> orderNumbers;

    /**
     * This setup method is called by the main
     * menu, and used to setup the
     * og view of the this menu
     */
    public void setUpMenu(){
        orderNumbers =
                FXCollections
                        .observableArrayList(
                                storeOrders
                                        .getOrderNumbersAsListOfStrings());

        orderNumPicker.setItems(orderNumbers);
        orderNumPicker.getSelectionModel().selectLast();

        updateListOfPizzasBasedOnSelectedOrderNumber();
    }

    /**
     * This setup method gets the reference
     * for the main menu and in turn we
     * can use the public members
     * @param controller the main controller
     */
    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
        storeOrders = mainMenuController.getAllStoreOrders();
    }

    /**
     * This method is called when the user wants to remove a order
     */
    @FXML
    public void removeOrder(){
        int indexToRemove = orderNumPicker
                .getSelectionModel()
                .getSelectedIndex();
        String toRemove  = orderNumPicker
                .getSelectionModel()
                .getSelectedItem();
        if(indexToRemove < 0 || toRemove == null){
            Utility.throwPopUpError(
                    "Not cancelled",
                    "Please select an order to " +
                            "cancel. if non in list place" +
                            " a order first");
            return;
        }
        storeOrders.removeOrder(orderNumPicker
                .getSelectionModel()
                .getSelectedItem());
        orderNumbers.remove(orderNumPicker
                .getSelectionModel()
                .getSelectedIndex());
        orderNumPicker.getSelectionModel().selectLast();

        updateListOfPizzasBasedOnSelectedOrderNumber();

        Utility.throwPopUpInfo(
                "Order Removed",
                "We are sorry to see your order cancelled");
    }

    /**
     * This is called when user wants to export the current orders
     */
    @FXML
    public void exportOrders(){
        storeOrders.export();
    }

    /**
     * This is called to when the user wants to view a diff order
     */
    @FXML
    public void changeOrderNumber(){
        updateListOfPizzasBasedOnSelectedOrderNumber();
    }

    /**
     * This is a helper method which updates the list of pizzas list view
     */
    private void updateListOfPizzasBasedOnSelectedOrderNumber() {
        String orderNumber = orderNumPicker
                .getSelectionModel()
                .getSelectedItem();
        if(orderNumber == null){
            pizzasInCurrOrder.getItems().clear();
            orderTotal.setText("");
            return;
        }
        Order currentSetOrder = storeOrders.getOrder(orderNumber);
        pizzasInCurrOrder.getItems().clear();
        pizzasInCurrOrder.getItems()
                .addAll(currentSetOrder.getPizzasAsString());
        orderTotal.setText(
                new DecimalFormat("#,##0.00")
                        .format(currentSetOrder.orderTotal()));
    }
}
