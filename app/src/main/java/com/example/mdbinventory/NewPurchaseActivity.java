package com.example.mdbinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPurchaseActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameText;
    EditText costText;
    EditText descText;
    EditText dateText;
    EditText vendorNameText;
    Button submit;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_purchase);

        nameText = findViewById(R.id.purchaseName);
        costText = findViewById(R.id.cost);
        descText = findViewById(R.id.desc);
        dateText = findViewById(R.id.date);
        vendorNameText = findViewById(R.id.vendorName);
        submit = findViewById(R.id.submit);
        mDatabaseHelper = new DatabaseHelper(this);

        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case (R.id.submit):

                if (TextUtils.isEmpty(nameText.getText())
                        || TextUtils.isEmpty(costText.getText())
                        || TextUtils.isEmpty(descText.getText())
                        || TextUtils.isEmpty(dateText.getText())
                        || TextUtils.isEmpty(vendorNameText.getText())) {
                    Toast.makeText(NewPurchaseActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Purchase newP = new Purchase(nameText.getText().toString().trim(),
                        Integer.parseInt(costText.getText().toString().trim()),
                        descText.getText().toString().trim(),
                        vendorNameText.getText().toString().trim(),
                        dateText.getText().toString().trim());
                addPurchaseToDB(newP);

                //mDatabaseHelper.getData(0);
        }
    }

    private void addPurchaseToDB(Purchase p) {
        boolean success = mDatabaseHelper.addData(p);
        if (success) {
            Toast.makeText(this, "Successfully created new purchase", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Error in creating new purchase", Toast.LENGTH_LONG).show();
        }
    }
}
