package com.example.talkingclock;

import android.net.Uri;
import android.provider.BaseColumns;

public final class AlarmEntity {
    // Empty constructor to prevent instantiation
    public AlarmEntity() {

    }

    // Define content authority, base URI, and path
    public static final String CONTENT_AUTHORITY = "com.example.talkingclock";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    // Path name should be similar to your table name
    public static final String PATH_CONTACTS = "talkingclock";

    public static abstract class AlarmEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH_CONTACTS);

        // Table and column names
        public static final String TABLE_NAME = "alarmlist";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_NOTES = "notes";
        /*
        public static final String COLUMN_PASSPORT = "passport";
        public static final String COLUMN_ROOMID = "roomId";
        public static final String COLUMN_USERNAME = "userName";
        public static final String COLUMN_PICTURE = "picture";
        public static final String COLUMN_PHONENUMBER = "number";
        public static final String COLUMN_TYPEOFCONTACT = "type";

        //values for COLUMN_TYPEOFCONTACT
        public static final String TYPEOFCONTACT_WORK = "Work";
        public static final String TYPEOFCONTACT_HOME = "Home";
        public static final String TYPEOFCONTACT_PERSONAL = "Personal";

        // Check if the provided type is valid
        public static boolean isValidType(String type) {
            return type.equals(TYPEOFCONTACT_HOME) || type.equals(TYPEOFCONTACT_PERSONAL) || type.equals(TYPEOFCONTACT_WORK);

        }
        */
    }
}
