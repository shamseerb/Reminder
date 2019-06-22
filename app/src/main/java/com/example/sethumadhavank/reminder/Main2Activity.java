package com.example.sethumadhavank.reminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main2Activity extends AppCompatActivity  {

    EditText titleEdit,descEdit, dateEdit,timeEdit;
    Button save;
    Spinner spinner;
    private int mYear, mMonth, mDay, mHour, mMinute;
    boolean edit = false;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dateEdit = (EditText) findViewById(R.id.datetext);
        timeEdit = (EditText) findViewById(R.id.timetext);
        titleEdit = (EditText) findViewById(R.id.titleEdit);
        descEdit = (EditText) findViewById(R.id.descEdit);
        save = (Button) findViewById(R.id.save);

        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<>();
        list.add("High Priority");
        list.add("Medium Priority");
        list.add("Low Priority");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Bundle bundle = getIntent().getExtras();

       if(getIntent().hasExtra("id"))
       {
           //Toast.makeText(getApplicationContext(),bundle.getString("id").toString(),Toast.LENGTH_LONG).show();
           ReminderOperation reminderOperation = new ReminderOperation(Main2Activity.this);
           reminderOperation.open();
           this.id = Integer.parseInt(bundle.getString("id"));
           Reminder reminder = reminderOperation.getReminder(this.id);
           titleEdit.setText(reminder.getTitle());
           descEdit.setText(reminder.getDescription());
           dateEdit.setText(reminder.getDate());
           timeEdit.setText(reminder.getTime());
           edit = true;
       }

        dateEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    dateEdit.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }

            }
        });


        timeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Get Current Time
               if(hasFocus){
                   final Calendar c = Calendar.getInstance();
                   mHour = c.get(Calendar.HOUR_OF_DAY);
                   mMinute = c.get(Calendar.MINUTE);
                   // Launch Time Picker Dialog
                   TimePickerDialog timePickerDialog = new TimePickerDialog(Main2Activity.this,
                           new TimePickerDialog.OnTimeSetListener() {

                               @Override
                               public void onTimeSet(TimePicker view, int hourOfDay,
                                                     int minute) {

                                   timeEdit.setText(hourOfDay + ":" + minute);
                               }
                           }, mHour, mMinute, false);
                   timePickerDialog.show();
               }
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderOperation reminderOperation = new ReminderOperation(Main2Activity.this);
                Reminder reminder = new Reminder();

                reminder.setTitle(titleEdit.getText().toString());
                reminder.setDescription(descEdit.getText().toString());
                if(spinner.getSelectedItem().toString() == "High Priority")
                    reminder.setPriority(1);
                if(spinner.getSelectedItem().toString() == "Medium Priority")
                    reminder.setPriority(2);
                if(spinner.getSelectedItem().toString() == "Low Priority")
                    reminder.setPriority(3);


                reminder.setDate(dateEdit.getText().toString());
                reminder.setTime(timeEdit.getText().toString());

                reminderOperation.open();

                if(edit){
                    reminder.setId(Main2Activity.this.id);
                    reminderOperation.updateRemider(reminder);
                }
                else
                    reminderOperation.addReminder(reminder);

                //Log.i("ID :",String.valueOf(i));

                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }





}
