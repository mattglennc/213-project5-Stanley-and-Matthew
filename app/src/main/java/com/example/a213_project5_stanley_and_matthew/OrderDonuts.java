package com.example.a213_project5_stanley_and_matthew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * This OrderDonuts class provides functionality for activity_donut.xml, allowing
 * users to select donuts to add to their order.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */

public class OrderDonuts extends AppCompatActivity {
    private ArrayList<Item> items = new ArrayList<>();


    private int [] itemImages = {R.drawable.jelly, R.drawable.glazed, R.drawable.chocolate,
            R.drawable.strawberry, R.drawable.sugar, R.drawable.lemonfilled, R.drawable.bostoncream,
            R.drawable.cinnamon,R.drawable.blueberry,R.drawable.oldfashion,
            R.drawable.glazedholes,R.drawable.cinnamonholes,R.drawable.jellyholes};

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     *
     * @param savedInstanceState saved state information of the model
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        ItemsAdapter adapter = new ItemsAdapter(this, items); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        String [] itemPrices = getResources().getStringArray(R.array.itemPrices);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i], itemPrices[i]));
        }
    }
}
