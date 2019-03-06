package com.example.mdbinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "purchase_table";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_COST = "cost";
    private static final String COL_DESC = "description";
    private static final String COL_VENDOR = "vendor";
    private static final String COL_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, "
                + COL_COST + " INTEGER, "
                + COL_DESC + " TEXT, "
                + COL_VENDOR + " TEXT, "
                + COL_DATE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, p.getName());
        contentValues.put(COL_COST, p.getCost());
        contentValues.put(COL_DATE, p.getDate());
        contentValues.put(COL_DESC, p.getDesc());
        contentValues.put(COL_VENDOR, p.getVendorName());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
