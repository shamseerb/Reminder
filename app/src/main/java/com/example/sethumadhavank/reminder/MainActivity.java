package com.example.sethumadhavank.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Reminder> arrayList = new ArrayList<Reminder>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);

                startActivity(i);
            }
        });


        listView = (ListView) findViewById(R.id.list);

        loadInListView();
        setListViewOnClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadInListView();
        setListViewOnClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void  loadInListView(){
        ReminderOperation reminderOperation = new ReminderOperation(MainActivity.this);
        reminderOperation.open();
        ArrayList<Reminder> data = reminderOperation.getAllReminder();

        Reminder reminder;
        listView.setAdapter(null);
        arrayList.clear();
        try {
            for(int i=0;i<data.size();i++){
                reminder = data.get(i);
                arrayList.add(reminder);
            }

            Log.e("List", "loadInListView: loaded array" );
            ReminderAdaper adapter = new ReminderAdaper(getApplicationContext(), R.layout.content_reimder_list, arrayList);
            listView.setAdapter(adapter);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void setListViewOnClick()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                TextView tvID =(TextView)view.findViewById(R.id.r_id);
                intent.putExtra("id",tvID.getText().toString());
                startActivity(intent);
            }
        });
    }
}
