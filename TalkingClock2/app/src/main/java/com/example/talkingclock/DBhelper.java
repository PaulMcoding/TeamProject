package com.example.talkingclock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "talkingclock.db";
    public static final int DATABASE_VERSION = 1;

    // Constructor
    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the table
        String SQL_CREATE_TABLE = "CREATE TABLE " + AlarmEntity.AlarmEntry.TABLE_NAME + " ("
                + AlarmEntity.AlarmEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AlarmEntity.AlarmEntry.COLUMN_TIME + " TEXT NOT NULL, "
                + AlarmEntity.AlarmEntry.COLUMN_NOTES + " TEXT)";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TABLE);
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + AlarmEntity.AlarmEntry.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
