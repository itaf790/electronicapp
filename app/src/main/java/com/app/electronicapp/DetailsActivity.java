package com.app.electronicapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

public class DetailsActivity extends AppCompatActivity {
    private String firstname ;
    private String lname;
    private String adress;
    private String phone;
    private String image;
    private String gender;
    private String date;
    private String time;
    private String duration;



    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Intent intent = getIntent();

        TextView Fname  = (TextView) findViewById(R.id.name);
        TextView Lastname  = (TextView) findViewById(R.id.lastnamee);
        TextView Phone  = (TextView) findViewById(R.id.number);
        TextView Address  = (TextView) findViewById(R.id.adress);
        TextView Gender  = (TextView) findViewById(R.id.gender);
        ImageView Image = (ImageView)findViewById(R.id.IMAGE);
        TextView datee  = (TextView) findViewById(R.id.datee);
        TextView timee  = (TextView) findViewById(R.id.TTime);
        TextView durationn  = (TextView) findViewById(R.id.DUR);



        phone=getIntent().getStringExtra("phone").toString();
        lname=getIntent().getStringExtra("last name").toString();

        adress=getIntent().getStringExtra("adress").toString();

        image=getIntent().getStringExtra("image").toString();
        gender=getIntent().getStringExtra("gender").toString();
        date=getIntent().getStringExtra("date").toString();
        time=getIntent().getStringExtra("time").toString();
        duration=getIntent().getStringExtra("duration").toString();






        Phone.setText(phone);
        Lastname.setText(lname);
        Address.setText(adress);
        datee.setText(date);
        timee.setText(time);
        Gender.setText(gender);
        durationn.setText(duration);

        Glide.with(this ).load(image).override(300, 300) .into(Image);

        Gender.setText(gender);

    }
}
