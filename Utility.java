package com.example.pizzabypatel;

import javafx.scene.control.Alert;

/**
 * This utility class provides popup windows
 * @author Dharmik Patel and Krish Patel
 */
public class Utility {
    public static int SCREEN_WIDTH = 400;
    public static int SCREEN_HEIGHT = 500;

    /**
     * Popup error window
     * @param title The title of the error
     * @param message The description of the error
     */
    public static void throwPopUpError(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - Pizza by Patel");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Popup info window
     * @param title The title of the info to display
     * @param message The description of the information
     */
    public static void throwPopUpInfo(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info - Pizza by Patel");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
