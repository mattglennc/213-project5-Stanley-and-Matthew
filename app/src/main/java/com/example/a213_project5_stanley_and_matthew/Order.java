package com.example.a213_project5_stanley_and_matthew;

import java.text.DecimalFormat;

/**
 * This Order class provides the methods and constructors for the Order object, which holds
 * all menu items for an order.  It implements the Customizable interface.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class Order implements Customizable {

    private MenuItem[] menuItems;
    private int numItems;
    private static final int NOT_FOUND = -1;
    private static final int INITIAL_SIZE = 1;
    private static final int GROWTH_FACTOR = 1;

    /**
     * Constructor to create new empty Order instance.
     */
    public Order() {
        this.menuItems = new MenuItem[INITIAL_SIZE];
        this.numItems = 0;
    }

    /**
     * Takes a MenuItem and returns its index in the Order, returns NOT_FOUND if not found.
     * @param item - the account that needs to be found in the Order returning its index
     * @return Index of found item, NOT_FOUND otherwise.
     */
    private int find(MenuItem item) {
        for (int i = 0; i < this.menuItems.length; i++) {
            if (item.equals(this.menuItems[i])) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    /**
     * Creates an Order of capacity of the Order + 1 and then copies over that MenuItems of that
     * MenuItem array into the new array and sets it as the new Order.
     */
    private void grow() {
        MenuItem[] newItems = new MenuItem[this.menuItems.length + GROWTH_FACTOR];
        for (int i = 0; i < this.menuItems.length; i++) {
            newItems[i] = this.menuItems[i];
        }
        this.menuItems = newItems;
    }

    /**
     * Implemented from the Customizable interface, adds a MenuItem to this Order instance.
     * @param obj MenuItem object to be added to list of MenuItems.
     * @return True if MenuItem was successfully added to list, false otherwise.
     */
    public boolean add(Object obj) {
        if (this.numItems == this.menuItems.length) {
            grow();
        }
        if (obj instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) obj;
            if (find(menuItem) != NOT_FOUND) {
                int found = find(menuItem);
                this.menuItems[found].update(menuItem.getQuantity());
                return true;
            } else {
                for (int i = 0; i < this.menuItems.length; i++) {
                    if (this.menuItems[i] == null) {
                        this.menuItems[i] = menuItem;
                        this.numItems = this.numItems + 1;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Getter method that returns MenuItem at a specified index.
     * @param index Index of MenuItem to be retrieved.
     * @return MenuItem of specified index.
     */
    public MenuItem getMenuItem(int index){
        return this.menuItems[index];
    }

    /**
     * Implemented from the Customizable interface, removes a MenuItem from the Order
     * @param obj MenuItem object to be removed from list of MenuItems
     * @return True if MenuItem was successfully removed from list, false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) obj;
            int deletionIndex = -1;
            int currentArrIndex = 0;
            for (int i = 0; i < this.menuItems.length; i++) {
                if (menuItem.equals(this.menuItems[i])) {
                    deletionIndex = i;
                }
            }
            if (deletionIndex == -1) {
                return false;
            }
            if (menuItem.getQuantity() < this.menuItems[deletionIndex].getQuantity()) {
                this.menuItems[deletionIndex].update(-1 * menuItem.getQuantity());
                return true;
            }
            MenuItem[] newItems = new MenuItem[this.menuItems.length];
            for (int j = 0; j < this.menuItems.length; j++) {
                if (j != deletionIndex) {
                    newItems[currentArrIndex] = this.menuItems[j];
                    currentArrIndex = currentArrIndex + 1;
                }
            }
            this.menuItems = newItems;
            this.numItems = this.numItems - 1;
            return true;
        }
        return false;
    }

    /**
     * Determines if two Order objects are equal.
     * @param obj Object to be compared to target Order.
     * @return True if Order instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            Order o = (Order) obj;
            boolean sameLength = o.numItems == this.numItems;
            if (!sameLength) {
                return false;
            }

            for (int i = 0; i < this.menuItems.length; i++) {
                if (o.find(this.menuItems[i]) == NOT_FOUND) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Calculates total cost of Order instance and returns it.
     * @return Calculated total cost of Order.
     */
    public double finalCost() {
        double cost = 0;
        for (int i = 0; i < this.numItems; i++) {
            cost += (this.menuItems[i].itemPrice());
        }
        return cost;
    }

    /**
     * Getter method which returns number of items in Order.
     * @return Number of items in Order
     */
    public int getNumItems() {
        return this.numItems;
    }

    /**
     * Getter method which returns MenuItem from specified index.
     * @param index Index of MenuItem to be retrieved.
     * @return MenuItem at specified index.
     */
    public MenuItem getItem(int index) {
        return this.menuItems[index];
    }

    /**
     * Prints out every item held in this instance of Order, including all details.
     * @return String representation of all items in order, along with total cost.
     */
    public String print() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String result = "";
        for (int i = 0; i < this.numItems; i++) {
            result = result + (this.menuItems[i].toString() + '\n');
        }
        result = result + ("--------------\n");
        result = result + ("TOTAL COST: $" + decimalFormat.format(finalCost()) + '\n');
        return result;
    }


}
