package com.example.a213_project5_stanley_and_matthew;

/**
 * This CakeDonut class provides the constructors and methods for the CakeDonut object which
 * extends from the Donut class and inherits its instance variables and methods.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class CakeDonut extends Donut {

    private final static double CAKE_DONUT_PRICE = 1.79;

    /**
     * Constructor to create an instance of the CakeDonut object.
     * @param flavor String field which holds the CakeDonut flavor, inherited from Donut class..
     * @param quantity Integer field which holds the quantity for this instance, inherited from Donut class.
     */
    public CakeDonut(String flavor, int quantity) {
        super(flavor, quantity);
        super.setTotalPrice(this.itemPrice());
    }

    /**
     * Inherited from the Donut class, calculates the item's price.
     * @return The calculated CakeDonut price based on quantity.
     */
    @Override
    public double itemPrice() {
        return CAKE_DONUT_PRICE * super.getQuantity();
    }

    /**
     * Inherited from the Donut class, determines if two CakeDonut objects are equal.
     * @param obj Object to be compared to target CakeDonut.
     * @return True if CakeDonut instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CakeDonut) {
            CakeDonut cd = (CakeDonut) obj;
            return super.equals(cd);
        }
        return false;
    }

    /**
     * Inherited from Donut, returns string representation of CakeDonut.
     * @return String representation of CakeDonut Instance.
     */
    @Override
    public String toString() {
        return "Cake Donut " + super.toString();
    }

}
