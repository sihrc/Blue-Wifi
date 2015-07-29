package io.sihrc.blue_wifi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.sihrc.blue_wifi.models.Wifi;

/**
 * Created by Chris on 4/13/15.
 */
public class WifiDatabase {
    static WifiDatabase instance;
    // Sketch Columns
    private static String[] collectionColumns = {
        DatabaseModel.KEY_ID,
        DatabaseModel.KEY_NAME,
        DatabaseModel.KEY_DESC,
        DatabaseModel.KEY_PASSWORD
    };
    SQLiteDatabase database;
    Context context;

    public static WifiDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new WifiDatabase();
            instance.context = context;
            instance.database = DatabaseHelper.getInstance(context).getDatabase();
        }

        return instance;
    }


    /**
     * Collection helpers
     */
    public Wifi getWifi(String id) {
        Cursor cursor = database.query(DatabaseModel.TABLE_NAME,
            collectionColumns, DatabaseModel.KEY_ID + " = '" + id + "'",
            null, null, null, null);
        if (!cursor.moveToFirst())
            return null;

        Wifi wifi = loadWifi(cursor);
        cursor.close();
        return wifi;
    }

    public List<Wifi> getAllWifis() {
        List<Wifi> wifis = new ArrayList<>();
        Cursor cursor = database.query(DatabaseModel.TABLE_NAME,
            collectionColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            wifis.add(loadWifi(cursor));
            cursor.moveToNext();
        }

        cursor.close();
        return wifis;
    }

    public void deleteWifi(int id) {
        database.delete(DatabaseModel.TABLE_NAME,
            DatabaseModel.KEY_ID + " = '" + id + "'",
            null);
    }

    public void saveWifi(Wifi wifi) {
        ContentValues values = new ContentValues();
        values.put(DatabaseModel.KEY_ID, wifi.id);
        values.put(DatabaseModel.KEY_NAME, wifi.name);
        values.put(DatabaseModel.KEY_NAME, wifi.password);
        database.insertWithOnConflict(DatabaseModel.TABLE_NAME, null,
            values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Wifi findOrCreate(Wifi wifi) {
        Cursor cursor = database.query(DatabaseModel.TABLE_NAME,
            collectionColumns, DatabaseModel.KEY_ID + " = '" + wifi.id + "'",
            null, null, null, null);

        if (cursor != null && (cursor.moveToFirst() || cursor.getCount() != 0)) {
            wifi =  loadWifi(cursor);
            cursor.close();
            return wifi;
        }

        saveWifi(wifi);
        return wifi;
    }

    private Wifi loadWifi(Cursor cursor) {
        if (cursor == null) {
            Log.e("DatabaseHelper", "Cursor is null for loadWifi");
            return null;
        }

        return new Wifi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        );

    }

    public List<Wifi> getWifis(List<String> ids) {
        List<Wifi> wifis = new ArrayList<>(ids.size());

        for (String id : ids) {
            wifis.add(getWifi(id));
        }

        return wifis;
    }
}