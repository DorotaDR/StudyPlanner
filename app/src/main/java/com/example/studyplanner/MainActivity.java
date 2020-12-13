package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.studyplanner.Notifications.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.btn_timeTable)
//    Button btn_timeTable;
//    @BindView(R.id.btn_todoList)
//    Button btn_todoList;

    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        notificationManager = NotificationManagerCompat.from(this);
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

    public void sendNotifications(View w)
    {
        String title = "this is the title";
        String message = "this is the message";

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }

}