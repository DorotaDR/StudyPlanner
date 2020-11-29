package com.example.studyplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.studyplanner.adapters.ToDoAdapter;
import com.example.studyplanner.models.ToDoClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity {

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
        todoList.add(new ToDoClass(et_title.getText().toString(), et_subject.getText().toString(), et_date.getText().toString(), false));
        alertDialog.cancel();

        mAdapter = new ToDoAdapter(todoList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        String sList = gson.toJson(todoList);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("todolist", Context.MODE_PRIVATE);
        pref.edit().putString("list", sList).apply();

    }
}