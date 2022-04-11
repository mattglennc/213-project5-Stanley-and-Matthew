package com.example.a213_project5_stanley_and_matthew;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * This CoffeeController class provides functionality for coffee-view.xml, allowing
 * users to place orders of Coffee.  Private helper methods are included.
 * initialize() creates the StoreOrders and Order and starts its operation.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class CoffeeController {

    private Coffee coffee;

    private MainController mainController;

    /**
     * Initializes all data fields as well as the current Coffee Order to new instances.
     */
    public void initialize() {
        ObservableList<String> sizes = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        sizeSelect.getItems().addAll(sizes);
        ObservableList<String> quantities = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        quantitiesSelect.getItems().addAll(quantities);
        reInitialize();

    }

    /**
     * Initializes all data fields that can be reinitialized after an order is made.
     */
    public void reInitialize(){
        sizeSelect.getSelectionModel().selectFirst();
        quantitiesSelect.getSelectionModel().selectFirst();
        String size = sizeSelect.getValue();
        String quantity = quantitiesSelect.getValue();
        int quant = Integer.parseInt(quantity);
        coffee = new Coffee(quant, size);
        subTotal.setText(coffee.priceString());
        resetBoxes();
    }

    /**
     * References the MainController in this CoffeeController instance.
     *
     * @param controller MainController to be referenced in this view.
     */
    public void setMainController(MainController controller) {
        mainController = controller;
    }

    /**
     * Unchecks all checkboxes in this view.
     */
    private void resetBoxes(){
        caramelBox.setSelected(false);
        wcBox.setSelected(false);
        milkBox.setSelected(false);
        creamBox.setSelected(false);
        syrupBox.setSelected(false);
    }

    @FXML
    private CheckBox caramelBox;

    @FXML
    private CheckBox creamBox;

    @FXML
    private CheckBox milkBox;

    @FXML
    private ComboBox<String> quantitiesSelect;

    @FXML
    private ComboBox<String> sizeSelect;

    @FXML
    private TextField subTotal;

    @FXML
    private CheckBox syrupBox;

    @FXML
    private CheckBox wcBox;

    /**
     * Event handler for adding or removing Caramel add-in from current coffee order.
     *
     * @param event Add Caramel if checked, remove if unchecked.
     */
    @FXML
    void caramelCheck(ActionEvent event) {
        if(caramelBox.isSelected()){
            coffee.add("Caramel");
        }else{
            coffee.remove("Caramel");
        }
        subTotal.setText(coffee.priceString());
    }

    /**
     * Event handler for adding or removing Cream add-in from current coffee order.
     *
     * @param event Add Cream if checked, remove if unchecked.
     */
    @FXML
    void creamCheck(ActionEvent event) {
        if(creamBox.isSelected()){
            coffee.add("Cream");
        }else{
            coffee.remove("Cream");
        }
        subTotal.setText(coffee.priceString());
    }

    /**
     * Event handler for adding or removing Milk add-in from current coffee order.
     *
     * @param event Add Milk if checked, remove if unchecked.
     */
    @FXML
    void milkCheck(ActionEvent event) {
        if(milkBox.isSelected()){
            coffee.add("Milk");
        }else{
            coffee.remove("Milk");
        }
        subTotal.setText(coffee.priceString());
    }

    /**
     * Event handler for setting size of the current coffee order.
     *
     * @param event  Sets size of current coffee order to value of sizeSelect.
     */
    @FXML
    void sizeChosen(ActionEvent event) {
        String size = sizeSelect.getSelectionModel().getSelectedItem();
        coffee.setSize(size);
        subTotal.setText(coffee.priceString());
    }

    /**
     * Event handler for setting quantity of current coffee order.
     *
     * @param event Sets quantity of current coffee order with value of quantitiesSelect.
     */
    @FXML
    void quantChosen(ActionEvent event) {
        int quant = Integer.parseInt(quantitiesSelect.getValue());
        coffee.update(quant - coffee.getQuantity());
        subTotal.setText(coffee.priceString());

    }

    /**
     * Event handler for adding or removing Syrup add-in from current coffee order.
     *
     * @param event Add Syrup if checked, remove if unchecked.
     */
    @FXML
    void syrupCheck(ActionEvent event) {
        if(syrupBox.isSelected()){
            coffee.add("Syrup");
        }else{
            coffee.remove("Syrup");
        }
        subTotal.setText(coffee.priceString());
    }

    /**
     * Event handler for adding or removing Whipped Cream add-in from current coffee order.
     *
     * @param event Add Whipped Cream if checked, remove if unchecked.
     */
    @FXML
    void wcCheck(ActionEvent event) {
        if(wcBox.isSelected()){
            coffee.add("Whipped Cream");
        }else{
            coffee.remove("Whipped Cream");
        }
        subTotal.setText(coffee.priceString());
    }

    /**
     * Event handler to add the current coffee order to the currentOrder in the MainController.
     *
     * @param event When this button is clicked, add current coffee order to currentOrder in MainController.
     */
    @FXML
    void orderCoffee(ActionEvent event) {
        Coffee tempCoffee = new Coffee(coffee);
        Order order = this.mainController.getOrder();
        this.mainController.setOrder(order);
        order.add(tempCoffee);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("Item has been added to your order.");
        alert.setContentText("Please check your order to view.");
        alert.showAndWait();
        reInitialize();
        setMainController(this.mainController);
    }

}

