package com.example.talkingclock;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Provider extends ContentProvider {

    // creating two paths to insert query, delete data whether we delete/update/insert in whole table
    public static final int ALARMS = 100;
    public static final int ALARMS_ID = 101;
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AlarmEntity.CONTENT_AUTHORITY, AlarmEntity.PATH_CONTACTS, ALARMS);
        // this hashtag represents the row id number
        sUriMatcher.addURI(AlarmEntity.CONTENT_AUTHORITY, AlarmEntity.PATH_CONTACTS + "/#", ALARMS_ID);

    }

    public DBhelper mDbhelper;

    @Override
    public boolean onCreate() {
        mDbhelper = new DBhelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,  String selection,  String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database  = mDbhelper.getReadableDatabase();
        // intilaizing cursor
        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case ALARMS:
                cursor = database.query(AlarmEntity.AlarmEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case ALARMS_ID:

                selection = AlarmEntity.AlarmEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(AlarmEntity.AlarmEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cant Query" + uri);


        }


        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete( Uri uri,  String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
