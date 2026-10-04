package com.example.project;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private EditText etBirthYear, etAge;
    private AppCompatButton btnCalcAge, btnCalcBirthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setSoftInputMode(
                android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        etBirthYear = findViewById(R.id.etBirthYear);
        etAge = findViewById(R.id.etAge);
        btnCalcAge = findViewById(R.id.btnCalcAge);
        btnCalcBirthYear = findViewById(R.id.btnCalcBirthYear);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        btnCalcAge.setOnClickListener(v -> {
            hideKeyboard();

            String inputStr = etBirthYear.getText().toString().trim();

            if (inputStr.isEmpty()) {
                Toast.makeText(this, "태어난 연도를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            int birthYear = Integer.parseInt(inputStr);

            if (birthYear < 0 || birthYear > currentYear) {
                Toast.makeText(
                        this,
                        "출생 연도는 0 ~ " + currentYear + " 사이로 입력해주세요.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int age = currentYear - birthYear + 1;

            Toast.makeText(
                    this,
                    "당신의 나이는 " + age + "세입니다.",
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnCalcBirthYear.setOnClickListener(v -> {
            hideKeyboard();

            String inputStr = etAge.getText().toString().trim();

            if (inputStr.isEmpty()) {
                Toast.makeText(this, "나이를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(inputStr);

            if (age < 1 || age > 130) {
                Toast.makeText(
                        this,
                        "나이는 1 ~ 130살 사이로 입력해주세요.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int birthYear = currentYear - age + 1;

            Toast.makeText(
                    this,
                    "당신의 태어난 해는 " + birthYear + "년입니다.",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
}