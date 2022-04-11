package com.example.a213_project5_stanley_and_matthew;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * This DonutController class provides functionality for donut-view.xml, allowing
 * users to place orders of Donut.  Private helper methods are included.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class DonutController {
    private static final ObservableList<String> YEASTFLAVORS = FXCollections.observableArrayList("jelly", "glazed", "chocolate frosted", "strawberry frosted", "sugar", "lemon filled");
    private static final ObservableList<String> CAKEFLAVORS = FXCollections.observableArrayList("cinnamon sugar", "old fashion", "blueberry");
    private static final ObservableList<String> HOLEFLAVORS = FXCollections.observableArrayList("glazed holes", "jelly holes", "cinnamon sugar holes");

    private MainController mainController;

    private Order donutsOrdered;

    @FXML
    private ComboBox<String> donutSelect = new ComboBox<>();

    @FXML
    private ListView<String> donutList;

    @FXML
    private ListView<String> flavorList;

    @FXML
    private ComboBox<String> quantitySelect;

    @FXML
    private TextField subTotalText;

    /**
     * Initializes all data fields as well as the current Donut Order to new instances.
     */
    public void initialize() {
        ObservableList<String> donuts = FXCollections.observableArrayList("Cake Donut", "Donut Hole", "Yeast Donut");
        donutSelect.getItems().addAll(donuts);
        ObservableList<String> quantities = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        quantitySelect.getItems().addAll(quantities);
        donutsOrdered = new Order();
    }

    /**
     * References the MainController in this DonutController instance.
     *
     * @param controller MainController to be referenced in this view.
     */
    public void setMainController(MainController controller) {
        mainController = controller;
    }

    /**
     * Event handler that determines which flavor choices to display based on donut type.
     *
     * @param event When donut type is selected, display corresponding flavors in flavorList.
     */
    @FXML
    void donutChosen(ActionEvent event) {
        if (donutSelect.getSelectionModel().getSelectedItem().equals("Yeast Donut")) {
            flavorList.setItems(YEASTFLAVORS);
        } else if (donutSelect.getSelectionModel().getSelectedItem().equals("Cake Donut")) {
            flavorList.setItems(CAKEFLAVORS);
        } else {
            flavorList.setItems(HOLEFLAVORS);
        }
    }

    /**
     * Display Alert pop-up if user attempts to add donuts to list and information is missing.
     *
     * @return True if information is missing, false otherwise.
     */
    private boolean missingInfo() {
        if (quantitySelect.getValue() == null || flavorList.getSelectionModel().getSelectedItem() == null || donutSelect.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("MISSING INFO");
            alert.setHeaderText("One or more fields are empty.");
            alert.setContentText("Make sure all fields are filled before adding to order.");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    /**
     * Adds selected Donut choice to donutList if not information is missing.
     *
     * @param event When button is clicked, add selected Donut to donutList and display it, updating price displayed.
     */
    @FXML
    void addDonuts(ActionEvent event) {
        String SelectedDonut = flavorList.getSelectionModel().getSelectedItem();
        String quantity = quantitySelect.getValue();
        String newTotal = subTotalText.getText();
        if (missingInfo()) {
            return;
        }
        int numQuantity = Integer.parseInt(quantity);
        int numDonuts = donutsOrdered.getNumItems();
        Donut d;
        if (donutSelect.getSelectionModel().getSelectedItem().equals("Yeast Donut")) {
            d = new YeastDonut(SelectedDonut, numQuantity);
            donutsOrdered.add(d);
        } else if (donutSelect.getSelectionModel().getSelectedItem().equals("Cake Donut")) {
            d = new CakeDonut(SelectedDonut, numQuantity);
            donutsOrdered.add(d);
        } else {
            d = new DonutHole(SelectedDonut, numQuantity);
            donutsOrdered.add(d);
        }
        if (numDonuts != donutsOrdered.getNumItems()) {
            String addToOrder = d.toSubString();
            donutList.getItems().add(addToOrder);
        } else {
            donutList.getItems().clear();
            for (int i = 0; i < donutsOrdered.getNumItems(); i++) {
                donutList.getItems().add(((Donut) donutsOrdered.getItem(i)).toSubString());
            }
        }
        double finalTotal = donutsOrdered.finalCost();
        newTotal = "$" + String.format("%.2f", finalTotal);
        subTotalText.setText(newTotal);
    }

    /**
     * Adds selected Donuts to current Order.
     * @param event When button is clicked, add selected Donuts to current Order.
     */
    @FXML
    void orderDonuts(ActionEvent event) {
        if (donutsOrdered.getNumItems() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO DONUTS ORDERED");
            alert.setHeaderText("You have 0 items chosen.");
            alert.setContentText("Please add at least 1 item to order.");
            alert.showAndWait();
            return;
        }
        Order order = this.mainController.getOrder();
        for (int i = 0; i < donutsOrdered.getNumItems(); i++) {
            order.add(donutsOrdered.getItem(i));
        }
        this.mainController.setOrder(order);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Confirmed");
        alert.setHeaderText("Item(s) added to order.");
        alert.setContentText("Please check your order to view.");
        alert.showAndWait();
    }

    /**
     * Removes selected donut from DonutList.
     * @param event When button is clicked, remove selected donuts from DonutList, updating displayed price.
     */
    @FXML
    void removeDonuts(ActionEvent event) {
        if (donutsOrdered.getNumItems() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO DONUTS ORDERED");
            alert.setHeaderText("You have 0 items chosen.");
            alert.setContentText("Must have at least 1 item to remove from order.");
            alert.showAndWait();
            return;
        }

        if (donutList.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO ITEM SELECTED");
            alert.setHeaderText("You have not selected an item.");
            alert.setContentText("Please select an item to remove from the order.");
            alert.showAndWait();
            return;
        }
        int index = donutList.getSelectionModel().getSelectedIndex();
        donutList.getItems().remove(index);
        Donut removeDonut = (Donut) donutsOrdered.getItem(index);
        donutsOrdered.remove(removeDonut);
        double finalTotal = donutsOrdered.finalCost();
        String newTotal = "$" + String.format("%.2f", finalTotal);
        subTotalText.setText(newTotal);
    }

}
