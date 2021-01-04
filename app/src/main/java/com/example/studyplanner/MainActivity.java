package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.studyplanner.Notifications.CHANNEL_1_ID;

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
        createNotificationChannels();
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
	
	@OnClick(R.id.btn_Calendar)
    public void timeTable_Start() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void createNotificationChannels()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("this is channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }

    }
    @OnClick(R.id.btn_newspage)
    public void news_Start() {
        Intent intent = new Intent(this, NewsPageActivity.class);
        startActivity(intent);
    }

}