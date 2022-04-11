package com.example.a213_project5_stanley_and_matthew;

/**
 * This MenuItem class provides the data fields, constructors and methods to be inherited
 * and implemented by subclasses of MenuItem.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class MenuItem {

    private int quantity;
    private double totalPrice;

    /**
     * Constructor to create instance of MenuItem class.
     * @param quantity Integer quantity field associated with this item.
     */
    public MenuItem(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the item's price.  Because the MenuItem class is an unspecified item on
     * it only returns 0.
     * @return The calculated MenuItem price based on quantity.
     */
    public double itemPrice() {
        return 0;
    }

    /**
     * Changes the quantity and price of MenuItem instance by entered amount.
     * @param updateQuantity Amount by which to change MenuItem quantity.
     */
    public void update(int updateQuantity) {
        this.totalPrice += updateQuantity * (this.totalPrice / quantity);
        this.quantity += updateQuantity;
    }

    /**
     * Returns Integer quantity of MenuItem instance.
     * @return Quantity of MenuItem instance.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Setter method to update the total item price.
     * @param price New price to update this MenuItem with.
     */
    public void setTotalPrice(double price){
        this.totalPrice = price;
    }

    /**
     * Getter method to retrieve the total price for this MenuItem accounting for quantity.
     * @return Total price for this MenuItem.
     */
    public double getTotalPrice(){
        return this.totalPrice;
    }


    /**
     * Returns string representation of MenuItem.
     * @return String representation of MenuItem Instance.
     */
    @Override
    public String toString() {
        return " (" + this.quantity + ")";
    }

}
