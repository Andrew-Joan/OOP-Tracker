package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Record> records;
    private ArrayAdapter<Record> adapter;
    private ListView listView;
    private TextView totalTimeTextView;
    private Button btnAdd;
    // Declare the OnItemClickListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        records = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);

        listView = findViewById(R.id.listView);
        totalTimeTextView = findViewById(R.id.totalTimeTextView);
        btnAdd = findViewById(R.id.btnAdd);

        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(this);

        listView.setOnItemClickListener(this);
    }

    private void updateTotalTime() {
        double totalTime = 0;
        for (Record record : records) {
            totalTime += record.getTime();
        }

        if (totalTime >= 120) {
            totalTimeTextView.setText(String.format("Total Time: %.1f Hours", totalTime / 120));
        } else if (totalTime >= 60 && totalTime < 120) {
            totalTimeTextView.setText(String.format("Total Time: %.1f Hour", totalTime / 60));
        } else {
            totalTimeTextView.setText(String.format("Total Time: %.1f Minutes", totalTime));
        }
    }

    public void addRecord(Record record) {
        records.add(record);
        adapter.notifyDataSetChanged();
        updateTotalTime();
    }

    private void openAddRecordDialog() {
        DialogUtils.openAddRecordDialog(this, new DialogUtils.AddRecordListener() {
            @Override
            public void onAddRecord(String exerciseName, double time) {
                ExerciseRecord newRecord = new ExerciseRecord(exerciseName, time);
                addRecord(newRecord);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == btnAdd) {
            openAddRecordDialog();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(view == listView){
            Record record = records.get(position);
            Toast.makeText(MainActivity.this, "Selected: " + record.getDetails(), Toast.LENGTH_SHORT).show();
        }
    }
}