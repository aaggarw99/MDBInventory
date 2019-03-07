package com.example.mdbinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "purchasesTable";
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
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL UNIQUE, "
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


    public void getData(int i) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {Integer.toString(i)};
        Cursor cursor = db.query(TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);

        //List<String> data = new ArrayList<>();
        do {
            //data.add(cursor.getString(0));
            //data.add(cursor.getString(1));
            Log.i("Data List", cursor.getString(0));
        } while (cursor.moveToNext());

        int count = cursor.getCount();
        Log.i("COUNT:", Integer.toString(count));
        cursor.close();
    }

    public ArrayList<Purchase> getAllData() {

        ArrayList<Purchase> purchases = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Purchase p = new Purchase(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_COST))),
                        cursor.getString(cursor.getColumnIndex(COL_DESC)),
                        cursor.getString(cursor.getColumnIndex(COL_VENDOR)),
                        cursor.getString(cursor.getColumnIndex(COL_DATE)));
                purchases.add(p);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return purchases;

    }

    public boolean deleteitem(String name){

        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "DELETE FROM " + TABLE_NAME +
                " WHERE " + COL_NAME + " = ?";

        String[] strParams = {name};

        db.execSQL(selectQuery, strParams);
        db.close();
        return  true;

    }

}
