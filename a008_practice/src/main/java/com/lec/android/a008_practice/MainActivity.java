package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    InformationAdapter adapter;
    EditText etName, etAge, etAddress;
    RecyclerView rv;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAddress = findViewById(R.id.etAddress);

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        adapter = new InformationAdapter();

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    insertData(v);
                    rv.setAdapter(adapter);

            }
        });

        View.OnFocusChangeListener focus = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                ((EditText)v).setBackgroundColor(Color.YELLOW);
            } else {
                ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
            }
            }
        };

        etName.setOnFocusChangeListener(focus);
        etAge.setOnFocusChangeListener(focus);
        etAddress.setOnFocusChangeListener(focus);

    } // end onCreate()

    protected void insertData(View v){
        int idx = 0;
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String address = etAddress.getText().toString();

        List<String> nameList = new ArrayList<>();
        List<Integer> ageList = new ArrayList<>();
        List<String> addressList = new ArrayList<>();

        nameList.add(name);
        ageList.add(age);
        addressList.add(address);

        adapter.addItem(new Information(nameList.get(idx), ageList.get(idx), addressList.get(idx)));
        adapter.notifyDataSetChanged();
        idx++;
    } // end insertData()



} // end Activity
