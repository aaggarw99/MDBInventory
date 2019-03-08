package com.example.mdbinventory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class NewPurchaseActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText nameText;
    EditText costText;
    EditText descText;

    Button dateText;

    EditText vendorNameText;
    Button submit;

    DatabaseHelper mDatabaseHelper;

    int day, year, month = -1;
    boolean dateSelected;

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
        dateText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case (R.id.submit):

                if (TextUtils.isEmpty(nameText.getText())
                        || TextUtils.isEmpty(costText.getText())
                        || TextUtils.isEmpty(descText.getText())
                        || !this.dateSelected
                        || TextUtils.isEmpty(vendorNameText.getText())) {
                    Toast.makeText(NewPurchaseActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                Purchase newP = new Purchase(nameText.getText().toString().trim(),
                        Integer.parseInt(costText.getText().toString().trim()),
                        descText.getText().toString().trim(),
                        vendorNameText.getText().toString().trim(),
                        this.year,
                        this.month,
                        this.day);

                addPurchaseToDB(newP);
                break;

            case (R.id.date):
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        NewPurchaseActivity.this, this, year, month, day);
                dialog.show();
                break;

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month + 1;
        this.day = dayOfMonth;
        dateSelected = true;
    }
}
