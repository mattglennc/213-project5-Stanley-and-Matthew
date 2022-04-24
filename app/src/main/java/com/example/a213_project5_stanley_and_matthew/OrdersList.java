package com.example.a213_project5_stanley_and_matthew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * This OrdersList class provides functionality for activity_store.xml, allowing
 * users to remove orders from the store and see their cost and contents
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class OrdersList extends AppCompatActivity {

    private ListView ordersList;
    private ArrayList<String> orders;
    private StoreOrders allOrders;
    private static final DecimalFormat format = new DecimalFormat("0.00");

    /**
     * Get the references of all instances of Views defined in the layout file, retrieves the StoreOrders orders object
     * from MainActivity and displays all the orders in the allOrders Listview, removes order from alloOders on click and
     * displays the cost of every order
     *
     * @param savedInstanceState saved state information of the model
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ordersList = findViewById(R.id.ordersList);
        this.allOrders = MainActivity.orders;
        this.orders = setOrders();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  this.orders);
        ordersList.setAdapter(adapter);
        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle(getResources().getString(R.string.removeOrder));
                alert.setMessage(allOrders.getOrder(position).print());
                //handle the "YES" click
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getResources().getString(R.string.orderRemoved), Toast.LENGTH_LONG).show();
                        Order remove = allOrders.getOrder(position);
                        allOrders.remove(remove);
                        orders.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                    //handle the "NO" click
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getResources().getString(R.string.orderNotRemoved), Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Private helper method that when the orders displayed in the list view need to updated
     * on order removal from the order or list initialization.Updates the orders that need to be displayed
     *
     * @return ArrayList<String> representation of all the orders now in the store.
     */
    private ArrayList<String> setOrders(){
        int numOrders = this.allOrders.getNumOrders();
        ArrayList<String> items = new ArrayList<String>();
        String currentString = "";
        for (int i = 0; i < numOrders; i++){
            currentString = "Order Number " + (i+1) +": \n"; //add 1 because we count from 1
            Order currentOrder = allOrders.getOrder(i);
            int numItems = currentOrder.getNumItems();
            for(int j = 0; j < numItems; j++) {
                currentString = currentString + currentOrder.getItem(j).toString() + "\n";
            }
            currentString = currentString + "Total Price: $" + format.format(currentOrder.finalCost()) + "\n";
            items.add(currentString);
        }
        return items;
    }

}
