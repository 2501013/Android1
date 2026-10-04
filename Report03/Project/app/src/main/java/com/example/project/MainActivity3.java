package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity3 extends AppCompatActivity {

    private CheckBox chkReading, chkTravel, chkGame;
    private AppCompatButton btnSelect;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        chkReading = findViewById(R.id.chkReading);
        chkTravel = findViewById(R.id.chkTravel);
        chkGame = findViewById(R.id.chkGame);
        btnSelect = findViewById(R.id.btnSelect);
        tvResult = findViewById(R.id.tvResult);


        btnSelect.setOnClickListener(v -> {
            StringBuilder selectedHobbies = new StringBuilder();

            if (chkReading.isChecked()) {
                selectedHobbies.append("독서");
            }
            if (chkTravel.isChecked()) {
                if (selectedHobbies.length() > 0) selectedHobbies.append(", ");
                selectedHobbies.append("여행");
            }
            if (chkGame.isChecked()) {
                if (selectedHobbies.length() > 0) selectedHobbies.append(", ");
                selectedHobbies.append("게임");
            }


            if (selectedHobbies.length() == 0) {
                Toast.makeText(MainActivity3.this, "취미를 선택해주세요.", Toast.LENGTH_SHORT).show();
                tvResult.setText("");
            } else {

                tvResult.setText("선택한 취미 : " + selectedHobbies.toString());
            }
        });
    }
}