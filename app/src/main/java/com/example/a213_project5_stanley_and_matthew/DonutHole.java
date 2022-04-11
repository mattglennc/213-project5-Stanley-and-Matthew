package com.example.a213_project5_stanley_and_matthew;

/**
 * This DonutHole class provides the constructors and methods for the DonutHole object which
 * extends from the Donut class and inherits its instance variables and methods.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class DonutHole extends Donut {

    private final static double DONUT_HOLE_PRICE = 0.39;

    /**
     * Constructor to create an instance of the DonutHole object.
     * @param flavor String field which holds the DonutHole flavor, inherited from Donut class.
     * @param quantity Integer field which holds the quantity for this instance, inherited from Donut class.
     */
    public DonutHole(String flavor, int quantity) {
        super(flavor, quantity);
        super.setTotalPrice(this.itemPrice());
    }

    /**
     * Inherited from the Donut class, calculates the item's price.
     * @return The calculated DonutHole price based on quantity.
     */
    @Override
    public double itemPrice() {
        return DONUT_HOLE_PRICE * super.getQuantity();
    }

    /**
     * Inherited from the Donut class, determines if two DonutHole objects are equal.
     * @param obj Object to be compared to target DonutHole.
     * @return True if DonutHole instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DonutHole) {
            DonutHole dh = (DonutHole) obj;
            return super.equals(dh);
        }
        return false;
    }

    /**
     * Inherited from Donut, returns string representation of DonutHole.
     * @return String representation of DonutHole Instance.
     */
    @Override
    public String toString() {
        return "Donut Hole " + super.toString();
    }


}
