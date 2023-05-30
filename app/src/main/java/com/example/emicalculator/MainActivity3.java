package com.example.emicalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class MainActivity3 extends AppCompatActivity {
    private EditText principalEditText, interestRateEditText, loanTermEditText;
    private RadioButton monthly, quarterly, halfYear, yearly;
    private int paymentFrequency;
    private Button calculateBtn, listCalculationBtn, PreviousBtn;
    private ArrayList<String> calculationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        principalEditText = findViewById(R.id.etAmount);
        interestRateEditText = findViewById(R.id.etInterestRate);
        loanTermEditText = findViewById(R.id.etYear);
        RadioGroup frequencyRadioGroup = findViewById(R.id.radioGroup);
        monthly = findViewById(R.id.monthlyRadioBtn);
        quarterly = findViewById(R.id.quarterlyRadioBtn);
        halfYear = findViewById(R.id.halfYearlyRadioBtn);
        yearly = findViewById(R.id.yearlyRadioBtn);
        calculateBtn = findViewById(R.id.btnCalculate);
        PreviousBtn = findViewById(R.id.PreviousBtn);
        listCalculationBtn = findViewById(R.id.listCalculation_Btn);
        calculateBtn.setEnabled(false);
        RadioGroup paymentFrequencyRadioGroup = findViewById(R.id.radioGroup);
        paymentFrequencyRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(principalEditText.getWindowToken(), 0);

            if (checkedId == R.id.monthlyRadioBtn) {
                calculateBtn.setBackgroundColor(getResources().getColor(R.color.richPurple));
            } else if (checkedId == R.id.quarterlyRadioBtn) {
                calculateBtn.setBackgroundColor(getResources().getColor(R.color.navyBlue));
            } else if (checkedId == R.id.halfYearlyRadioBtn) {
                calculateBtn.setBackgroundColor(getResources().getColor(R.color.cyan));
            } else if (checkedId == R.id.yearlyRadioBtn) {
                calculateBtn.setBackgroundColor(getResources().getColor(R.color.goldenYellow));
            }
            calculateBtn.setEnabled(true);
        });

        PreviousBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(intent);
        });
        calculateBtn.setOnClickListener(v -> {
            double principal = Double.parseDouble(principalEditText.getText().toString());
            double interestRate = Double.parseDouble(interestRateEditText.getText().toString());
            int loanTerm = Integer.parseInt(loanTermEditText.getText().toString());
            int selectedId = paymentFrequencyRadioGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.monthlyRadioBtn) {
                paymentFrequency = 12;
            } else if (selectedId == R.id.quarterlyRadioBtn) {
                paymentFrequency = 4;
            } else if (selectedId == R.id.halfYearlyRadioBtn) {
                paymentFrequency = 2;
            } else if (selectedId == R.id.yearlyRadioBtn) {
                paymentFrequency = 1;
            }
            double emi = calculateEmi(principal, interestRate, loanTerm);
            double totalPayment = emi * loanTerm * paymentFrequency;
            double interestPayable = totalPayment - principal;
            String resultText = String.format("EMI: %.2f\nInterest Payable: %.2f\nTotal Payment: %.2f", emi, interestPayable, totalPayment);
            TextView tvResult = findViewById(R.id.tvResult);
            tvResult.setText(resultText);
            String LoanTerms = "";
            if (selectedId == R.id.monthlyRadioBtn) {
                LoanTerms = "Monthly";
            } else if (selectedId == R.id.quarterlyRadioBtn) {
                LoanTerms = "Quarterly";
            } else if (selectedId == R.id.halfYearlyRadioBtn) {
                LoanTerms = "Half Yearly";
            } else if (selectedId == R.id.yearlyRadioBtn) {
                LoanTerms = "Yearly";
            }
            String calculationDetails = String.format("Principal: %.2f\nInterest Rate: %.2f\nLoan Term: %d\nEMI: %.2f\nInterest Payable: %.2f\nTotal Payment: %.2f\nLoan Terms: %s",
                    principal, interestRate, loanTerm, emi, interestPayable, totalPayment, LoanTerms);
            saveCalculationDetails(calculationDetails);
            calculationList.add(calculationDetails);
        });
        principalEditText.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(principalEditText, InputMethodManager.SHOW_IMPLICIT);
        });
        listCalculationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, CalculationListActivity.class);
            intent.putStringArrayListExtra("calculationList", calculationList);
            startActivity(intent);
        });
    }
    private void saveCalculationDetails(String calculationDetails) {
        SharedPreferences sharedPreferences = getSharedPreferences("CalculationData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int count = sharedPreferences.getInt("count", 0);
        editor.putString("calculation_" + count, calculationDetails);
        editor.putInt("count", count + 1);
        editor.apply();
    }
    public double calculateEmi(double principal, double interestRate, int loanTerm) {
        double monthlyInterestRate = interestRate / (paymentFrequency * 100);
        int numberOfPayments = loanTerm * paymentFrequency;
        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
        return emi;
    }
}
