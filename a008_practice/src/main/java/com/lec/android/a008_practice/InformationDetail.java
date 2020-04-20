package com.lec.android.a008_practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InformationDetail extends AppCompatActivity {

    TextView dtName, dtAge, dtAddress;
    Button btnEdit, btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);

        dtName = findViewById(R.id.dtName);
        dtAge = findViewById(R.id.dtAge);
        dtAddress = findViewById(R.id.dtAddress);

        Intent intent = getIntent();
        Information inf = (Information) intent.getSerializableExtra("inf");
        dtName.setText(inf.getName());
        dtAge.setText(inf.getAge()+"");
        dtAddress.setText(inf.getAddress());

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}

