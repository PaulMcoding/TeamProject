/*package com.example.talkingclock;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.talkingclock.databinding.ActivityAddAlarmBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class Add_Alarm extends AppCompatActivity {

    private ActivityAddAlarmBinding binding;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static final int MY_PERMISSIONS_REQUEST_ALARM = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();

        binding.selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        binding.setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        binding.cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm canceled", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (canScheduleExactAlarms()) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            Toast.makeText(this, "Alarm Set Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cannot schedule exact alarms. Please check app permissions.", Toast.LENGTH_SHORT).show();
        }
    }


    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        picker.show(getSupportFragmentManager(), "TalkingClock");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = picker.getHour();
                int minute = picker.getMinute();
                String amPm;

                if (hour >= 12) {
                    amPm = "PM";
                    if (hour > 12) {
                        hour -= 12;
                    }
                } else {
                    amPm = "AM";
                }

                String formattedHour = String.format("%02d", hour);
                String formattedMinute = String.format("%02d", minute);

                binding.selectedTime.setText(formattedHour + " : " + formattedMinute + " " + amPm);

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "TalkingClockAlarmChannel";
            String description = "Channel for Talking Clock Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("TalkingClock1.0", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private boolean canScheduleExactAlarms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return alarmManager.canScheduleExactAlarms();
        }
        // For versions below Android 12 (API level 31), return true assuming that exact alarms can be scheduled
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_ALARM) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setAlarm();
            } else {
                Toast.makeText(this, "Permission denied. Cannot set alarm.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


 */



/*package com.example.tenantrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
    }
}*/

package com.example.talkingclock;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.NavUtils;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

public class Add_Alarm extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    EditText mTimeEditText, mNotesEditText, mAlarmRepeatEditText, mSomething1EditText;
    //private Uri mPhotoUri;
    //private String roomId;
    //private String userName;
    private Uri mCurrentContactUri;

    //private String mType = AlarmEntity.AlarmEntry.TYPEOFCONTACT_PERSONAL;
    //ImageView mPhoto;
    private boolean mAlarmHasChanged = false;
    //Spinner mSpinner; needed spinner in slecting ringtone
    public static final int LOADER = 0;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mAlarmHasChanged = true;
            return false;
        }
    };

    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        Intent intent = getIntent();
        mCurrentContactUri = intent.getData();
        mTimeEditText = findViewById(R.id.selectedTime);//selectedTime & timeEditText
        mNotesEditText = findViewById(R.id.notesEditText);
        //mAlarmRepeatEditText = findViewById(R.id.repeatEditText);
        //mSomething1EditText = findViewById(R.id.something1EditText);
        //mPhoto = findViewById(R.id.profile_image);
        //mSpinner = findViewById(R.id.spinner);


        if (mCurrentContactUri == null) {
            //mPhoto.setImageResource(R.drawable.photo);
            //setTitle("Add a Tenant");
            // hide delete menu when we are adding a new contact
            invalidateOptionsMenu();

        } else {
            //setTitle("Edit a Tenant");
            getLoaderManager().initLoader(LOADER, null, this);

        }


        mTimeEditText.setOnTouchListener(mOnTouchListener);
        mNotesEditText.setOnTouchListener(mOnTouchListener);
        //mAlarmRepeatEditText.setOnTouchListener(mOnTouchListener);
        //mSomething1EditText.setOnTouchListener(mOnTouchListener); ////Changes here for Passport No.
        //mPhoto.setOnTouchListener(mOnTouchListener);
        //mSpinner.setOnTouchListener(mOnTouchListener);


        findViewById(R.id.setAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform actions when the button is clicked
                Log.d("MyActivity", "Button clicked");
                saveContact();

                // Replace the fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.navAlarmList, new Alarm_list_fragment())
                        .addToBackStack(null)  // Optional: Add transaction to the back stack
                        .commit();
                Log.d("MyActivity", "Fragment replaced");
            }
        });
    }
