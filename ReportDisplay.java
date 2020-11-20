package com.example.transport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ReportDisplay extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ArrayList<Transport_Model> transport_models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_display);
        recyclerView = findViewById(R.id.recyle);
        databaseHelper = new DatabaseHelper(this);
        transport_models = databaseHelper.getAllTransport();
        ReportAdapter reportAdapter = new ReportAdapter(this,transport_models);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(reportAdapter);
    }
}