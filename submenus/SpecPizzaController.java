package com.example.pizzabypatel.submenus;

import com.example.pizzabypatel.MainMenuController;
import com.example.pizzabypatel.Utility;
import com.example.pizzabypatel.pizzastore.pizza.Pizza;
import com.example.pizzabypatel.pizzastore.pizza.PizzaMaker;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * This is the controller for the spec pizza menu view
 * @author Dharmik Patel and Krish Patel
 */
public class SpecPizzaController {
    private MainMenuController mainMenuController;
    private ObservableList<String> currentToppings;
    private Pizza currentPizza;
    @FXML
    private ImageView pizzaImg;
    @FXML
    private ListView<String> ingredientsListView;
    @FXML
    private ComboBox<String> pizzaTypeCB;
    @FXML
    private ToggleGroup pizzaSize;
    @FXML
    private Label sauceType;
    @FXML
    private CheckBox extraSauce;
    @FXML
    private CheckBox extraCheese;
    @FXML
    private TextField price;

    /**
     * This method sets up the og view for this menu
     */
    public void initialize(){
        pizzaTypeCB.getItems().addAll(
                Arrays.asList(
                        "Deluxe",
                        "Supreme",
                        "Meatzza",
                        "Seafood",
                        "Pepperoni"));
        pizzaTypeCB.getSelectionModel().select(0);

        currentPizza = PizzaMaker.createPizza("Deluxe");
        currentToppings = FXCollections.observableArrayList(
                currentPizza.getToppingsAsString());
        ingredientsListView.setEditable(false);
        ingredientsListView.setItems(currentToppings);

        updateImage("Deluxe");
        updateSauceType();
        updatePrice();
    }

    /**
     * This setup method gets the reference
     * to the main menu controller, so we can
     * use its public members
     * @param controller The main controller
     */
    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
    }

    /**
     * This method is called when the combo box has a diff
     * item selected. it updates
     * the list view, img, and price
     */
    @FXML
    public void changePizzaType() {
        String selectedPizzaType =
                pizzaTypeCB
                        .getSelectionModel()
                        .getSelectedItem();

        currentPizza = PizzaMaker.createPizza(selectedPizzaType);
        currentPizza.setExtraSauce(extraSauce.isSelected());
        currentPizza.setExtraCheese(extraSauce.isSelected());
        RadioButton selectedRB =
                (RadioButton) pizzaSize.getSelectedToggle();
        currentPizza.setSize(
                Size.valueOf(selectedRB.getText().toUpperCase()));

        currentToppings.clear();
        currentToppings.addAll(currentPizza.getToppingsAsString());
        updateSauceType();
        updateImage(selectedPizzaType);
        updatePrice();
    }

    /**
     * This updates the sauce type.
     */
    private void updateSauceType() {
        sauceType.setText(
                String.format("Sauce: %s",
                        currentPizza.getSauce()));
    }

    /**
     * This updates the image
     * @param selectedPizzaType given the selected pizza type
     */
    private void updateImage(String selectedPizzaType) {
        pizzaImg.setImage(new Image("file:src/main/resources/com/"
                + "example/"
                + "pizzabypatel/img/"
                + selectedPizzaType
                + ".png"));
    }


    /**
     * This will be called when the checkbox is clicked
     */
    @FXML
    public void extraSauceOnClick() {
        currentPizza.setExtraSauce(extraSauce.isSelected());
        updatePrice();
    }

    /**
     * This will be called when the checkbox is clicked
     */
    @FXML
    public void extraCheeseOnClick() {
        currentPizza.setExtraCheese(extraCheese.isSelected());
        updatePrice();
    }

    /**
     * This will be called when the radio button is clicked for size small
     */
    @FXML
    public void smallRBOnClick(){
        currentPizza.setSize(Size.SMALL);
        updatePrice();
    }
    /**
     * This will be called when the radio button is clicked for size med
     */
    @FXML
    public void mediumRBOnClick(){
        currentPizza.setSize(Size.MEDIUM);
        updatePrice();
    }
    /**
     * This will be called when the radio button is clicked for size large
     */
    @FXML
    public void largeRBOnClick(){
        currentPizza.setSize(Size.LARGE);
        updatePrice();
    }
    /**
     * This utility method will update the price of the pizza
     */
    private void updatePrice() {
        price.setText(
                new DecimalFormat("#,##0.00")
                        .format(currentPizza.price()));
    }

    /**
     * Once user is ready to add to order,
     * this method is called, and it will
     * reset the UI
     */
    @FXML
    public void addToOrder() {
        mainMenuController.getCurrentOrder().addPizza(currentPizza);
        resetUI();
        Utility.throwPopUpInfo(
                "Added",
                "Pizza is added to Current Order");
    }

    /**
     * This helper method will reset the UI after a pizza is added to order
     */
    private void resetUI(){
        currentPizza = PizzaMaker.createPizza("Deluxe");
        currentToppings.clear();
        currentToppings.addAll(currentPizza.getToppingsAsString());
        pizzaTypeCB.getSelectionModel().select(0);
        pizzaSize.selectToggle(pizzaSize.getToggles().get(0));
        extraCheese.setSelected(false);
        extraSauce.setSelected(false);
        currentPizza.setSize(Size.SMALL);
        currentPizza.setExtraCheese(false);
        currentPizza.setExtraSauce(false);
        updateImage("Deluxe");
        updateSauceType();
        updatePrice();
    }
}
