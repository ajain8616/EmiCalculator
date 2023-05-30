package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class CalculationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_list);
        ListView calculationListView = findViewById(R.id.calculationListView);
        Button calculation_PageBtn = findViewById(R.id.Calculation);
        ArrayList<String> calculationList = getCalculationList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, calculationList);
        calculationListView.setAdapter(adapter);
        calculation_PageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CalculationListActivity.this, MainActivity3.class);
            startActivity(intent);
        });
    }
    private ArrayList<String> getCalculationList() {
        ArrayList<String> calculationList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("CalculationData", MODE_PRIVATE);
        int count = sharedPreferences.getInt("count", 0);
        for (int i = 0; i < count; i++) {
            String calculationDetails = sharedPreferences.getString("calculation_" + i, "");
            if (!calculationDetails.isEmpty()) {
                calculationList.add(calculationDetails);
            }
        }
        return calculationList;
    }
}
