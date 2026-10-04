package com.example.project;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity4 extends AppCompatActivity {

    private EditText etRadius;
    private AppCompatButton btnCalcArea;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        etRadius = findViewById(R.id.etRadius);
        btnCalcArea = findViewById(R.id.btnCalcArea);
        tvResult = findViewById(R.id.tvResult);

        btnCalcArea.setOnClickListener(v -> {
            hideKeyboard();

            String radiusStr = etRadius.getText().toString().trim();

            if (radiusStr.isEmpty()) {
                Toast.makeText(this, "입력 바람", Toast.LENGTH_SHORT).show();
                tvResult.setVisibility(View.GONE);
                return;
            }

            double radius = Double.parseDouble(radiusStr);

            if (radius <= 0) {
                Toast.makeText(this, "반지름은 0보다 커야 합니다.", Toast.LENGTH_SHORT).show();
                tvResult.setVisibility(View.GONE);
                return;
            }

            double area = 3.141592 * radius * radius;


            String radiusNumStr = String.format("%.2f", radius);
            String areaNumStr = String.format("%,.2f", area);

            String line1 = String.format("원의 반지름 : %s cm", radiusNumStr);
            String line2 = String.format("원의 면적 : %s ㎠", areaNumStr);
            String fullText = line1 + "\n" + line2;

            SpannableString spannableString = new SpannableString(fullText);


            int radiusStart = line1.indexOf(":") + 2;
            int radiusEnd = radiusStart + radiusNumStr.length();
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#D32F2F")), radiusStart, radiusEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            int areaStart = fullText.indexOf(line2) + line2.indexOf(":") + 2;
            int areaEnd = areaStart + areaNumStr.length();
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#D32F2F")), areaStart, areaEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvResult.setText(spannableString);
            tvResult.setTextColor(Color.parseColor("#555555"));
            tvResult.setVisibility(View.VISIBLE);
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}