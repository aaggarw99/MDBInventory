package com.example.mdbinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PurchaseDetail extends AppCompatActivity implements View.OnClickListener {

    TextView nameField;
    TextView costField;
    TextView dateField;
    TextView descField;
    TextView vendorField;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);

        nameField = findViewById(R.id.purchaseName);
        costField = findViewById(R.id.cost);
        dateField = findViewById(R.id.date);
        descField = findViewById(R.id.desc);
        vendorField = findViewById(R.id.vendor);
        back = findViewById(R.id.back);


        back.setOnClickListener(this);

        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        String cost = Integer.toString(i.getIntExtra("COST", 0));
        String date = i.getStringExtra("DATE");
        String desc = i.getStringExtra("DESC");
        String vendor = i.getStringExtra("VENDOR");

        nameField.setText(name);
        costField.setText(cost);
        dateField.setText(date);
        descField.setText(desc);
        vendorField.setText(vendor);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.back):
                startActivity(new Intent(this, MainActivity.class));
        }
    }


}
