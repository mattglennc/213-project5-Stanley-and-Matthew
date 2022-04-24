package com.example.a213_project5_stanley_and_matthew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

/**
 * This itemSelectedActivity class provides functionality for activity_item_selected.xml, allowing
 * users to place orders of Donut.  Private helper methods are included.
 *
 * @author Matthew Carrascoso & Stanley Chou
 */
public class ItemSelectedActivity extends AppCompatActivity {
    private Button addToOrder;
    private TextView donutLabel;
    private Spinner quantSelect;
    private String [] quantities;
    private ArrayAdapter<String> adapter;
    private TextInputEditText donutSubtotal;
    private Donut tempDonut;

    /**
     * Get the references of all instances of Views defined in the layout file, gets all the information needed
     * to place donut order on button click.
     *
     * @param savedInstanceState saved state information of the model
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        addToOrder = findViewById(R.id.addToOrder);
        donutLabel = findViewById(R.id.donutLabel);
        quantSelect = findViewById(R.id.quantSelect);
        quantities = getResources().getStringArray(R.array.quantities);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantities);
        quantSelect.setAdapter(adapter);
        donutSubtotal = findViewById(R.id.donutSubtotal);
        Intent intent = getIntent();
        String flavor = intent.getStringExtra("ITEM");
        donutLabel.setText(flavor);
        String price =intent.getStringExtra("PRICE");
        tempDonut = new Donut(flavor,1,Double.parseDouble(price));
        price =getResources().getString(R.string.dollarSign) + price;
        donutSubtotal.setText(price);

        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle(getResources().getString(R.string.addPrompt));
                alert.setMessage(tempDonut.toString());
                //handle the "YES" click
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getResources().getString(R.string.added), Toast.LENGTH_LONG).show();
                                MainActivity.currentOrder.add(tempDonut);
                    }
                    //handle the "NO" click
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getResources().getString(R.string.notAdded), Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        quantSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int quantity = Integer.parseInt(adapterView.getSelectedItem().toString());
                tempDonut.update(quantity - tempDonut.getQuantity());
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempDonut.getTotalPrice());
                donutSubtotal.setText(newPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                donutSubtotal.setText("");
            }
        });
    }

}