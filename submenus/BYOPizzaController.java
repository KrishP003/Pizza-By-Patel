package com.example.pizzabypatel.submenus;

import com.example.pizzabypatel.MainMenuController;
import com.example.pizzabypatel.Utility;
import com.example.pizzabypatel.pizzastore.pizza.BuildYourOwn;
import com.example.pizzabypatel.pizzastore.pizza.Pizza;
import com.example.pizzabypatel.pizzastore.pizza.PizzaMaker;
import com.example.pizzabypatel.pizzastore.pizza.enums.Sauce;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;
import com.example.pizzabypatel.pizzastore.pizza.enums.Topping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

/**
 * This is the controller for the BYOD menu view
 * @author Dharmik Patel and Krish Patel
 */
public class BYOPizzaController {
    private MainMenuController mainMenuController;
    private ObservableList<String> notPickedToppings;
    private ObservableList<String> pickedToppings;
    @FXML
    private ListView<String> allIndyListView;
    @FXML
    private ListView<String> pickedIndyListView;
    private Pizza currentPizza;
    @FXML
    private ComboBox pizzaSize;
    @FXML
    private RadioButton tomatoSauce;
    @FXML
    private CheckBox extraSauce;
    @FXML
    private CheckBox extraCheese;
    @FXML
    private TextField price;


    /**
     * This method sets up the BYO menu.
     * it sets all the global vars needed,
     * and also sets up the observable lists.
     */
    public void initialize(){
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");
        currentPizza.setSize(Size.SMALL);
        currentPizza.setSauce(Sauce.TOMATO);

        pizzaSize.setItems(
                FXCollections.observableArrayList(Size.getSizes()));
        pizzaSize.getSelectionModel().selectFirst();

        tomatoSauce.setSelected(true);

        allIndyListView.setEditable(false);
        pickedIndyListView.setEditable(false);

        notPickedToppings =
                FXCollections.observableArrayList(Topping.getToppings());
        pickedToppings = FXCollections.observableArrayList();

        allIndyListView.setItems(notPickedToppings);
        pickedIndyListView.setItems(pickedToppings);

        updatePrice();
    }

    /**
     * This method gets reference to the main controller
     * @param controller the main controller
     */
    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
    }

    /**
     * This method is called when the user wants to add the topping
     * it will check that the user can only add a max of 7 toppings
     * it will also update the price,
     * and also the observable lists, and the backend
     */
    @FXML
    public void addIndyBtnOnPress(){
        if(pickedToppings.size() < BuildYourOwn.MAX_NUM_TOPPING) {
            String selectedIngredient =
                    allIndyListView.getSelectionModel().getSelectedItem();
            if (selectedIngredient != null) {
                notPickedToppings.remove(selectedIngredient);
                pickedToppings.add(selectedIngredient);
                currentPizza.addTopping(
                        Topping
                                .valueOf(
                                        selectedIngredient
                                                .toUpperCase()
                                                .replace(" ",
                                                        "_")));
                updatePrice();
            }
            else{
                Utility.throwPopUpError(
                        "Select An ingredient",
                        "Please select one " +
                                "ingredient before pressing button");
            }
        }else{
            Utility.throwPopUpError(
                    "Maximum number of toppings",
                    "At most 7 toppings!");
        }
    }
    /**
     * This method is called when the user wants to remove the topping
     * it will also update the price,
     * and also the observable lists, and the backend
     */
    @FXML
    public void rmvIndyBtnOnPress(){
        String selectedIngredient =
                pickedIndyListView.getSelectionModel().getSelectedItem();
        if (selectedIngredient != null) {
            pickedToppings.remove(selectedIngredient);
            notPickedToppings.add(selectedIngredient);
            currentPizza.rmvTopping(Topping
                    .valueOf(selectedIngredient
                            .toUpperCase()
                            .replace(" ", "_")));
            updatePrice();
        }
        else{
            Utility.throwPopUpError("Select An ingredient",
                    "Please select one " +
                            "ingredient before pressing button");
        }

    }

    /**
     * This method adds the pizza to the
     * current order from the main controller.
     * This will also reset the UI once the pizza is added,
     * This also checks if the min and max
     * number of toppings is matched.
     */
    @FXML
    public void addToOrderBtnOnPress(){
        if (pickedIndyListView.getItems().size()
                < BuildYourOwn.MIN_NUM_TOPPING) {
            Utility.throwPopUpError(
                    "Minimum number of toppings required",
                    "At least 3 toppings!");
            return;
        }
        if (pickedIndyListView.getItems().size()
                > BuildYourOwn.MAX_NUM_TOPPING) {
            Utility.throwPopUpError(
                    "Max number of toppings required",
                    "At most 7 toppings!");
            return;
        }
        mainMenuController.getCurrentOrder().addPizza(currentPizza);
        resetUI();
        Utility.throwPopUpInfo(
                "Added",
                "Pizza is added to Current Order");

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
     * This method will be called when the user
     * wants to change teh size of the pizza
     */
    @FXML
    public void changePizzaSize() {
        String selectedPizzaSize =
                pizzaSize.
                        getSelectionModel().
                        getSelectedItem().
                        toString();
        currentPizza.setSize(Size.
                valueOf(selectedPizzaSize.toUpperCase()));
        updatePrice();
    }

    /**
     * This is called when the user wants alfredo sauce
     */
    @FXML
    public void alfredoSauceRBOnClick() {
        currentPizza.setSauce(Sauce.ALFREDO);
    }
    /**
     * This is called when the user wants tomato sauce
     */
    @FXML
    public void tomatoSauceRBOnClick() {
        currentPizza.setSauce(Sauce.TOMATO);
    }
    /**
     * This is called when the user wants extra sauce
     */
    @FXML
    public void extraSauceOnClick() {
        currentPizza.setExtraSauce(extraSauce.isSelected());
        updatePrice();
    }
    /**
     * This is called when the user wants extra cheese
     */
    @FXML
    public void extraCheeseOnClick() {
        currentPizza.setExtraCheese(extraCheese.isSelected());
        updatePrice();
    }

    /**
     * This helper method will reset the UI after a pizza is added to order
     */
    private void resetUI(){
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");
        currentPizza.setSize(Size.SMALL);
        currentPizza.setSauce(Sauce.TOMATO);

        pizzaSize.getSelectionModel().selectFirst();

        tomatoSauce.setSelected(true);

        notPickedToppings.clear();
        notPickedToppings.addAll(Topping.getToppings());
        pickedToppings.clear();

        extraSauce.setSelected(false);
        extraCheese.setSelected(false);

        updatePrice();
    }
}
