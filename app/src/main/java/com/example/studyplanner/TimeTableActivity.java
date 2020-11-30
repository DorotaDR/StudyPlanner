package com.example.studyplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.example.studyplanner.adapters.ExpandableAdapter;
import com.example.studyplanner.models.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeTableActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ArrayList<String> listGroup;
    HashMap<String, List<Subject>> listItemTrzy;
    ExpandableAdapter adapter;
    Spinner spinnerDays;

    private AlertDialog alertDialog;
    ArrayList<ArrayList<Subject>> subjectsDay = new ArrayList<>();

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        listGroup = new ArrayList<>();
        listGroup.add("Poniedziałek");
        listGroup.add("Wtorek");
        listItemTrzy = new HashMap<>();
        ArrayList<Subject> testList = new ArrayList<>();
//        testList.add(new Subject("Nazwa1", 444, "8:20"));
//        testList.add(new Subject("Nazwa2", 555, "9:20"));
//        testList.add(new Subject("Nazwa3", 666, "10:20"));
        subjectsDay.add(testList);
        listItemTrzy.put(listGroup.get(0),subjectsDay.get(0));
        testList = new ArrayList<>();
        subjectsDay.add(testList);
        listItemTrzy.put(listGroup.get(1),subjectsDay.get(1));

        expandableListView = findViewById(R.id.expandable_listview);
        adapter = new ExpandableAdapter(this, listGroup, listItemTrzy);
        expandableListView.setAdapter(adapter);
//        adapter.notifyDataSetChanged(); ta metoda uaktualnia liste
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

    private void setupSpinner(){
        spinnerDays=  alertDialog.findViewById(R.id.spinnerDays);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.week_days_array));//setting the country_array to spinner
        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}