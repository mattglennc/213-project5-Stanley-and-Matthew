package com.example.a213_project5_stanley_and_matthew;

import androidx.appcompat.app.AppCompatActivity;

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

    }

    public void coffeeActivity(View view){

    }

    public void orderActivity(View view){

    }

    public void storeActivity(View view){

    }
}