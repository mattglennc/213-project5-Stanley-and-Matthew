package com.example.a213_project5_stanley_and_matthew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This MainController class holds StoreOrders and Order and acts as a way to interact with it using the JavaFX GUI, and
 * provides functionality for the menu of the RU CAFE.  Private helper methods are included.
 * initialize() creates the StoreOrders and Order and starts its operation.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class MainController {
    private StoreOrders orders;
    private Order currentOrder;

    /**
     * Getter method that returns the StoreOrders
     *
     * @return The StoreOrders list of orders for this MainController.
     */
    public StoreOrders getOrders() {
        return this.orders;
    }

    /**
     * Setter method for the currentOrder of this MainController.
     *
     * @param newOrders New order which will replace the old one.
     */
    public void setOrders(StoreOrders newOrders) {
        this.orders = newOrders;
        return;
    }

    /**
     * Places currentOrder, adding it to the StoreOrders list.
     */
    public void placeOrder() {
        this.orders.add(currentOrder);
        currentOrder = new Order();
        return;
    }

    /**
     * Getter method to return the currentOrder of this MainController.
     *
     * @return The current Order of this Controller.
     */
    public Order getOrder() {
        return this.currentOrder;
    }

    /**
     * Setter method for the currentOrder of this MainController.
     *
     * @param newOrder New order which will replace the old one.
     */
    public void setOrder(Order newOrder) {
        this.currentOrder = newOrder;
        return;
    }

    /**
     * Setter method for the orders of this MainController.
     *
     * @param remOrders Order to remove from store orders
     */
    public void removeOrder(Order remOrders) {
        StoreOrders orders = this.orders;
        orders.remove(remOrders);
        return;
    }

    /**
     * Initializes the StoreOrders and Order instances of this controller as new instances.
     */
    public void initialize() {
        this.orders = new StoreOrders();
        this.currentOrder = new Order();
    }

    /**
     * Event handler which opens the coffee-view window and references MainController in CoffeeController.
     *
     * @param event When this button is clicked, open coffee-view.
     * @throws IOException
     */
    @FXML
    void coffeeView(ActionEvent event) throws IOException {
        FXMLLoader coffeeLoader = new FXMLLoader(Main.class.getResource("coffee-view.fxml"));
        Scene coffeeScene = new Scene((VBox) coffeeLoader.load(), 604, 549);
        Stage coffeeStage = new Stage();
        coffeeStage.setTitle("Coffee");
        coffeeStage.setScene(coffeeScene);
        coffeeStage.show();
        CoffeeController CoffeeController = coffeeLoader.getController();
        CoffeeController.setMainController(this);
    }

    /**
     * Event handler which opens the donut-view window and references MainController in DonutController.
     *
     * @param event When this button is clicked, open donut-view.
     * @throws IOException
     */
    @FXML
    void donutsView(ActionEvent event) throws IOException {
        FXMLLoader donutLoader = new FXMLLoader(Main.class.getResource("donut-view.fxml"));
        Scene donutScene = new Scene((VBox) donutLoader.load(), 604, 549);
        Stage donutStage = new Stage();
        donutStage.setTitle("Donuts");
        donutStage.setScene(donutScene);
        donutStage.show();
        DonutController donutController = donutLoader.getController();
        donutController.setMainController(this);
    }

    /**
     * Event handler which opens the order-view window and references MainController in OrderController.
     *
     * @param event When this button is clicked, open order-view.
     * @throws IOException
     */
    @FXML
    void orderingView(ActionEvent event) throws IOException {
        FXMLLoader orderLoader = new FXMLLoader(Main.class.getResource("order-view.fxml"));
        Scene orderScene = new Scene((VBox) orderLoader.load(), 604, 549);
        Stage orderStage = new Stage();
        orderStage.setTitle("Your Order");
        orderStage.setScene(orderScene);
        orderStage.show();
        OrderController orderController = orderLoader.getController();
        orderController.setMainController(this);
    }

    /**
     * Event handler which opens the storeOrders-view window and references MainController in StoreOrderController.
     *
     * @param event When this button is clicked, open storeOrders-view.
     * @throws IOException
     */
    @FXML
    void storeOrdersView(ActionEvent event) throws IOException {
        FXMLLoader storeLoader = new FXMLLoader(Main.class.getResource("storeOrders-view.fxml"));
        Scene storeScene = new Scene((VBox) storeLoader.load(), 604, 549);
        Stage storeStage = new Stage();
        storeStage.setTitle("Store Orders");
        storeStage.setScene(storeScene);
        storeStage.show();
        StoreOrderController storeOrdersController = storeLoader.getController();
        storeOrdersController.setMainController(this);
    }

}
