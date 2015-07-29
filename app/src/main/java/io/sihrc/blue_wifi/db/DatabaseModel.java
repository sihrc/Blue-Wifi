package io.sihrc.blue_wifi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chris on 4/7/15.
 */
public class DatabaseModel extends SQLiteOpenHelper {
    // Table Names
    public static final String TABLE_NAME = "collection";
    /**
     * FIELDS *
     */
    // Common Fields
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESC = "desc";
    public static final String KEY_PASSWORD = "password";

    // ModelDatabase creation sql statement
    private static final String CREATE = "create table "
        + TABLE_NAME + "("
        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
        + KEY_DESC + " TEXT NOT NULL,"
        + KEY_NAME + " TEXT NOT NULL,"
        + KEY_PASSWORD + " TEXT NOT NULL);";

    // Table Name
    public static final String DATABASE_NAME = "BlueWifi";
    //Database Info
    private static final int DATABASE_VERSION = 1;

    //Default Constructor
    public DatabaseModel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //OnCreate Method - creates the ModelDatabase
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
    }

    @Override
    //OnUpgrade Method - upgrades ModelDatabase if applicable
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DatabaseModel.class.getName(),
            "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
