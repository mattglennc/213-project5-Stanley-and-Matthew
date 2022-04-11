package com.example.a213_project5_stanley_and_matthew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * This OrderController class provides functionality for order-view.xml, allowing
 * users to place and modify Orders.  Private helper methods are included.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class OrderController {
    private MainController mainController;

    private Order currentOrder;

    private static final Double SALESTAX = .06625;

    /**
     * References the MainController in this DonutController instance.
     *
     * @param controller MainController to be referenced in this view.
     */
    public void setMainController(MainController controller) {
        this.mainController = controller;
        this.currentOrder = this.mainController.getOrder();
        for (int i = 0; i < currentOrder.getNumItems(); i++) {
            menuItemsList.getItems().add(currentOrder.getItem(i).toString());
        }
        setCosts();
    }

    /**
     * Sets the Order costs to be displayed in the salesTax, subTotal, and totalCost text fields.
     */
    private void setCosts(){
        Double numSubtotal = this.currentOrder.finalCost();
        String price = "$" + String.format("%.2f", numSubtotal);
        subTotal.setText(price);
        Double tax = SALESTAX * numSubtotal;
        Double total = tax + numSubtotal;
        String strTax = "$" + String.format("%.2f", tax);
        String newTotal = "$" + String.format("%.2f", total);
        salesTax.setText(strTax);
        totalCost.setText(newTotal);
    }

    @FXML
    private ListView<String> menuItemsList;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField subTotal;

    @FXML
    private TextField totalCost;


    /**
     * Places order, adding it to the StoreOrders list of the MainController if there are items in menuItemsList.
     *
     * @param event When this button is clicked, add this order to the StoreOrders list.
     */
    @FXML
    void placeOrder(ActionEvent event) {
        if (currentOrder.getNumItems() == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO ITEMS ORDERED");
            alert.setHeaderText("You have 0 items ordered.");
            alert.setContentText("Order must have at least 1 item to be placed.");
            alert.showAndWait();
            return;
        }
        this.mainController.placeOrder();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Confirmed");
        alert.setHeaderText("Your order has been placed.");
        alert.setContentText("Check the store orders to view.");
        alert.showAndWait();
        menuItemsList.getItems().clear();
        salesTax.setText("$0.00");
        subTotal.setText("$0.00");
        totalCost.setText("$0.00");
        setMainController(mainController);
        return;
    }

    /**
     * Removes selected items from current Order, as long as there is an item chosen and the list is not empty.
     *
     * @param event When this button is clicked, remove item from menuItemsList.
     */
    @FXML
    void removeItems(ActionEvent event) {
        if(currentOrder.getNumItems() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO ITEMS ORDERED");
            alert.setHeaderText("You have 0 items chosen.");
            alert.setContentText("Must have at least 1 item to remove from order.");
            alert.showAndWait();
            return;
        }

        if(menuItemsList.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO ITEM SELECTED");
            alert.setHeaderText("You have not selected an item.");
            alert.setContentText("Please select an item to remove from the order.");
            alert.showAndWait();
            return;
        }
        int index = menuItemsList.getSelectionModel().getSelectedIndex();
        MenuItem remove = this.currentOrder.getMenuItem(index);
        this.currentOrder.remove(remove);
        menuItemsList.getItems().remove(index);
        setCosts();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Items Removed");
        alert.setHeaderText("Item(s) have been removed from your order.");
        alert.setContentText("Close this alert to view your updated order.");
        alert.showAndWait();
    }

}
