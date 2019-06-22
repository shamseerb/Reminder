package com.example.sethumadhavank.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderOperation {

    //public static final String LOGTAG = "EMP_MNGMNT_SYS";
    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    public ReminderOperation(Context context){
        dbhandler = new ReminderDBHelper(context);
    }

    public void open(){
        Log.i("MSG : ","Database Opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i("MSG : ", "Database Closed");
        dbhandler.close();
    }

    public long addReminder(Reminder reminder){

        ContentValues values  = new ContentValues();
        values.put("title",reminder.getTitle());
        values.put("description",reminder.getDescription());
        values.put("priority", reminder.getPriority());
        values.put("date", reminder.getDate());
        values.put("time", reminder.getTime());
        long insertid = database.insert(ReminderDBHelper.TABLE_NAME,null,values);
        return  insertid;

    }

    public Reminder getReminder(int id) {

        String[] columns = {"id","title","description","priority","date","time"};
        Cursor cursor = database.query(ReminderDBHelper.TABLE_NAME,columns, "id=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
       // Reminder e = new Reminder();
        Reminder e = new Reminder(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3), cursor.getString(4),cursor.getString(5));
        return e;
    }

    public ArrayList<Reminder> getAllReminder() {

        String[] columns = {"id","title","description","priority","date","time"};

        Cursor cursor = database.query(ReminderDBHelper.TABLE_NAME,columns,null,null,null, null, null);

        ArrayList<Reminder> reminders = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Reminder reminder = new Reminder();
                reminder.setId((cursor.getInt(0)));
                reminder.setTitle(cursor.getString(1));
                reminder.setDescription(cursor.getString(2));
                reminder.setPriority(cursor.getInt(3));
                reminder.setDate(cursor.getString(4));
                reminder.setTime(cursor.getString(5));
                reminders.add(reminder);
            }
        }
        // return All Employees
        return reminders;
    }




    // Updating Employee
    public int updateRemider(Reminder reminder) {

        ContentValues values = new ContentValues();
        values.put("title",reminder.getTitle());
        values.put("description",reminder.getDescription());
        values.put("priority", reminder.getPriority());
        values.put("date", reminder.getDate());
        values.put("time", reminder.getTime());


        // updating row
        return database.update(ReminderDBHelper.TABLE_NAME, values,
                "id =?",new String[] { String.valueOf(reminder.getId())});
    }

    // Deleting Employee
    public void removeReminder(Reminder reminder) {
        database.delete(ReminderDBHelper.TABLE_NAME, "id =" + reminder.getId(), null);
    }
}
