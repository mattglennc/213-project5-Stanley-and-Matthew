package com.example.a213_project5_stanley_and_matthew;

/**
 * This YeastDonut class provides the constructors and methods for the YeastDonut object which
 * extends from the Donut class and inherits its instance variables and methods.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class YeastDonut extends Donut {

    private final static double YEAST_DONUT_PRICE = 1.59;

    /**
     * Constructor to create an instance of the YeastDonut object.
     * @param flavor String field which holds the YeastDonut flavor, inherited from Donut class.
     * @param quantity Integer field which holds the quantity for this instance, inherited from Donut class.
     */
    public YeastDonut(String flavor, int quantity) {
        super(flavor, quantity);
        super.setTotalPrice(this.itemPrice());
    }

    /**
     * Inherited from the Donut class, calculates the item's price.
     * @return The calculated YeastDonut price based on quantity.
     */
    @Override
    public double itemPrice() {
        return YEAST_DONUT_PRICE * super.getQuantity();
    }

    /**
     * Inherited from the Donut class, determines if two YeastDonut objects are equal.
     * @param obj Object to be compared to target YeastDonut.
     * @return True if YeastDonut instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof YeastDonut) {
            YeastDonut yd = (YeastDonut) obj;
            return super.equals(yd);
        }
        return false;
    }

    /**
     * Inherited from Donut, returns string representation of YeastDonut.
     * @return String representation of YeastDonut Instance.
     */
    @Override
    public String toString() {
        return "Yeast Donut " + super.toString();
    }


}
