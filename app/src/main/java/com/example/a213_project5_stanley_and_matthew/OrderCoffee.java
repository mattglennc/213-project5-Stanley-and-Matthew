package com.example.a213_project5_stanley_and_matthew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class OrderCoffee extends AppCompatActivity {

    private CheckBox milkBox;
    private CheckBox creamBox;
    private CheckBox syrupBox;
    private CheckBox caramelBox;
    private CheckBox wcBox;
    private Spinner sizeSelect;
    private Spinner quantSelect;
    private String [] quantities;
    private String [] sizes;
    private ArrayAdapter<String> adapterSize;
    private ArrayAdapter<String> adapterQuant;
    private Button orderCoffeeButton;
    private TextInputEditText subtotalCoffee;
    private Coffee tempCoffee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        milkBox = findViewById(R.id.milkBox);
        creamBox = findViewById(R.id.creamBox);
        syrupBox = findViewById(R.id.syrupBox);
        caramelBox = findViewById(R.id.caramelBox);
        wcBox = findViewById(R.id.wcBox);
        orderCoffeeButton = findViewById(R.id.orderCoffeeButton);
        quantSelect = findViewById(R.id.quantCoffeeSelect);
        quantities = getResources().getStringArray(R.array.quantities);
        adapterQuant = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantities);
        quantSelect.setAdapter(adapterQuant);
        sizeSelect = findViewById(R.id.sizeSelect);
        sizes = getResources().getStringArray(R.array.sizes);
        adapterSize = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        sizeSelect.setAdapter(adapterSize);
        tempCoffee = new Coffee(1,sizeSelect.getSelectedItem().toString());
        subtotalCoffee = findViewById(R.id.subtotalCoffee);
        String price = String.format("%.2f",tempCoffee.getTotalPrice());
        price =getResources().getString(R.string.dollarSign) + price;
        subtotalCoffee.setText(price);

        orderCoffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle(getResources().getString(R.string.addPrompt));
                alert.setMessage(tempCoffee.toString());
                //handle the "YES" click
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getResources().getString(R.string.added), Toast.LENGTH_LONG).show();
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
                tempCoffee.update(quantity - tempCoffee.getQuantity());
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempCoffee.itemPrice());
                subtotalCoffee.setText(newPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                subtotalCoffee.setText("");
            }
        });

        sizeSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String size = adapterView.getSelectedItem().toString();
                tempCoffee.setSize(size);
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempCoffee.itemPrice());
                subtotalCoffee.setText(newPrice);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                subtotalCoffee.setText("");
            }
        });

        milkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean milkChecked = compoundButton.isChecked();
                if(milkChecked){
                    tempCoffee.add("milk");
                } else{
                    tempCoffee.remove("milk");
                }
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempCoffee.itemPrice());
                subtotalCoffee.setText(newPrice);
            }
        });

        creamBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean creamChecked = compoundButton.isChecked();
                if(creamChecked){
                    tempCoffee.add("cream");
                } else{
                    tempCoffee.remove("cream");
                }
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempCoffee.itemPrice());
                subtotalCoffee.setText(newPrice);
            }
        });

        syrupBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean syrupChecked = compoundButton.isChecked();
                if(syrupChecked){
                    tempCoffee.add("syrup");
                } else{
                    tempCoffee.remove("syrup");
                }
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempCoffee.itemPrice());
                subtotalCoffee.setText(newPrice);
            }
        });

        caramelBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean caramelChecked = compoundButton.isChecked();
                if(caramelChecked){
                    tempCoffee.add("caramel");
                } else{
                    tempCoffee.remove("caramel");
                }
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempCoffee.itemPrice());
                subtotalCoffee.setText(newPrice);
            }
        });

        wcBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean wcChecked = compoundButton.isChecked();
                if(wcChecked){
                    tempCoffee.add("whipped cream");
                } else{
                    tempCoffee.remove("whipped cream");
                }
                String newPrice = getResources().getString(R.string.dollarSign) + String.format("%.2f",tempCoffee.itemPrice());
                subtotalCoffee.setText(newPrice);
            }
        });

    }
}
