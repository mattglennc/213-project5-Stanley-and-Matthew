package com.example.a213_project5_stanley_and_matthew;

/**
 * This StoreOrders class provides the methods and constructors for the StoreOrders object, which holds
 * all orders made by the user.  It implements the Customizable interface.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class StoreOrders implements Customizable {
    private Order[] orders;
    private int numOrders;
    private static final int NOT_FOUND = -1;
    private static final int INITIAL_SIZE = 1;
    private static final int GROWTH_FACTOR = 1;

    /**
     * Constructor to create new empty StoreOrders instance.
     */
    public StoreOrders() {
        this.orders = new Order[INITIAL_SIZE];
        this.numOrders = 0;
    }

    /**
     * Gets the number of orders in StoreOrders
     *
     * @return int value of the number of orders  in Storeorders
     */
    public int getNumOrders(){
        return this.numOrders;
    }

    /**
     * Gets a specific order from orders
     *
     * @param index integer value of the index of the specfic order being requested
     * @return the order corresponding to the specified index
     */
    public Order getOrder(int index){
        return this.orders[index];
    }

    /**
     * Creates an Order array of capacity of the array + 1 and then copies over that values of that
     * Order array into the new array and sets it as the new list of Orders.
     */
    private void grow() {
        Order[] newOrders = new Order[this.orders.length + GROWTH_FACTOR];
        for (int i = 0; i < this.orders.length; i++) {
            newOrders[i] = this.orders[i];
        }
        this.orders = newOrders;
    }

    /**
     * Implemented from the Customizable interface, adds an Order to the list of Orders for this StoreOrders instance.
     *
     * @param obj Order object to be added to list of Orders.
     * @return True if Order was successfully added to list, false otherwise.
     */
    public boolean add(Object obj) {
        if (this.numOrders == this.orders.length) {
            grow();
        }
        if (obj instanceof Order) {
            Order o = (Order) obj;
            for (int i = 0; i < this.orders.length; i++) {
                if (this.orders[i] == null) {
                    this.orders[i] = o;
                    this.numOrders = this.numOrders + 1;
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Implemented from the Customizable interface, removes an Order from the list of Orders for this StoreOrders instance.
     *
     * @param obj Order object to be removed from list of Orders.
     * @return True if Order was successfully removed from list, false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            Order order = (Order) obj;
            int deletionIndex = -1;
            int currentArrIndex = 0;
            Order[] newOrders = new Order[this.orders.length];
            for (int i = 0; i < this.orders.length; i++) {
                if (order.equals(this.orders[i])) {
                    deletionIndex = i;
                }
            }
            if (deletionIndex == -1) {
                return false;
            }
            for (int j = 0; j < this.orders.length; j++) {
                if (j != deletionIndex) {
                    newOrders[currentArrIndex] = this.orders[j];
                    currentArrIndex = currentArrIndex + 1;
                }
            }
            this.orders = newOrders;
            this.numOrders = this.numOrders - 1;
            return true;
        }
        return false;
    }

    /**
     * Prints all the Orders in the StoreOrders with all details included by order number.
     *
     * @return Final formatted String of Orders to be exported by user.
     */
    public String print() {
        String result = "";
        for (int i = 0; i < this.numOrders; i++) {
            result = result + ("ORDER #" + (i + 1) + '\n');
            result = result + ("--------------" + '\n');
            result = result + this.orders[i].print() + '\n';
            System.out.println(" ");
        }
        return result;
    }

}
