package com.app.electronicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ID extends AppCompatActivity {
    EditText TextID;
    Button Next;
    String value1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d);
        Next = findViewById(R.id.btnID);
        TextID=findViewById(R.id.textid);


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value1 = (String)TextID.getText().toString();
                Intent intent = new Intent(ID.this,updateactivity.class);
                intent.putExtra("updateID", value1);
                startActivity(intent);
            }


        });
    }}


