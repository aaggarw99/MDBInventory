package com.example.mdbinventory;

import android.util.Log;

import java.util.Date;

public class Purchase implements Comparable<Purchase> {
    private String name;
    private int cost;
    private String desc;
    private String vendorName;
    private int year;
    private int month;
    private int day;
    private int id;

    public Purchase(String name, int cost, String desc, String vendorName, int year, int month, int day) {
        this.name = name;
        this.cost = cost;
        this.desc = desc;
        this.vendorName = vendorName;
        this.year = year;
        this.month = month;
        this.day = day;
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

    public int getYear() { return this.year; }

    public int getDay() { return this.day; }

    public int getMonth() { return this.month; }

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

    @Override
    public int compareTo(Purchase p) {
        if (this.year > p.getYear()) {
            return -1;
        } else if (this.year < p.getYear()) {
            return 1;
        } else {
            if (this.month > p.getMonth()) {
                return -1;
            } else if (this.month < p.getMonth()) {
                return 1;
            } else {
                if (this.day > p.getDay()) {
                    return -1;
                } else if (this.day < p.getDay()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

    }

}
