package com.example.a213_project5_stanley_and_matthew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class CurrentOrder extends AppCompatActivity {

    private Button placeOrder;
    private TextInputEditText subtotal;
    private TextInputEditText salesTax;
    private TextInputEditText total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        placeOrder = findViewById(R.id.placeOrder);
        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        getResources().getString(R.string.placeOrder), Toast.LENGTH_LONG).show();
            }
        });

    }
}