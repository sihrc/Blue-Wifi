package io.sihrc.blue_wifi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Chris on 4/7/15.
 */
public class DatabaseHelper {
    // DatabaseHelper Instance
    static DatabaseHelper instance;

    //All Fields
    private DatabaseModel model;
    // Database
    private SQLiteDatabase database;

    //Public Constructor - create connection to Database
    public DatabaseHelper(Context context) {
        model = new DatabaseModel(context);
        open();
    }

    //Get Writable Database - open the database
    public void open() {
        database = model.getWritableDatabase();
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }


    public SQLiteDatabase getDatabase() {
        return database;
    }
}
