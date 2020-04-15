package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;



public class Main2Activity extends AppCompatActivity{
    // 과제 계산기 앱 만들기
    EditText et;

    int a;
    int where =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et = findViewById(R.id.et);

        Button btn9 = findViewById(R.id.btn9);
        Button btn8 = findViewById(R.id.btn8);
        Button btn7 = findViewById(R.id.btn7);
        Button btn6 = findViewById(R.id.btn6);
        Button btn5 = findViewById(R.id.btn5);
        Button btn4 = findViewById(R.id.btn4);
        Button btn3 = findViewById(R.id.btn3);
        Button btn2 = findViewById(R.id.btn2);
        Button btn1 = findViewById(R.id.btn1);
        Button btn0 = findViewById(R.id.btn0);
        Button btnA = findViewById(R.id.btnA);
        Button btnS = findViewById(R.id.btnS);
        Button btnM = findViewById(R.id.btnM);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnC = findViewById(R.id.btnC);

        OnClickListener cl = new OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v == btn0) {
                    et.setText(et.getText().toString() + 0);
                } else if (v == btn1) {
                    et.setText(et.getText().toString() + 1);
                } else if (v == btn2) {
                    et.setText(et.getText().toString() + 2);
                } else if (v == btn3) {
                    et.setText(et.getText().toString() + 3);
                } else if (v == btn4) {
                    et.setText(et.getText().toString() + 4);
                } else if (v == btn5) {
                    et.setText(et.getText().toString() + 5);
                } else if (v == btn6) {
                    et.setText(et.getText().toString() + 6);
                } else if (v == btn7) {
                    et.setText(et.getText().toString() + 7);
                } else if (v == btn8) {
                    et.setText(et.getText().toString() + 8);
                } else if (v == btn9) {
                    et.setText(et.getText().toString() + 9);
                } else if(v== btnA){
                    a = Integer.valueOf(et.getText().toString().trim());
                    et.setText("");
                    where = 1;
                } else if(v== btnS) {
                    a = Integer.valueOf(et.getText().toString().trim());
                    et.setText("");
                    where = 2;
                } else if(v== btnM){
                    a = Integer.valueOf(et.getText().toString().trim());
                    et.setText("");
                    where = 3;
                } else if(v== btnD) {
                    a = Integer.valueOf(et.getText().toString().trim());
                    et.setText("");
                    where = 4;
                } else if (v==btnE) {
                    if(where==1) {
                        a = a + Integer.valueOf(et.getText().toString().trim());
                        et.setText(Integer.toString(a));
                    } else if(where==2) {
                        a = a - Integer.valueOf(et.getText().toString().trim());
                        et.setText(Integer.toString(a));
                    } else if(where==3) {
                        a = a * Integer.valueOf(et.getText().toString().trim());
                        et.setText(Integer.toString(a));
                    } else if(where==4) {
                        try {
                            a = a / Integer.valueOf(et.getText().toString().trim());
                            et.setText(Integer.toString(a));
                        } catch (ArithmeticException e) {
                            onClick(btnC);
                        }
                    }
                } else if(v==btnC) {
                    et.setText("");
                }
            }
        };

        btn0.setOnClickListener(cl);
        btn1.setOnClickListener(cl);
        btn2.setOnClickListener(cl);
        btn3.setOnClickListener(cl);
        btn4.setOnClickListener(cl);
        btn5.setOnClickListener(cl);
        btn6.setOnClickListener(cl);
        btn7.setOnClickListener(cl);
        btn8.setOnClickListener(cl);
        btn9.setOnClickListener(cl);
        btnA.setOnClickListener(cl);
        btnS.setOnClickListener(cl);
        btnM.setOnClickListener(cl);
        btnD.setOnClickListener(cl);
        btnE.setOnClickListener(cl);
        btnC.setOnClickListener(cl);

    } // end onCreate()

} // end Activity


