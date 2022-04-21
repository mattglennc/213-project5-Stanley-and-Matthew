package com.example.a213_project5_stanley_and_matthew;

/**
 * This Donut class provides the data fields, constructors and methods to be inherited
 * and implemented by subclasses.  It also inherits from the MenuItem class.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class Donut extends MenuItem {

    private String flavor;

    /**
     * Constructor to create an instance of the Donut object.
     * @param flavor String field which holds the Donut flavor.
     * @param quantity Integer field which holds the quantity for this instance, inherited from the MenuItem class.
     * @param price Double field which holds the individual price of this instance.
     */
    public Donut(String flavor, int quantity, double price) {
        super(quantity);
        this.flavor = flavor;
        super.setTotalPrice(price);
    }

    /**
     * Calculates the item's price, inherited from the MenuItem class.  Because the Donut class is not a specified type
     * it only returns 0.
     * @return The calculated Donut price based on quantity.
     */
    @Override
    public double itemPrice() {
        return super.getTotalPrice();
    }

    /**
     * Determines if two Donut objects are equal.
     * @param obj Object to be compared to target Donut.
     * @return True if Donut instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut) {
            Donut d = (Donut) obj;
            return d.flavor.equals(this.flavor);
        }
        return false;
    }

    /**
     * Inherited from MenuItem, returns string representation of Donut.
     * @return String representation of Donut Instance.
     */
    @Override
    public String toString() {
        return this.flavor + " " + super.toString();
    }
}
