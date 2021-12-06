package com.mim.tutorprojecttest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.mim.tutorprojecttest.classes.Details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DBTables extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TutorDB";

    // table names
    private static final String TABLE_DETAILS = "tbl_Details";

    private final String id = "id";
    private final String name = "name";
    private final String classis = "classis";
    private final String date = "createdDate";
    private final String height = "height";


    private final String QUERY_TABLE_DETAILS = "CREATE TABLE IF NOT EXISTS " + TABLE_DETAILS + " ( "
            + id + " INTEGER PRIMARY KEY,"
            + name + " TEXT,"
            + classis + " TEXT,"
            + date + " TEXT,"
            + height + " TEXT  " + ")";

    public DBTables(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("In onCreate", "Before Creating Tables");

        db.execSQL(QUERY_TABLE_DETAILS);

        Log.e("in onCreate", "After Creating Tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("in onUpgrade", "before upgrading upgrade");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);

        onCreate(db);

        Log.e("in onUpgrade", "after upgrading db");
    }

    public void insertDetails(Details item) {
        Log.e("in the ", " populate users");
        SQLiteDatabase db = this.getWritableDatabase();
        //  db.execSQL("delete from " + TABLE_SYNC);
        ContentValues values = new ContentValues();
        values.put(id, item.getId());
        values.put(name, item.getName());
        values.put(classis, item.getClassis());
        values.put(date, item.getDate());
        values.put(height, item.getHeight());

        db.insert(TABLE_DETAILS, null, values);

        db.close();
        Log.e("after inserting", "values in db");

    }

    public void insertDetails(ArrayList<Details> details) {
        Log.e("in the ", " populate users");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_DETAILS);
        ContentValues values = new ContentValues();
        for (int i = 0; i < details.size(); i++) {
            Details item = details.get(i);
            values.put(id, item.getId());
            values.put(name, item.getName());
            values.put(classis, item.getClassis());
            values.put(date, item.getDate());
            values.put(height, item.getHeight());
            db.insert(TABLE_DETAILS, null, values);
        }
        db.close();
        Log.e("after inserting", "values in db");

    }

    public List<Details> retrieveDetails() {
        List<Details> userInfo = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DETAILS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                Details userItem = new Details();
                userItem.setId(cursor.getString(0));
                userItem.setName(cursor.getString(1));
                userItem.setClassis(cursor.getString(2));
                userItem.setDate(cursor.getString(3));
                userItem.setHeight(cursor.getString(4));
                userInfo.add(userItem);
            } while (cursor.moveToNext());
        }

        return userInfo;
    }


}
