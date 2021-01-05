package com.app.electronicapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

public class DetailsActivity extends AppCompatActivity {
    private String fname;
    private String lname;
    private String adress;
    private String phone;
    private String image;
    private String gender;


    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Intent intent = getIntent();

        TextView firstName = (TextView) findViewById(R.id.name);
        TextView Phone  = (TextView) findViewById(R.id.number);
        TextView Lastname  = (TextView) findViewById(R.id.lastnamee);
        TextView Address  = (TextView) findViewById(R.id.adress);
        ImageView Image = (ImageView)findViewById(R.id.IMAGE);
        TextView Gender  = (TextView) findViewById(R.id.gender);


       fname=getIntent().getStringExtra("first name").toString();
        adress=getIntent().getStringExtra("adress").toString();
        lname=getIntent().getStringExtra("last name").toString();
        phone=getIntent().getStringExtra("phone").toString();
        image=getIntent().getStringExtra("image").toString();
      gender=getIntent().getStringExtra("gender").toString();


       firstName.setText(fname);
        Phone.setText(phone);
        Lastname.setText(lname);
        Address.setText(adress);
       Gender.setText(gender);

        Glide.with(this ).load(image).override(300, 300) .into(Image);



    }
}
