package com.example.mdbinventory;

import android.util.Log;

public class Purchase {
    private String name;
    private int cost;
    private String desc;
    private String vendorName;
    private String date;
    private int id;

    public Purchase(String name, int cost, String desc, String vendorName, String date) {
        this.name = name;
        this.cost = cost;
        this.desc = desc;
        this.vendorName = vendorName;
        this.date = date;
        this.id = -1;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public String getDate() {
        return this.date;
    }

    public int getId() {
        if (id != -1) {
            return id;
        }
        Log.i("GET ID ERROR", "got -1, meaning ID was not set");
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

}
