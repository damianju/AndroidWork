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
    double cal1;
    double cal2;
    String operator;



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


        btnE.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et.getText().equals("") && !operator.equals("")) {
                    cal2 = Double.parseDouble(et.getText().toString());
                    try{if(operator.equals("+")){
                        et.setText((cal1+cal2)+"");
                    } else if(operator.equals("-")){
                        et.setText((cal1-cal2)+"");
                    } else if(operator.equals("×")){
                        et.setText((cal1*cal2)+"");
                    }  else{
                        et.setText((cal1/cal2)+"");
                    }
                    } catch (ArithmeticException e){
                    }
                }
            } // end onClick()
        });

        btnC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });

        OnClickListener cal = new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal1 = Double.parseDouble(et.getText().toString());
                et.setText(et.getText().append((String)((Button)v).getText()));
                operator = (String)((Button)v).getText();
            }
        };

        OnClickListener num = new OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText((String)((Button)v).getText());
            }
        };

        btn0.setOnClickListener(num);
        btn1.setOnClickListener(num);
        btn2.setOnClickListener(num);
        btn3.setOnClickListener(num);
        btn4.setOnClickListener(num);
        btn5.setOnClickListener(num);
        btn6.setOnClickListener(num);
        btn7.setOnClickListener(num);
        btn8.setOnClickListener(num);
        btn9.setOnClickListener(num);
        btnA.setOnClickListener(cal);
        btnS.setOnClickListener(cal);
        btnM.setOnClickListener(cal);
        btnD.setOnClickListener(cal);


    } // end onCreate()

} // end Activity


