package com.example.pizzabypatel.submenus;


import com.example.pizzabypatel.MainMenuController;
import com.example.pizzabypatel.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

/**
 * This is the controller for the current order menu view
 * @author Dharmik Patel and Krish Patel
 */
public class CurrentOrderController {
    private MainMenuController mainMenuController;
    @FXML
    private TextField orderNumber;
    @FXML
    private ListView<String> pizzasInOrder;
    @FXML
    private TextField subtotal;
    @FXML
    private TextField tax;
    @FXML
    private TextField orderTotal;

    /**
     * This setup method is called by the main menu
     * to set the starting view of the this menu
     */
    public void setUpMenu(){
        orderNumber.setText("" +
                mainMenuController
                        .getCurrentOrder()
                        .getOrderNumber());
        pizzasInOrder.getItems().addAll(
                mainMenuController
                        .getCurrentOrder()
                        .getPizzasAsString());
        updatePrices();
    }

    /**
     * This setup method is also called by the main
     * menu to get the reference to the main controller
     * and its public members.
     * @param controller The main controller
     */
    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
    }

    /**
     * This method removes the selected pizza from the list
     */
    @FXML
    public void removePizzaFromCurrentOrder() {
        int indexToRemove = pizzasInOrder
                .getSelectionModel().getSelectedIndex();
        if(indexToRemove < 0){
            Utility.throwPopUpError(
                    "Can not remove",
                    "Please click on the " +
                            "pizza to select to remove");
            return;
        }
        pizzasInOrder.getItems().remove(indexToRemove);
        mainMenuController.getCurrentOrder().removePizza(indexToRemove);
        updatePrices();
    }

    /**
     * This method adds the order to the overall store
     */
    @FXML
    public void placeOrderToStore() {
        if(mainMenuController.getCurrentOrder().getNumOfPizzas() == 0){
            Utility.throwPopUpError(
                    "Order not placed",
                    "Can not place order with zero pizzas");
            return;
        }
        mainMenuController
                .getAllStoreOrders()
                .addOrder(mainMenuController.getCurrentOrder());
        resetCurrentOrder();
        Utility.throwPopUpInfo(
                "Order Placed",
                "Your order has been placed!!");
    }

    /**
     * This method resets the view after an order is placed.
     */
    private void resetCurrentOrder(){
        mainMenuController.resetToNextOrder();
        orderNumber.setText("");
        subtotal.setText("");
        tax.setText("");
        orderTotal.setText("");
        pizzasInOrder.getItems().clear();
    }

    /**
     * This method updates the prices of
     * the order after an pizza is removed.
     */
    private void updatePrices() {
        subtotal.setText(new DecimalFormat("#,##0.00")
                .format(mainMenuController.getCurrentOrder().subTotal()));
        tax.setText(new DecimalFormat("#,##0.00")
                .format(mainMenuController.getCurrentOrder().tax()));
        orderTotal.setText(new DecimalFormat("#,##0.00")
                .format(mainMenuController.getCurrentOrder().orderTotal()));
    }
}
