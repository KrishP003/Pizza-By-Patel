package com.example.pizzabypatel;

import com.example.pizzabypatel.pizzastore.Order;
import com.example.pizzabypatel.pizzastore.StoreOrder;
import com.example.pizzabypatel.submenus.BYOPizzaController;
import com.example.pizzabypatel.submenus.CurrentOrderController;
import com.example.pizzabypatel.submenus.SpecPizzaController;
import com.example.pizzabypatel.submenus.StoreOrdersController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the controller for the main menu view
 * @author Dharmik Patel and Krish Patel
 */
public class MainMenuController {
    private Order currentOrder;
    private StoreOrder allStoreOrders;

    /**
     * This makes all the vars needed for the application
     */
    public void initialize(){
        if(currentOrder == null){
            currentOrder = new Order();
        }
        if(allStoreOrders == null){
            allStoreOrders = new StoreOrder();
        }
    }

    /**
     * This launches a new menu to order a spec pizza
     */
    @FXML
    public void launchSpecPizzaMenu() {
        Stage view1 = new Stage();
        StackPane root;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "spec-pizza-view.fxml"));
            root = loader.load();
            Scene scene = new Scene(
                    root,
                    Utility.SCREEN_WIDTH,
                    Utility.SCREEN_HEIGHT);
            view1.setScene(scene);
            view1.setTitle("Specialty Pizza Menu");
            view1.show();
            SpecPizzaController view1controller = loader.getController();
            view1controller.setMainController(this);
        } catch (IOException e) {

            Utility.throwPopUpError(
                    "Error",
                    "Ordering Specialty Pizza " +
                            "is not available at this time.");
        }
    }

    /**
     * This launches a new menu to order a custom BYO pizza
     */
    @FXML
    public void launchBYOMenu() {
        Stage view1 = new Stage();
        StackPane root;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "BYO-pizza-view.fxml"));
            root = loader.load();
            Scene scene = new Scene(
                    root,
                    Utility.SCREEN_WIDTH,
                    Utility.SCREEN_HEIGHT);
            view1.setScene(scene);
            view1.setTitle("Build Your Own Pizza Menu");
            view1.show();
            BYOPizzaController view1controller = loader.getController();
            view1controller.setMainController(this);
        } catch (IOException e) {
            Utility.throwPopUpError(
                    "Error",
                    "Making a Build Your Own Pizza " +
                            "is not available at this time.");
        }
    }

    /**
     * This launches a menu to view the current order and total prices
     */
    @FXML
    public void launchCurrOrderMenu() {
        Stage view1 = new Stage();
        StackPane root;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "current-order-view.fxml"));
            root = loader.load();
            CurrentOrderController view1controller = loader.getController();
            view1controller.setMainController(this);
            view1controller.setUpMenu();
            Scene scene = new Scene(
                    root,
                    Utility.SCREEN_WIDTH,
                    Utility.SCREEN_HEIGHT);
            view1.setScene(scene);
            view1.setTitle("Current Order Menu");
            view1.show();

        } catch (IOException e) {
            Utility.throwPopUpError(
                    "Error",
                    "Viewing your Current Order " +
                            "is not available at this time.");
        }

    }

    /**
     * This launches a menu to view all current orders.
     * This is used by the store to view all the orders.
     */
    @FXML
    public void launchStoreOrdersMenu() {
        Stage view1 = new Stage();
        StackPane root;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "store-orders-view.fxml"));
            root = loader.load();
            Scene scene = new Scene(
                    root,
                    Utility.SCREEN_WIDTH,
                    Utility.SCREEN_HEIGHT);
            view1.setScene(scene);
            view1.setTitle("Store Order Menu");
            view1.show();
            StoreOrdersController view1controller = loader.getController();
            view1controller.setMainController(this);
            view1controller.setUpMenu();
        } catch (IOException e) {
            Utility.throwPopUpError(
                    "Error",
                    "Viewing all Store " +
                            "Order is not available at this time.");
        }
    }

    /**
     * This public method is used to get the
     * current order being placed by the user.
     * @return the current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * This is to get the all store orders object
     * @return all store orders
     */
    public StoreOrder getAllStoreOrders() {return allStoreOrders;}

    /**
     * This is to reset the current order after it has been placed.
     */
    public void resetToNextOrder() {
        currentOrder = new Order();
    }
}