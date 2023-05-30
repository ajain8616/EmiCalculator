package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView interestRateListView = findViewById(R.id.list_item);
        Button NxtBtn=findViewById(R.id.NextBtn);
        Button PreBtn=findViewById(R.id.PreBtn);
        List<String> interestRateList = getIntent().getStringArrayListExtra("interestRateList");
        if (interestRateList != null && !interestRateList.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, interestRateList);
            interestRateListView.setAdapter(adapter);
        }
           NxtBtn.setOnClickListener(v -> {
               Intent intent=new Intent(MainActivity2.this, MainActivity3.class);
               startActivity(intent);
           });
           PreBtn.setOnClickListener(v -> {
               Intent intent=new Intent(MainActivity2.this, MainActivity.class);
               startActivity(intent);
           });
    }
}
