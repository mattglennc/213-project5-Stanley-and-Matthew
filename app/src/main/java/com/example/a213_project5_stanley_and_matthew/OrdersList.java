package com.example.a213_project5_stanley_and_matthew;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrdersList extends AppCompatActivity {

    private ListView ordersList;
    private ArrayList<String> orders;
    private StoreOrders allOrders;
    private static final DecimalFormat format = new DecimalFormat("0.00");
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
                Toast.makeText(view.getContext(),
                        "Order Removed from the Store", Toast.LENGTH_LONG).show();
                Order remove = allOrders.getOrder(position);
                allOrders.remove(remove);
                orders.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

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