/*
    private void setUpSpinner() {

        ArrayAdapter spinner = ArrayAdapter.createFromResource(this, R.array.arrayspinner, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner.setAdapter(spinner);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.homephone))) {
                        mType = Contract.ContactEntry.TYPEOFCONTACT_HOME;
                    } else if (selection.equals(getString(R.string.workphone))) {
                        mType = Contract.ContactEntry.TYPEOFCONTACT_WORK;

                    } else {
                        mType = Contract.ContactEntry.TYPEOFCONTACT_PERSONAL;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mType = Contract.ContactEntry.TYPEOFCONTACT_PERSONAL;

            }
        });
    }

    // selector for image selecting ftom photos
    public void trySelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
        openSelector();
    }

    private void openSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType(getString(R.string.intent_type));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_image)), 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openSelector();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mPhotoUri = data.getData();
                mPhoto.setImageURI(mPhotoUri);
                mPhoto.invalidate();
            }
        }
    }

    */

    //working on menu options

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menueditor, menu);
        return true;

    }

    @Override //spinner options
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_save) {
            saveContact();
            if (hasAllRequiredValues) {
                finish();
            }
            return true;
        } else if (itemId == R.id.delete) {
            showDeleteConfirmationDialog();
            return true;
        } else if (itemId == android.R.id.home) {
            if (!mAlarmHasChanged) {
                DialogInterface.OnClickListener discardButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NavUtils.navigateUpFromSameTask(Add_Alarm.this);
                    }
                };
                showUnsavedChangesDialog(discardButton);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean saveContact() {

        // create savecontact method
        hasAllRequiredValues = false;
        String alarmtime = mTimeEditText.getText().toString().trim();
        //String email = mAlarmRepeatEditText.getText().toString().trim();
        String alarmnotes = mNotesEditText.getText().toString().trim();
        //String passport = mSomething1EditText.getText().toString().trim(); /// Values has been changed here

        // when fields are empty
        if (mCurrentContactUri == null && TextUtils.isEmpty(alarmtime)
                && TextUtils.isEmpty(alarmnotes) ){ //&& TextUtils.isEmpty(phone) && TextUtils.isEmpty(passport)  && mType == Contract.ContactEntry.TYPEOFCONTACT_PERSONAL && mPhotoUri == null) {

            hasAllRequiredValues = true;
            return hasAllRequiredValues;

        }

        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty(alarmtime)) {
            Toast.makeText(this, "Time is Required", Toast.LENGTH_SHORT).show();
            return hasAllRequiredValues;


        } else {
            values.put(AlarmEntity.AlarmEntry.COLUMN_TIME, alarmtime);
        }
        /*
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show();
            return hasAllRequiredValues;


        } else {
            values.put(Contract.ContactEntry.COLUMN_EMAIL, email);
        }*/

        if (TextUtils.isEmpty(alarmnotes)) {
            Toast.makeText(this, "Phone Number is Required", Toast.LENGTH_SHORT).show();
            return hasAllRequiredValues;


        } else {
            values.put(AlarmEntity.AlarmEntry.COLUMN_NOTES, alarmnotes);
        }
        /*
        if (TextUtils.isEmpty(passport)) { //  CHanges Made for PAssport
            Toast.makeText(this, "PassPort Number is Required", Toast.LENGTH_SHORT).show();
            return hasAllRequiredValues;


        } else {
            values.put(Contract.ContactEntry.COLUMN_PASSPORT, passport);
        }

        // optional values

        values.put(Contract.ContactEntry.COLUMN_TYPEOFCONTACT, mType);
        values.put(Contract.ContactEntry.COLUMN_PICTURE, mPhotoUri.toString());
        values.put(Contract.ContactEntry.COLUMN_ROOMID, roomId); // putting room no. which was intented
        values.put(Contract.ContactEntry.COLUMN_USERNAME, userName); // putting username in whose account was used
        */
        if (mCurrentContactUri == null) {

            Uri newUri = getContentResolver().insert(AlarmEntity.AlarmEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "Error with Saving", Toast.LENGTH_SHORT).show();
            } else {
                long newRowId = ContentUris.parseId(newUri); // Get the row ID of the newly inserted row
                Log.d("SaveContact", "New row ID: " + newRowId);
                Toast.makeText(this, "Success with Saving", Toast.LENGTH_SHORT).show();

            }
        } else {

            int rowsAffected = getContentResolver().update(mCurrentContactUri, values, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(this, "Error  with Update", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Success  with Update", Toast.LENGTH_SHORT).show();

            }

        }

        hasAllRequiredValues = true;

        return hasAllRequiredValues;

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {AlarmEntity.AlarmEntry._ID,
                AlarmEntity.AlarmEntry.COLUMN_TIME,
                AlarmEntity.AlarmEntry.COLUMN_NOTES,
                //AlarmEntity.AlarmEntry.COLUMN_PICTURE,
                //AlarmEntity.AlarmEntry.COLUMN_PASSPORT, // Changes made for Passport
                //AlarmEntity.AlarmEntry.COLUMN_PHONENUMBER,
                //AlarmEntity.AlarmEntry.COLUMN_TYPEOFCONTACT
        };

        return new CursorLoader(this, mCurrentContactUri,
                projection, null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            // getting position of each column
            int alarmTime = cursor.getColumnIndex(AlarmEntity.AlarmEntry.COLUMN_TIME);
            int alarmNotes = cursor.getColumnIndex(AlarmEntity.AlarmEntry.COLUMN_NOTES);
            //int passport = cursor.getColumnIndex(Contract.ContactEntry.COLUMN_PASSPORT);
            //int type = cursor.getColumnIndex(Contract.ContactEntry.COLUMN_TYPEOFCONTACT);
            //int number = cursor.getColumnIndex(Contract.ContactEntry.COLUMN_PHONENUMBER);
            //int picture = cursor.getColumnIndex(Contract.ContactEntry.COLUMN_PICTURE);

            String alarmtime = cursor.getString(alarmTime);
            String alarmnotes = cursor.getString(alarmNotes);
            //String contactnumber = cursor.getString(number);
            //String contactpassport= cursor.getString(passport); // cj=hanges made here
            //String contactpicture = cursor.getString(picture);
            //String typeof = cursor.getString(type);
            //mPhotoUri = Uri.parse(contactpicture);

            mNotesEditText.setText(alarmnotes);
            mTimeEditText.setText(alarmtime);
            /*mAlarmRepeatEditText.setText(contactemail);
            mSomething1EditText.setText(contactpassport); // change dmade here
            mPhoto.setImageURI(mPhotoUri);

            switch (typeof) {

                case Contract.ContactEntry.TYPEOFCONTACT_HOME:
                    mSpinner.setSelection(1);
                    break;

                case Contract.ContactEntry.TYPEOFCONTACT_WORK:
                    mSpinner.setSelection(2);
                    break;

                default:
                    mSpinner.setSelection(0);




            }*/

        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mNotesEditText.setText("");
        mTimeEditText.setText("");
        //mAlarmRepeatEditText.setText("");
        //mSomething1EditText.setText("");
        //mPhoto.setImageResource(R.drawable.photo);

    }

    // alert keep editing dialog box
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // when User clicked the "Keep editing" button
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button,
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // deleting from database
    private void deleteProduct() {
        // perform the delete if this is an existing product.
        if (mCurrentContactUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentContactUri, null, null);

            // Show a toast
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }

    @Override
    public void onBackPressed() {
        // If the current fragment is not Alarm_list_fragment, navigate back to it
        if (!(getSupportFragmentManager().findFragmentById(R.id.navAlarmList) instanceof Alarm_list_fragment)) {
            // Replace the current fragment with Alarm_list_fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navAlarmList, new Alarm_list_fragment())
                    .commit();
            return;
        }

        // If the product hasn't changed, continue with handling back button press
        if (!mAlarmHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

}