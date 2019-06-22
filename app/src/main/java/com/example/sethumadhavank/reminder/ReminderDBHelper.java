package com.example.sethumadhavank.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReminderDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reminder_db.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "reminder";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT, " +
                    "description TEXT, " +
                    "priority INTEGER, " +
                    "date TEXT ," +
                    "time TEXT " +
                    ")";


    public ReminderDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL(TABLE_CREATE);
    }
}
