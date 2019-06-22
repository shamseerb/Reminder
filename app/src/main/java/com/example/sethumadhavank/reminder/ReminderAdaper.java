package com.example.sethumadhavank.reminder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReminderAdaper extends ArrayAdapter<Reminder> {

    List<Reminder> reminderList;
    Context context;
    int resource;


    public ReminderAdaper(Context context, int resource, List<Reminder> reminders){
        super(context, resource, reminders);
        this.context = context;
        this.resource = resource;
        this.reminderList = reminders;

    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        //we need to get the view of the xml for our list item
//        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //getting the view
        View view = layoutInflater.inflate(this.resource, null, false);
//
//        //getting the view elements of the list from the view

          TextView textViewTitle = view.findViewById(R.id.title);
          TextView textViewDate = view.findViewById(R.id.date);
          TextView textViewID = view.findViewById(R.id.r_id);

          ImageView p_image = view.findViewById(R.id.p_image);
          ImageView d_image = view.findViewById(R.id.delete);
//

        textViewTitle.setTextColor(Color.BLACK);
        textViewDate.setTextColor(Color.BLACK);

        textViewTitle.setVisibility(View.VISIBLE);
        textViewDate.setVisibility(View.VISIBLE);
        p_image.setVisibility(View.VISIBLE);
        d_image.setVisibility(View.INVISIBLE);


        Reminder list = reminderList.get(position);
        d_image.setImageResource(R.mipmap.delete);
        p_image.setImageResource(R.mipmap.r_p_1);

        if(list.getPriority() == 1){
            p_image.setImageResource(R.mipmap.r_p_1);
        }

        if(list.getPriority() == 2){
            p_image.setImageResource(R.mipmap.r_p_2);
        }

        if(list.getPriority() == 3){
            p_image.setImageResource(R.mipmap.r_p_3);
        }

        textViewTitle.setTextSize(20);
        textViewDate.setText(list.getDate() + " - " + list.getTime());
        textViewTitle.setText(list.getTitle());
        textViewID.setText( String.valueOf(list.getId()));
        return view;
    }

}
