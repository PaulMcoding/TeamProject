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
        int match = sUriMatcher.match(uri);
        if (match == ALARMS) {
            return insertAlarms(uri, values);
        }
        throw new IllegalArgumentException("Cannot insert Alarms" + uri);

    }

    private Uri insertAlarms(Uri uri, ContentValues values) {

        String alarmTime = values.getAsString(AlarmEntity.AlarmEntry.COLUMN_TIME);
        if (alarmTime == null) {
            throw new IllegalArgumentException("Time is required");
        }


        String alarmNotes = values.getAsString(AlarmEntity.AlarmEntry.COLUMN_NOTES);
        if (alarmNotes == null) {
            throw new IllegalArgumentException("number is required");
        }

        /*
        String email = values.getAsString(Contract.ContactEntry.COLUMN_EMAIL);
        if (email == null) {
            throw new IllegalArgumentException("email is required");
        }

        String passport = values.getAsString(Contract.ContactEntry.COLUMN_PASSPORT);
        if (passport == null) {
            throw new IllegalArgumentException("Passport No. is required");
        }

        String type = values.getAsString(Contract.ContactEntry.COLUMN_TYPEOFCONTACT);
        if (type == null || !Contract.ContactEntry.isValidType(type)) {
            throw new IllegalArgumentException("type is required");

        }
        */
        //going to insert data
        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        long id = database.insert(AlarmEntity.AlarmEntry.TABLE_NAME, null, values);

        if (id == -1) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);

    }

    @Override
    public int delete( Uri uri,  String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ALARMS:
                rowsDeleted = database.delete(AlarmEntity.AlarmEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case ALARMS_ID:
                selection = AlarmEntity.AlarmEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(AlarmEntity.AlarmEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Cannot delete" + uri);



        }

        if (rowsDeleted!=0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update( Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ALARMS:
                return updateAlarmList(uri, values, selection, selectionArgs);

            case ALARMS_ID:

                selection = AlarmEntity.AlarmEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateAlarmList(uri, values, selection, selectionArgs);

            default:
                throw new IllegalArgumentException(" Cannot update the contact");


        }

    }

    private int updateAlarmList(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(AlarmEntity.AlarmEntry.COLUMN_TIME)) {
            String alarmTime = values.getAsString(AlarmEntity.AlarmEntry.COLUMN_TIME);
            if (alarmTime == null) {
                throw new IllegalArgumentException("Time is required");
            }
        }
        if (values.containsKey(AlarmEntity.AlarmEntry.COLUMN_NOTES)) {
            String alarmNotes = values.getAsString(AlarmEntity.AlarmEntry.COLUMN_NOTES);
            if (alarmNotes == null) {
                throw new IllegalArgumentException("number is required");
            }
        }
/*
        if (values.containsKey(Contract.ContactEntry.COLUMN_PASSPORT)) {

                String passport = values.getAsString(Contract.ContactEntry.COLUMN_PASSPORT);
                if (passport == null) {
                    throw new IllegalArgumentException("Passort No. is required");
                }
            }

            if (values.containsKey(Contract.ContactEntry.COLUMN_EMAIL)) {
                String email = values.getAsString(Contract.ContactEntry.COLUMN_EMAIL);
                if (email == null) {
                    throw new IllegalArgumentException("email is required");
                }
            }

            if (values.containsKey(Contract.ContactEntry.COLUMN_TYPEOFCONTACT)) {
                String type = values.getAsString(Contract.ContactEntry.COLUMN_TYPEOFCONTACT);
                if (type == null || !Contract.ContactEntry.isValidType(type)) {
                    throw new IllegalArgumentException("type is required");

                }
            }
            */


        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        int rowsUpdated = database.update(AlarmEntity.AlarmEntry.TABLE_NAME, values, selection, selectionArgs);
        if (rowsUpdated!=0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
