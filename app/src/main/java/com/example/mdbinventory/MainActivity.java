package com.example.mdbinventory;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView listOfPurchases;
    PurchasesAdapter adapter;
    ArrayList<Purchase> purchases;
    FloatingActionButton fabAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Purchase dummy = new Purchase("Alcohol", 5, "Something to consume", "Target", "10/15/1999");
        Purchase dummy2 = new Purchase("Napkins", 1, "A piece of paper", "Target", "02/19/2019");
        Purchase dummy3 = new Purchase("Forks", 1, "Eating utensil", "Target", "12/31/2018");

        purchases = new ArrayList<>();
        purchases.add(dummy);
        purchases.add(dummy2);
        purchases.add(dummy3);

        Log.i("Size", Integer.toString(purchases.size()));
        Log.i("Purchases content", purchases.get(0).getName());
        Log.i("Purchases content", purchases.get(1).getName());
        Log.i("Purchases content", purchases.get(2).getName());


        listOfPurchases = findViewById(R.id.rv);
        listOfPurchases.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PurchasesAdapter(this, purchases);
        listOfPurchases.setAdapter(adapter);

        fabAdd = findViewById(R.id.fab);
        fabAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case (R.id.fab):
                startActivity(new Intent(this, NewPurchaseActivity.class));
        }
    }
}
