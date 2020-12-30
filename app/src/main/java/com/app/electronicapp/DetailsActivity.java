package com.app.electronicapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

public class DetailsActivity extends AppCompatActivity {
    private String name;
    private String date;
    private String time;
    private String phone;
    private String image;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);






        Intent intent = getIntent();

        TextView Name  = (TextView) findViewById(R.id.name);
        TextView Phone  = (TextView) findViewById(R.id.Num);
        TextView Time  = (TextView) findViewById(R.id.TIME);
        TextView Date  = (TextView) findViewById(R.id.ttare5);
        ImageView Image = (ImageView)findViewById(R.id.IMAGE);


        name=getIntent().getStringExtra("name").toString();
        time=getIntent().getStringExtra("time").toString();
        date=getIntent().getStringExtra("date").toString();
      phone=getIntent().getStringExtra("phone").toString();
        image=getIntent().getStringExtra("image").toString();


        Name.setText(name);
        Phone.setText(phone);
        Date.setText(time);
        Time.setText(date);
        Glide.with(this ).load(image).override(300, 300) .into(Image);



    }
}
