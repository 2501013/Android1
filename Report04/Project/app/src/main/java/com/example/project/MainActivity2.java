package com.example.project;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private SeekBar seekWeight, seekHeight;
    private TextView tvWeight, tvHeight, tvResult;

    // 초기값 설정
    private double weight = 35.0;
    private double height = 90.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        seekWeight = findViewById(R.id.seekWeight);
        seekHeight = findViewById(R.id.seekHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);
        tvResult = findViewById(R.id.tvResult);

        // 몸무게 SeekBar 리스너 (최소 35.0, 0.5씩 증가 -> max는 230)
        seekWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weight = 35.0 + (progress * 0.5);
                tvWeight.setText(String.format(Locale.getDefault(), "%.1f Kg", weight));
                calculateBMI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 키 SeekBar 리스너 (최소 90.0, 0.5씩 증가 -> max는 280)
        seekHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height = 90.0 + (progress * 0.5);
                tvHeight.setText(String.format(Locale.getDefault(), "%.1f Cm", height));
                calculateBMI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void calculateBMI() {
        // 키를 미터(m) 단위로 변환
        double heightM = height / 100.0;
        double bmi = weight / (heightM * heightM);

        String condition;
        if (bmi < 18.5) {
            condition = "저체중입니다!";
        } else if (bmi < 23.0) {
            condition = "정상 체중 입니다!";
        } else if (bmi < 25.0) {
            condition = "과체중 입니다!";
        } else {
            condition = "비만 입니다!";
        }

        tvResult.setText(String.format(Locale.getDefault(), "BMI : %.2f\n당신은 %s", bmi, condition));
    }
}