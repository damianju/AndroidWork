package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView tvResult;
    EditText op1, op2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvResult = findViewById(R.id.tvResult);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oper1 = op1.getText().toString();
                String oper2 = op2.getText().toString();

                int a,b;

               // if(oper1 !=null && !oper1.trim())


            }
        });


        op1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((EditText) v).setBackgroundColor(Color.YELLOW);
                } else {
                    ((EditText) v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
            });


    } // end onCreate
} // end Main
