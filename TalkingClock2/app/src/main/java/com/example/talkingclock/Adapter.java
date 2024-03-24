package com.example.talkingclock;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends CursorAdapter {

    public Adapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate the layout for a new item in the ListView
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Get references to the views in the list item layout
        TextView alarmTimeView, alarmNotesView, typeofContactView;
        ImageView mContactImageView;

        alarmTimeView = view.findViewById(R.id.textalarmtimeview);
        alarmNotesView = view.findViewById(R.id.textalarmnotes);
        //typeofContactView = view.findViewById(R.id.textTypeofContact);


        // Get column indices from the curser
        int name = cursor.getColumnIndex(AlarmEntity.AlarmEntry.COLUMN_TIME);
        //int type = cursor.getColumnIndex(AlarmEntity.AlarmEntry.COLUMN_TYPEOFCONTACT);
        int number = cursor.getColumnIndex(AlarmEntity.AlarmEntry.COLUMN_NOTES);


        // Get data from the cursor
        String contactname = cursor.getString(name);
        String contactnumber = cursor.getString(number);

        //String typeof = cursor.getString(type);
        ;

        // Show the view
        view.setVisibility(View.VISIBLE);

        // Set the data for the views
        alarmTimeView.setText(contactname);
        alarmNotesView.setText(contactnumber);
        //typeofContactView.setText(typeof);

    }
}
