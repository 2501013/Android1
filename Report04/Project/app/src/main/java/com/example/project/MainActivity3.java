package com.example.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    private SeekBar seekCelsius, seekFahrenheit;
    private TextView tvCelsius, tvFahrenheit, tvResult;
    private RadioGroup radioGroup;
    private RadioButton rbCtoF, rbFtoC;
    private Button btnConvert, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        seekCelsius = findViewById(R.id.seekCelsius);
        seekFahrenheit = findViewById(R.id.seekFahrenheit);
        tvCelsius = findViewById(R.id.tvCelsius);
        tvFahrenheit = findViewById(R.id.tvFahrenheit);
        tvResult = findViewById(R.id.tvResult);
        radioGroup = findViewById(R.id.radioGroup);
        rbCtoF = findViewById(R.id.rbCtoF);
        rbFtoC = findViewById(R.id.rbFtoC);
        btnConvert = findViewById(R.id.btnConvert);
        btnReset = findViewById(R.id.btnReset);

        setSeekBarsEnabled(false);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbCtoF) {
                seekCelsius.setEnabled(true);
                seekFahrenheit.setEnabled(false);
            } else if (checkedId == R.id.rbFtoC) {
                seekCelsius.setEnabled(false);
                seekFahrenheit.setEnabled(true);
            }
        });

        seekCelsius.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        double celsius = progress - 100.0;

                        tvCelsius.setText(
                                String.format(
                                        Locale.getDefault(),
                                        "섭씨온도(%.1f ℃)",
                                        celsius
                                )
                        );
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        seekFahrenheit.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        double fahrenheit = progress - 100.0;

                        tvFahrenheit.setText(
                                String.format(
                                        Locale.getDefault(),
                                        "화씨온도(%.1f ℉)",
                                        fahrenheit
                                )
                        );
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        btnConvert.setOnClickListener(v -> {

            int checkedId = radioGroup.getCheckedRadioButtonId();

            if (checkedId == -1) {
                Toast.makeText(
                        MainActivity3.this,
                        "선택해주세요",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            if (checkedId == R.id.rbCtoF) {

                double celsius =
                        seekCelsius.getProgress() - 100.0;

                double fahrenheit =
                        (celsius * 1.8) + 32;

                tvResult.setText(
                        String.format(
                                Locale.getDefault(),
                                "섭씨온도 : %.2f ℃, 화씨온도 : %.2f ℉",
                                celsius,
                                fahrenheit
                        )
                );

            } else if (checkedId == R.id.rbFtoC) {

                double fahrenheit =
                        seekFahrenheit.getProgress() - 100.0;

                double celsius =
                        (fahrenheit - 32) / 1.8;

                tvResult.setText(
                        String.format(
                                Locale.getDefault(),
                                "섭씨온도 : %.2f ℃, 화씨온도 : %.2f ℉",
                                celsius,
                                fahrenheit
                        )
                );
            }
        });

        btnReset.setOnClickListener(v -> {

            radioGroup.clearCheck();

            seekCelsius.setProgress(100);
            seekFahrenheit.setProgress(100);

            tvCelsius.setText("섭씨온도(0.0 ℃)");
            tvFahrenheit.setText("화씨온도(0.0 ℉)");
            tvResult.setText("");

            setSeekBarsEnabled(false);
        });
    }

    private void setSeekBarsEnabled(boolean enabled) {
        seekCelsius.setEnabled(enabled);
        seekFahrenheit.setEnabled(enabled);
    }
}