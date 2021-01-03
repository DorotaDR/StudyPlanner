package com.example.studyplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.studyplanner.adapters.ExpandableAdapter;
import com.example.studyplanner.models.Subject;
import com.example.studyplanner.models.ToDoClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TimeTableActivity extends AppCompatActivity {

    private int requestCode = 0;

    private ExpandableListView expandableListView;
    private ArrayList<String> listGroup;
    private HashMap<String, List<Subject>> listItemTrzy;
    private ExpandableAdapter adapter;

    private Spinner spinnerDays;
    private EditText edit_subject_title;
    private EditText edit_start_time;
    private EditText edit_end_time;
    private EditText edit_classroom;
    private Button btn_addSubject_save;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        listItemTrzy = new HashMap<>();

        listGroup = new ArrayList<>();
        String[] temp = getResources().getStringArray(R.array.week_days_array);
        for (int i = 0; i < temp.length; i++) {
            listGroup.add(temp[i]);
        }

        Gson gson = new Gson();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("timeTable", Context.MODE_PRIVATE);
        String jsonText = pref.getString("timeTable", "");
        if (jsonText.length() > 0) {
            Type type = new TypeToken<HashMap<String, List<Subject>>>() {
            }.getType();
            listItemTrzy = gson.fromJson(jsonText, type);
        }

        if (listItemTrzy.size() <= 0) {
            for (int i = 0; i < listGroup.size(); i++) {
                listItemTrzy.put(listGroup.get(i), new ArrayList<>());
            }
        }

        expandableListView = findViewById(R.id.expandable_listview);
        adapter = new ExpandableAdapter(this, listGroup, listItemTrzy);
        expandableListView.setAdapter(adapter);


    }


    public void addSubject(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_add_subject, viewGroup, false);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.show();
        setupSpinner();
    }

    private void setupSpinner() {
        spinnerDays = alertDialog.findViewById(R.id.spinnerDays);
        edit_subject_title = alertDialog.findViewById(R.id.edit_subject_title);
        edit_start_time = alertDialog.findViewById(R.id.edit_start_time);
        edit_end_time = alertDialog.findViewById(R.id.edit_end_time);
        edit_classroom = alertDialog.findViewById(R.id.edit_classroom);
        btn_addSubject_save = alertDialog.findViewById(R.id.btn_addSubject_save);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.week_days_array));
        spinnerDays.setAdapter(adapter);

        btn_addSubject_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDay = spinnerDays.getSelectedItem().toString();
                String subject = edit_subject_title.getText().toString();
                String start = edit_start_time.getText().toString();

                String end = edit_end_time.getText().toString();
                int room = -1;
                if (edit_classroom.getText().toString().equals("")) {
                    Toast.makeText(TimeTableActivity.this, "Podaj numer pokoju", Toast.LENGTH_SHORT).show();
                } else {
                    room = Integer.valueOf(edit_classroom.getText().toString());
                }

                if (subject.equals("") || start.equals("") || end.equals("")) {
                    Toast.makeText(TimeTableActivity.this, "Brakuje danych", Toast.LENGTH_SHORT).show();
                } else {
                    listItemTrzy.get(selectedDay).add(new Subject(subject, room, start, end));
                    Collections.sort(listItemTrzy.get(selectedDay));
                    adapter.notifyDataSetChanged();
                    alertDialog.cancel();

                    Intent intent = new Intent(TimeTableActivity.this, Notifications.class);
                    PendingIntent pIntent = PendingIntent.getBroadcast(TimeTableActivity.this, requestCode, intent, 0);
                    requestCode++;

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    // notification in 5 seconds
                    // for presentation purposes, comment those three lines below if needed
                    long timeAtButtonClick = System.currentTimeMillis();
                    long fiveSecsInMilis = 1000* 5;
                    alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + fiveSecsInMilis, pIntent);

                    // setting repeating alarms 15 minutes before classes
                    // uncomment if needed
                    /*
                    Calendar calendar = Calendar.getInstance();

                    if(selectedDay.equals("Poniedziałek"))
                        calendar.set(Calendar.DAY_OF_WEEK, 1);
                    else if(selectedDay.equals("Wtorek"))
                        calendar.set(Calendar.DAY_OF_WEEK, 2);
                    else if(selectedDay.equals("Środa"))
                        calendar.set(Calendar.DAY_OF_WEEK, 3);
                    else if(selectedDay.equals("Czwartek"))
                        calendar.set(Calendar.DAY_OF_WEEK, 4);
                    else
                        calendar.set(Calendar.DAY_OF_WEEK, 5);

                    String[] hourAndMins = start.split(":");

                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourAndMins[0]));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(hourAndMins[1]));
                    calendar.add(Calendar.MINUTE, -15);
                    calendar.set(Calendar.SECOND, 0);

                    //this condition is used for future reminder that means your reminder not fire for past time
                    if (calendar.before(Calendar.getInstance()))
                        calendar.add(Calendar.DATE, 7);

                    long interval =  7 * 24 * 60 * 60 * 1000; //once a week

                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pIntent);
                    Log.d("Alarm","Alarms set for " + selectedDay + " 15 minutes before " + start);

                    */
                }

            }
        });


        edit_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                edit_start_time.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();

            }
        });

        edit_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                edit_end_time.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        String sList = gson.toJson(listItemTrzy);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("timeTable", Context.MODE_PRIVATE);
        pref.edit().putString("timeTable", sList).apply();
    }
}