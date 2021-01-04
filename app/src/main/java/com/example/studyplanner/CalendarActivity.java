package com.example.studyplanner;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.stuff.studyplanner.R;

import java.util.Calendar;
import java.util.List;


public class CalendarActivity extends AppCompatActivity {

        CalendarView red_calendar;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);

            red_calendar = (CalendarView)
                    findViewById(R.id.calendarView);

            red_calendar
                    .setOnDateChangeListener(
                            new CalendarView
                                    .OnDateChangeListener() {
                                @Override
                                public void onSelectedDayChange(
                                        @NonNull CalendarView view,
                                        int year,
                                        int month,
                                        int dayOfMonth)
                                {
                                    String Date
                                            = dayOfMonth + "-"
                                            + (month + 1) + "-" + year;
                                }
                            });
        }
        public void AddCalendarEventTest(View view) {
            Calendar calendarEvent = Calendar.getInstance();
            Intent i = new Intent(Intent.ACTION_EDIT);
            i.setType("vnd.android.cursor.item/event");
            i.putExtra("beginTime", calendarEvent.getTimeInMillis());
            i.putExtra("allDay", false);
            //i.putExtra("rule", "FREQ=YEARLY");
            i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
            i.putExtra("title", "Kolokwium z ");
            i.putExtra("description", "KOLOKWIUM");
            i.putExtra("colorKey", "red");
            startActivity(i);
        }

    public void AddCalendarEventTask(View view) {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_EDIT);
        i.setType("vnd.android.cursor.item/event");
        i.putExtra("beginTime", calendarEvent.getTimeInMillis());
        i.putExtra("allDay", false);
        //i.putExtra("rule", "FREQ=YEARLY");
        i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
        i.putExtra("title", "Projekt na ");
        i.putExtra("description", "PROJEKT");
        i.putExtra("colorKey", 9);
        startActivity(i);
    }



}
