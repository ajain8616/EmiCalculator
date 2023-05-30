package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Button ConBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        ConBtn = findViewById(R.id.ConBtn);
        ConBtn.setEnabled(false);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_twoWheeler) {
                ConBtn.setBackgroundColor(getResources().getColor(R.color.richPurple));
                ConBtn.setTag("twoWheeler");
            } else if (checkedId == R.id.rb_fourWheeler) {
                ConBtn.setBackgroundColor(getResources().getColor(R.color.navyBlue));
                ConBtn.setTag("fourWheeler");
            } else if (checkedId == R.id.rb_homeLoan) {
                ConBtn.setBackgroundColor(getResources().getColor(R.color.cyan));
                ConBtn.setTag("homeLoan");
            }
            ConBtn.setEnabled(true);
        });

        ConBtn.setOnClickListener(v -> {
            String selectedOption = (String) ConBtn.getTag();
            List<String> interestRateList = getInterestRateList(selectedOption);
            if (interestRateList != null && !interestRateList.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putStringArrayListExtra("interestRateList", (ArrayList<String>) interestRateList);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "No interest rates available for the selected option.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private List<String> getInterestRateList(String selectedOption) {
        List<String> interestRateList = new ArrayList<>();
        if (selectedOption.equals("twoWheeler")) {
            interestRateList.add("TwoWheeler ->  Bank A ->  10%");
            interestRateList.add("TwoWheeler ->  Bank B ->  20%");
            interestRateList.add("TwoWheeler ->  Bank C ->  30%");
            interestRateList.add("TwoWheeler ->  Bank D ->  40%");
            interestRateList.add("TwoWheeler ->  Bank E ->  50%");
            interestRateList.add("TwoWheeler ->  Bank F ->  60%");
            interestRateList.add("TwoWheeler ->  Bank G ->  70%");
            interestRateList.add("TwoWheeler ->  Bank H ->  80%");
            interestRateList.add("TwoWheeler ->  Bank I ->  90%");
        } else if (selectedOption.equals("fourWheeler")) {
            interestRateList.add("FourWheeler ->  Bank A ->  10%");
            interestRateList.add("FourWheeler ->  Bank B ->  20%");
            interestRateList.add("FourWheeler ->  Bank C ->  30%");
            interestRateList.add("FourWheeler ->  Bank D ->  40%");
            interestRateList.add("FourWheeler ->  Bank E ->  50%");
            interestRateList.add("FourWheeler ->  Bank F ->  60%");
            interestRateList.add("FourWheeler ->  Bank G ->  70%");
            interestRateList.add("FourWheeler ->  Bank H ->  80%");
            interestRateList.add("FourWheeler ->  Bank I ->  90%");
        } else if (selectedOption.equals("homeLoan")) {
            interestRateList.add("HomeLoan ->  Bank A ->  10%");
            interestRateList.add("HomeLoan ->  Bank B ->  20%");
            interestRateList.add("HomeLoan ->  Bank C ->  30%");
            interestRateList.add("HomeLoan ->  Bank D ->  40%");
            interestRateList.add("HomeLoan ->  Bank E ->  50%");
            interestRateList.add("HomeLoan ->  Bank F ->  60%");
            interestRateList.add("HomeLoan ->  Bank G ->  70%");
            interestRateList.add("HomeLoan ->  Bank H ->  80%");
            interestRateList.add("HomeLoan ->  Bank I ->  90%");
        }
        return interestRateList;
    }
}
