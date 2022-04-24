package com.example.a213_project5_stanley_and_matthew;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import com.google.android.material.textfield.TextInputEditText;

public class CurrentOrder extends AppCompatActivity {

    private Button placeOrder;
    private TextInputEditText subtotal;
    private TextInputEditText salesTax;
    private TextInputEditText total;
    private ListView ordersList;
    private Order currentOrder;
    private static final Double SALESTAX = .06625;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        placeOrder = findViewById(R.id.placeOrder);
        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        ordersList = findViewById(R.id.ordersList);
        this.currentOrder = MainActivity.currentOrder;
        this.items = setItems();
        setCosts();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  this.items);
        ordersList.setAdapter(adapter);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        getResources().getString(R.string.placeOrder), Toast.LENGTH_LONG).show();
                MainActivity.orders.add(currentOrder);
                MainActivity.currentOrder = new Order();
                items.clear();
                setCosts();
                adapter.notifyDataSetChanged();
            }
        });
        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                MenuItem remove = currentOrder.getMenuItem(position);
                currentOrder.remove(remove);
                items.remove(position);
                setCosts();
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void setCosts(){
        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        Double numSubtotal = this.currentOrder.finalCost();
        String price = "$" + String.format("%.2f", numSubtotal);
        subtotal.setText(price);
        Double tax = SALESTAX * numSubtotal;
        Double totaldoub = tax + numSubtotal;
        String strTax = "$" + String.format("%.2f", tax);
        String newTotal = "$" + String.format("%.2f", totaldoub);
        salesTax.setText(strTax);
        total.setText(newTotal);
    }

    private ArrayList<String> setItems(){
        int numItems = this.currentOrder.getNumItems();
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<numItems; i++){
            items.add(this.currentOrder.getItem(i).toString());
        }
        setCosts();
        return items;
    }

}