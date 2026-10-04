package com.example.project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etId, etPassword;
    private Button btnLogin;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etId = findViewById(R.id.etId);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvResult = findViewById(R.id.tvResult);


        btnLogin.setOnClickListener(v -> {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null && getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }


            String userId = etId.getText().toString().trim();
            String userPw = etPassword.getText().toString().trim();


            if (userId.isEmpty() || userPw.isEmpty()) {
                Toast.makeText(MainActivity.this, "데이터 입력 해주세요", Toast.LENGTH_SHORT).show();
                tvResult.setVisibility(View.GONE);
            } else {

                tvResult.setText("아이디 : " + userId + " 비밀번호 : " + userPw);
                tvResult.setVisibility(View.VISIBLE);
            }
        });
    }
}