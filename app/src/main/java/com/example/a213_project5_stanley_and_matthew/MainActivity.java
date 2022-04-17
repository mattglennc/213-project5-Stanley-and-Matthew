package com.example.a213_project5_stanley_and_matthew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    private ImageButton donutButton, coffeeButton, orderButton,storeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donutButton = findViewById(R.id.donutButton);
        coffeeButton = findViewById(R.id.coffeeButton);
        orderButton = findViewById(R.id.orderButton);
        storeButton = findViewById(R.id.storeButton);
    }

    public void donutActivity(View view){
        Intent donutIntent = new Intent(this, OrderDonuts.class);
        startActivity(donutIntent);
    }

    public void coffeeActivity(View view){
        Intent coffeeIntent = new Intent(this, OrderCoffee.class);
        startActivity(coffeeIntent);
    }

    public void orderActivity(View view){
        Intent orderIntent = new Intent(this, CurrentOrder.class);
        startActivity(orderIntent);
    }

    public void storeActivity(View view){
        Intent storeIntent = new Intent(this, OrdersList.class);
        startActivity(storeIntent);
    }
}