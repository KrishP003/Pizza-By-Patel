package com.example.pizzabypatel;

import com.example.pizzabypatel.pizzastore.pizza.Pizza;
import com.example.pizzabypatel.pizzastore.pizza.PizzaMaker;
import com.example.pizzabypatel.pizzastore.pizza.enums.Sauce;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;
import com.example.pizzabypatel.pizzastore.pizza.enums.Topping;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildYourOwnTest {

    /**
     * Test Case 1
     * This tests the price method of the BYO class
     * with the largest pizza size, and the min num of toppings,
     * and both extra addons
     * Sauce type does not matter in price
     */
    @Test
    void priceTest1LargestSizeMinToppings2Addons() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        pizza.setSauce(Sauce.TOMATO);
        pizza.setSize(Size.LARGE);
        pizza.setExtraCheese(true);
        pizza.setExtraSauce(true);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.addTopping(Topping.PEPPERONI);

        // Calculated by hand, based on prices
        // provided in the project PDF provided by teacher
        double expectedPrice = 14.99;

        double actualPrice = pizza.price();
        assertEquals(expectedPrice, actualPrice);
    }
    /**
     * Test Case 2
     * This tests the price method of the BYO class
     * with the smallest pizza size, and the min num of toppings,
     * and one of the extra addons
     * Sauce type does not matter in price
     */
    @Test
    void priceTest2SmallestSizeMinToppings1Addon() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        pizza.setSauce(Sauce.ALFREDO);
        pizza.setSize(Size.SMALL);
        pizza.setExtraCheese(true);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.SQUID);

        // Calculated by hand, based on prices
        // provided in the project PDF provided by teacher
        double expectedPrice = 9.99;

        double actualPrice = pizza.price();
        assertEquals(expectedPrice, actualPrice);
    }
    /**
     * Test Case 3
     * This tests the price method of the BYO class
     * with the largest pizza size, and the max num of toppings,
     * and with both extra addons. This is the most expensive BYO pizza.
     * Sauce type does not matter in price
     */
    @Test
    void priceTest3MostExpensiveBYOPizza2Addons() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        pizza.setSauce(Sauce.TOMATO);
        pizza.setSize(Size.LARGE);
        pizza.setExtraCheese(true);
        pizza.setExtraSauce(true);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.GREEN_PEPPER);

        // Calculated by hand, based on prices
        // provided in the project PDF provided by teacher
        double expectedPrice = 20.95;

        double actualPrice = pizza.price();
        assertEquals(expectedPrice, actualPrice);
    }
    /**
     * Test Case 4
     * This tests the price method of the BYO class
     * with the smallest pizza size, and the min num of toppings,
     * and no extra addons. This is the least expensive BYO pizza.
     * Sauce type does not matter in price
     */
    @Test
    void priceTest4LeastExpensiveBYOPizzaNoAddons() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        pizza.setSauce(Sauce.TOMATO);
        pizza.setSize(Size.SMALL);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.GREEN_PEPPER);

        // Calculated by hand, based on prices
        // provided in the project PDF provided by teacher
        double expectedPrice = 8.99;

        double actualPrice = pizza.price();
        assertEquals(expectedPrice, actualPrice);
    }
    /**
     * Test Case 5
     * This tests the price method of the BYO class
     * with the medium pizza size, and 5 toppings,
     * and no extra addons.
     * Sauce type does not matter in price
     */
    @Test
    void priceTest5MediumSize5ToppingsNoAddons() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        pizza.setSauce(Sauce.ALFREDO);
        pizza.setSize(Size.MEDIUM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.addTopping(Topping.PEPPERONI);

        // Calculated by hand, based on prices
        // provided in the project PDF provided by teacher
        double expectedPrice = 13.97;

        double actualPrice = pizza.price();
        assertEquals(expectedPrice, actualPrice);
    }
}