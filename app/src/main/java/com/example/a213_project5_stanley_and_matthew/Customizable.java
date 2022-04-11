package com.example.a213_project5_stanley_and_matthew;

/**
 * This Customizable interface holds the methods to add and remove items from a list, which are implemented by various
 * classes in this program.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public interface Customizable {

    /**
     * Adds an object to the list.
     * @param obj Object to be added to list.
     * @return True if object is successfully added to list, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Adds an object to the list.
     * @param obj Object to be removed from list.
     * @return True if object is successfully removed from list, false otherwise.
     */
    boolean remove(Object obj);
}
