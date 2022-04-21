package com.example.a213_project5_stanley_and_matthew;

/**
 * This Coffee class provides the data fields, constructors and methods for the Coffee object.
 * It also inherits from the MenuItem class and implements the Customizable interface.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class Coffee extends MenuItem implements Customizable {

    private int size;
    private String sizeString;
    private final static double SHORT_PRICE = 1.69;
    private final static double SIZE_INCREASE = 0.40;
    private final static double ADD_ON = 0.30;
    private String[] addIns;
    private int numAddIns;
    private final static int GRANDE = 2;
    private final static int VENTI = 3;
    private static final int NOT_FOUND = -1;
    private static final int INITIAL_SIZE = 0;
    private static final int GROWTH_FACTOR = 1;

    /**
     * Constructor to create an instance of the Coffee class.
     * @param quantity Integer field which holds quantity of instance.  Inherited from MenuItem class.
     * @param size String field which holds the size of instance.
     */
    public Coffee(int quantity, String size) {
        super(quantity);
        this.size = getSizeInt(size);
        this.sizeString = size;
        this.addIns = new String[INITIAL_SIZE];
        this.numAddIns = 0;
        super.setTotalPrice(this.itemPrice());
    }

    /**
     * Copy constructor to duplicate a Coffee object into a new instance.
     * @param coffee Coffee object to be copied.
     */
    public Coffee(Coffee coffee){
        super(coffee.getQuantity());
        this.sizeString = coffee.sizeString;
        this.size = getSizeInt(this.sizeString);
        this.addIns = coffee.addIns;
        this.numAddIns = coffee.numAddIns;
        super.setTotalPrice(this.itemPrice());

    }

    /**
     * Setter method for the Integer field for size based on the size String.
     * @param size String representation of size used to determine Integer size.
     */
    public void setSize(String size){
        this.size = getSizeInt(size);
        this.sizeString = size;
        super.setTotalPrice(this.itemPrice());
    }



    /**
     * Getter method for the String representation of the Coffee price.
     * @return String representation of the price of this instance.
     */
    public String priceString(){
        return "$" + String.format("%.2f", this.itemPrice());
    }

    /**
     * Helper getter method for determining the Integer representation of the size String.
     * @param size String size to be considered.
     * @return Integer corresponding to String size. (0,1,2,3)
     */
    private int getSizeInt(String size){
        if(size.equals("Short")){
            return 0;
        }
        else if (size.equals("Tall")){
            return 1;
        }
        else if (size.equals("Grande")){
            return GRANDE;
        }
        else if (size.equals("Venti")){
            return VENTI;
        }
        return 0;
    }

    /**
     * Implemented from the Customizable interface, adds a String add-in to the list of add-ins for this Coffee instance.
     * @param obj Add-In object to be added to list of add-ins.
     * @return True if add-in was successfully added to list, false otherwise.
     */
    public boolean add(Object obj) {
        if (this.numAddIns == this.addIns.length) {
            grow();
        }
        if (obj instanceof String) {
            String addIn = (String) obj;
            if (find(addIn) != NOT_FOUND) {
                return false;
            } else {
                for (int i = 0; i < this.addIns.length; i++) {
                    if (this.addIns[i] == null) {
                        this.addIns[i] = addIn;
                        this.numAddIns = this.numAddIns + 1;
                        super.setTotalPrice(super.getTotalPrice() + ADD_ON * this.getQuantity());
                        return true;
                    }
                }
            }
            return false;
        }

        return false;
    }

    /**
     * Implemented from the Customizable interface, removes a String add-in from the list of add-ins for this Coffee instance.
     * @param obj Add-In object to be removed from list of add-ins.
     * @return True if add-in was successfully removed from list, false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof String) {
            String addIn = (String) obj;
            int deletionIndex = -1;
            int currentArrIndex = 0;
            String[] newAddIns = new String[this.addIns.length - GROWTH_FACTOR];
            for (int i = 0; i < this.addIns.length; i++) {
                if (addIn.equals(this.addIns[i])) {
                    deletionIndex = i;
                }
            }
            if (deletionIndex == -1) {
                return false;
            }
            for (int j = 0; j < this.addIns.length; j++) {
                if (j != deletionIndex) {
                    newAddIns[currentArrIndex] = this.addIns[j];
                    currentArrIndex = currentArrIndex + 1;
                }
            }
            this.addIns = newAddIns;
            this.numAddIns = this.numAddIns - 1;
            return true;
        }
        return false;
    }

    /**
     * Creates an add-ins array of capacity of the array + 1 and then copies over that values of that
     * add-ins array into the new array and sets it as the new list of add-ins.
     */
    private void grow() {
        String[] newAddIns = new String[this.addIns.length + GROWTH_FACTOR];
        for (int i = 0; i < this.addIns.length; i++) {
            newAddIns[i] = this.addIns[i];
        }
        this.addIns = newAddIns;
    }

    /**
     * Inherited from the MenuItem class.  Calculates Coffee price based on size, quantity, and add-ins.
     * @return The calculated Coffee price.
     */
    @Override
    public double itemPrice() {
        double resultPrice = SHORT_PRICE;
        resultPrice += this.size * SIZE_INCREASE + ADD_ON * numAddIns;
        return resultPrice * super.getQuantity();
    }

    /**
     * Takes an add-in and returns its index in the add-ins list, returns NOT_FOUND if not found.
     * @param addIn - the add-in String that needs to be found in the list returning its index
     * @return Index of add-in if found, false otherwise.
     */
    private int find(String addIn) {
        for (int i = 0; i < this.addIns.length; i++) {
            if (addIn.equals(this.addIns[i])) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    /**
     * Determines if two Coffee objects are equal.
     * @param obj Object to be compared to target Coffee.
     * @return True if Coffee instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coffee) {
            Coffee c = (Coffee) obj;
            boolean sameSize = c.size == this.size;
            boolean sameLength = c.numAddIns == this.numAddIns;
            if (!sameLength) {
                return false;
            }
            for (int i = 0; i < this.addIns.length; i++) {
                if (c.find(this.addIns[i]) == NOT_FOUND) {
                    return false;
                }
            }
            return sameSize;
        }
        return false;
    }

    /**
     * Inherited from MenuItem, returns string representation of Coffee.
     * @return String representation of Coffee Instance.
     */
    @Override
    public String toString() {
        String result = "Coffee ";
        String sizeString = this.sizeString + " ";
        String addInsString = "(";
        if (this.numAddIns == 0) {
            addInsString = " ";
        } else {
            for (int i = 0; i < addIns.length; i++) {
                if (i == addIns.length - 1) {
                    addInsString = addInsString + addIns[i] + ") ";
                } else {
                    addInsString = addInsString + addIns[i] + ", ";
                }
            }
        }
        return sizeString + result + addInsString + super.toString();
    }

}
