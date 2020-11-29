package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.btn_timeTable)
//    Button btn_timeTable;
//    @BindView(R.id.btn_todoList)
//    Button btn_todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_timeTable)
    public void timeTable_Start() {
        Intent intent = new Intent(this, TimeTableActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_todoList)
    public void todoList_Start() {
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }

}