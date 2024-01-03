package com.example.pizzabypatel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is JavaFX runner for the main menu
 * @author Dharmik Patel and Krish Patel
 */
public class MainMenuApplication extends Application {
    /**
     * This method overrides the method in Application.java
     * it will start up the main view
     * @param stage the stage of the app
     * @throws IOException error
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(MainMenuApplication.class
                                .getResource("main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),
                Utility.SCREEN_WIDTH, Utility.SCREEN_HEIGHT);
        stage.setTitle("Pizza by Patel: Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This will launch the main program
     * @param args Used by javafx
     */
    public static void main(String[] args) {
        launch();
    }
}