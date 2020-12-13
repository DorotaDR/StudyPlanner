package com.example.studyplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studyplanner.adapters.ToDoAdapter;
import com.example.studyplanner.models.DatePickerFragment;
import com.example.studyplanner.models.ToDoClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ToDoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText et_title;
    private EditText et_subject;
    private EditText et_date;

    private ArrayList<ToDoClass> todoList = new ArrayList<>();
    private AlertDialog alertDialog;

    private RecyclerView mRecyclerView;
    private ToDoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Gson gson = new Gson();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("todolist", Context.MODE_PRIVATE);
        String jsonText = pref.getString("list", "");
        if (jsonText.length() > 0) {
            Type type = new TypeToken<ArrayList<ToDoClass>>() {
                }.getType();
            todoList = gson.fromJson(jsonText, type);
        }
        if (todoList.size() == 0) {
            Toast.makeText(this, "Oł jea! Brak zadań", Toast.LENGTH_SHORT).show();
        }

        mRecyclerView = findViewById(R.id.rec_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ToDoAdapter(todoList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addToDoItem(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_todo_item, viewGroup, false);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.show();

        et_title = alertDialog.findViewById(R.id.et_title);
        et_subject = alertDialog.findViewById(R.id.et_subject);
        et_date = alertDialog.findViewById(R.id.et_date);
    }

    public void addItem(View view) {
        String title = et_title.getText().toString();
        String subject = et_subject.getText().toString();
        String date = et_date.getText().toString();
        if (title.length() > 0 && subject.length() > 0 && date.length() > 0) {
            todoList.add(new ToDoClass(title, subject, date, false));
            alertDialog.cancel();
            mAdapter = new ToDoAdapter(todoList);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        String sList = gson.toJson(todoList);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("todolist", Context.MODE_PRIVATE);
        pref.edit().putString("list", sList).apply();
    }

    public void input_date_calendar(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        et_date.setText(currentDateString);
    }


}