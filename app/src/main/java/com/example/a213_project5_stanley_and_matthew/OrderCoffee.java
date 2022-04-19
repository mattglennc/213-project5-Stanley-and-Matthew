package com.example.a213_project5_stanley_and_matthew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        orderCoffeeButton = findViewById(R.id.orderCoffeeButton);
        quantSelect = findViewById(R.id.quantCoffeeSelect);
        quantities = getResources().getStringArray(R.array.quantities);
        adapterQuant = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantities);
        quantSelect.setAdapter(adapterQuant);
        sizeSelect = findViewById(R.id.sizeSelect);
        sizes = getResources().getStringArray(R.array.sizes);
        adapterSize = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        sizeSelect.setAdapter(adapterSize);

        orderCoffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Add item to order?");
                alert.setMessage("Insert Coffee toString() here.");
                //handle the "YES" click
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                "Insert Coffee toString() here." + " added.", Toast.LENGTH_LONG).show();
                    }
                    //handle the "NO" click
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                "Insert Coffee toString() here." + " not added.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

    }
}
