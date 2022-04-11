package com.example.a213_project5_stanley_and_matthew;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * This StoreOrderController class provides functionality for order-view.xml, allowing
 * users to modify the list of orders and export it to a text file.  Private helper methods are included.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class StoreOrderController {
    private MainController mainController;
    private StoreOrders orders;
    private static final Double SALESTAX = .06625;

    @FXML
    private ListView<String> orderList;

    @FXML
    private ComboBox<String> orderNumSelect;

    @FXML
    private TextField totalCost;

    /**
     * Event handler for changing the displayed order based on what order was selected
     *
     * @param event display the selected order when chosen from the drop down.
     */
    @FXML
    void showOrder(ActionEvent event) {
        String orderIndex = orderNumSelect.getSelectionModel().getSelectedItem();
        if(orderIndex != null){
            int index = Integer.parseInt(orderIndex) - 1; // arrays are indexed starting at 0 not 1 the -1 is to offset this
            Order currentOrder = this.orders.getOrder(index);
            orderList.getItems().clear(); //clear the old order
            for (int i = 0; i < currentOrder.getNumItems(); i++) {
                orderList.getItems().add(currentOrder.getItem(i).toString());
            }
            setCost(currentOrder);
        }
    }

    /**
     * Sets the Order costs to be displayed in the totalCost text field.
     *
     * @param currentOrder the order where we need to read the cost information from and display
     */
    private void setCost(Order currentOrder){
        Double numSubtotal = currentOrder.finalCost();
        Double tax = SALESTAX * numSubtotal;
        Double total = tax + numSubtotal;
        String newTotal = "$" + String.format("%.2f", total);
        totalCost.setText(newTotal);
    }

    /**
     * References the MainController in this DonutController instance.
     *
     * @param controller MainController to be referenced in this view.
     */
    public void setMainController(MainController controller) {
        this.mainController = controller;
        this.orders= this.mainController.getOrders();
        int length = this.orders.getNumOrders();
        ObservableList<String> orderList = FXCollections.observableArrayList();
        for (int i = 1; i< length+1 ; i++){
            orderList.add("" + i);
        }
        orderNumSelect.setItems(orderList);
        if(orders.getNumOrders() == 0){
            totalCost.setText("$0.00");
        }
    }

    /**
     * cancels the currently selected order
     *
     * @param event cancels the order upon clicking the cancel button.
     */
    @FXML
    void cancelOrders(ActionEvent event) {
        if (orders.getNumOrders() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO ORDERS MADE");
            alert.setHeaderText("You have made 0 orders.");
            alert.setContentText("Must have at least 1 order to cancel.");
            alert.showAndWait();
            return;
        }
        if (orderNumSelect.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO ORDERS SELECTED");
            alert.setHeaderText("No order has been selected.");
            alert.setContentText("Please select an order number to cancel.");
            alert.showAndWait();
            return;
        }
        String orderIndex = orderNumSelect.getSelectionModel().getSelectedItem();
        int index = Integer.parseInt(orderIndex) - 1; // arrays are indexed starting at 0 not 1 the -1 is to offset this
        Order currentOrder = this.orders.getOrder(index);
        orders.remove(currentOrder);
        orderList.getItems().clear();
        this.mainController.setOrders(orders);
        orderNumSelect.getSelectionModel().selectFirst();
        setMainController(mainController);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Removed");
        alert.setHeaderText("Order has been removed from your the store orders.");
        alert.setContentText("Close this alert to continue operation");
        alert.showAndWait();
    }

    /***
     * exports the currently selected order to order.txt
     *
     * @param event exports the order upon clicking the export button
     * @throws FileNotFoundException
     * @throws IOException
     */
    @FXML
    void exportOrders(ActionEvent event) throws FileNotFoundException , IOException{
        if (orders.getNumOrders() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO ORDERS MADE");
            alert.setHeaderText("You have made 0 orders.");
            alert.setContentText("Must have at least 1 order to export.");
            alert.showAndWait();
            return;
        }
        File file = new File("order.txt");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        pw.println(this.orders.print());

        pw.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Orders Exported");
        alert.setHeaderText("Order have been Exported to orders.txt.");
        alert.setContentText("Close this alert to continue operation");
        alert.showAndWait();

        return;
    }
}
