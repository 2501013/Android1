package com.example.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar1;
    SeekBar seekBar2;

    TextView txtNum1;
    TextView txtNum2;
    TextView txtResult;

    Button btnLcm;

    // 입력 여부 확인
    boolean input1 = false;
    boolean input2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );


        // 위젯 연결
        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);

        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);

        txtResult = findViewById(R.id.txtResult);

        btnLcm = findViewById(R.id.btnLcm);


        // 첫 번째 SeekBar
        seekBar1.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        if (fromUser) {

                            input1 = true;

                            int num = progress + 1;

                            txtNum1.setText(
                                    "자연수 입력(" + num + ")"
                            );
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(
                            SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(
                            SeekBar seekBar) {
                    }
                }
        );


        // 두 번째 SeekBar
        seekBar2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        if (fromUser) {

                            input2 = true;

                            int num = progress + 1;

                            txtNum2.setText(
                                    "자연수 입력(" + num + ")"
                            );
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(
                            SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(
                            SeekBar seekBar) {
                    }
                }
        );


        // 버튼 클릭
        btnLcm.setOnClickListener(v -> {

            // 둘 중 하나라도 입력하지 않았으면
            if (!input1 || !input2) {

                Toast.makeText(
                        MainActivity.this,
                        "입력 바람",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // 자연수 가져오기
            int num1 = seekBar1.getProgress() + 1;
            int num2 = seekBar2.getProgress() + 1;


            // 최대공약수
            int gcd = getGCD(num1, num2);


            // 최소공배수
            int lcm = (num1 * num2) / gcd;


            // 결과 출력

            txtResult.setText(
                    num1 + ", " + num2
                            + "의 GCD(최대공약수)는 " + gcd + "입니다.\n"
                            + num1 + ", " + num2
                            + "의 LCM(최소공배수)는 " + lcm + "입니다."
            );


        });
    }


    // 최대공약수 구하기
    private int getGCD(int a, int b) {

        while (b != 0) {

            int temp = a % b;

            a = b;
            b = temp;
        }

        return a;
    }
}