package com.example.talkingclock;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

public class Alarm_list_fragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView alarmListView;
    private ArrayAdapter<String> alarmAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_list_fragment, container, false);
        alarmListView = view.findViewById(R.id.list_of_alarm);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize the adapter for the alarm list view
        alarmAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1);
        alarmListView.setAdapter(alarmAdapter);
        // Initialize the loader to load data from the database
        LoaderManager.getInstance(this).initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        // Define a projection that specifies which columns from the database you will actually use after this query.
        String[] projection = {
                AlarmEntity.AlarmEntry._ID,
                AlarmEntity.AlarmEntry.COLUMN_TIME,
                AlarmEntity.AlarmEntry.COLUMN_NOTES
        };

        // Perform a query on the provider using the ContentResolver.
        return new CursorLoader(requireContext(),
                AlarmEntity.AlarmEntry.CONTENT_URI,  // The content URI of the alarms table
                projection,                         // The columns to return for each row
                null,                               // Selection criteria
                null,                               // Selection criteria
                null);                              // The sort order for the returned rows
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        // Clear the adapter of previous alarm data
        alarmAdapter.clear();

        // Iterate through all rows of the cursor and add alarm data to the adapter
        while (data.moveToNext()) {
            String time = data.getString(data.getColumnIndexOrThrow(AlarmEntity.AlarmEntry.COLUMN_TIME));
            String notes = data.getString(data.getColumnIndexOrThrow(AlarmEntity.AlarmEntry.COLUMN_NOTES));
            // Format the alarm information as needed
            String alarmInfo = time + " - " + notes;
            // Add the alarm information to the adapter
            alarmAdapter.add(alarmInfo);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        // Clear the adapter when the data needs to be reset
        alarmAdapter.clear();
    }
}
