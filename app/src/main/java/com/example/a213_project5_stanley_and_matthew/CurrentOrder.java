package com.example.a213_project5_stanley_and_matthew;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
/**
 * This CurrentOrder class provides functionality for activity_order.xml, allowing
 * users to remove items from their order, see the cost, and finalize the order by sending it
 * to the  store.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class CurrentOrder extends AppCompatActivity {

    private Button placeOrder;
    private TextInputEditText subtotal;
    private TextInputEditText salesTax;
    private TextInputEditText total;
    private ListView ordersList;
    private Order currentOrder;
    private static final Double SALESTAX = .06625;
    private ArrayList<String> items;


    /**
     * Get the references of all instances of Views defined in the layout file, retrieves the current order
     * from MainActivity and displays it in the ordersList Listview, removes item from order on click and
     * sends order to the store on click, and displays the order cost before and after sales tax.
     *
     * @param savedInstanceState saved state information of the model
     */
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
                if(items.size() == 0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                    alert.setTitle(getResources().getString(R.string.emptyOrder));
                    alert.setMessage(getResources().getString(R.string.emptyMessage));
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }else{
                    Toast.makeText(view.getContext(),
                            getResources().getString(R.string.placeOrder), Toast.LENGTH_LONG).show();
                    MainActivity.orders.add(currentOrder);
                    MainActivity.currentOrder = new Order();
                    items.clear();
                    setCosts();
                    adapter.notifyDataSetChanged();
                }
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

    /**
     * Private helper method that when the three cost values need to be updated
     * Sets the Order costs to be displayed in the salesTax, subTotal, and total text fields.
     */
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

    /**
     * Private helper method that when the items displayed in the list view needs to updated
     * on item removal from the order or list initialization.Updates the items that need to be displayed
     *
     * @return ArrayList<String> representation of all the items now in the current order.
     */
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