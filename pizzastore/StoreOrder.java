package com.example.pizzabypatel.pizzastore;

import com.example.pizzabypatel.Utility;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This ADT holds all the current orders in the restaurant
 * @author Dharmik Patel and Krish Patel
 */
public class StoreOrder {
    static int NEXT_ORDER_NUMBER = 1;
    private ArrayList<Order> orders;

    /**
     * This constructor makes the store
     */
    public StoreOrder(){
        orders = new ArrayList<>();
    }



    /**
     * Returns all the order numbers in use
     * @return All the order numbers
     */
    public ArrayList<String> getOrderNumbersAsListOfStrings(){
        List<String> list = orders.stream()
                .map(order -> order.getOrderNumber() + "")
                .toList();
        return new ArrayList<>(list);
    }

    /**
     * This adds an order to the store queue
     * @param order order to add
     */
    public void addOrder(Order order){
        orders.add(order);
    }

    /**
     * This returns the order at the given order number
     * @param orderNumberS the order number of the order wanted
     * @return the orders
     */
    public Order getOrder(String orderNumberS) {
        int orderNumber = Integer.parseInt(orderNumberS);
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }
    /**
     * This removes an order
     * @param orderNumberS the order number of the order to remove
     */
    public void removeOrder(String orderNumberS) {
        int orderNumber = Integer.parseInt(orderNumberS);
        orders.removeIf(order -> order.getOrderNumber() == orderNumber);
    }

    /**
     * This exports the current list of orders to a txt file
     */
    public void export(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targeFile =
                chooser.showSaveDialog(stage);
        if(targeFile == null){
            Utility.throwPopUpError(
                    "Error - Exporting",
                    "Could not open to file");
        }
        else {
            try {
                FileWriter fileWriter = new FileWriter(targeFile);
                for(Order order : orders){
                    fileWriter.write(order.toString() + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                Utility.throwPopUpError(
                        "Error - Exporting",
                        "Could not open to file");
            }
            Utility.throwPopUpInfo(
                    "Done Exporting",
                    "Beware: If there are no orders, " +
                            "then the file will be blank. " +
                            "Open the saved file: " +
                            targeFile.getAbsolutePath());
        }
    }
}
