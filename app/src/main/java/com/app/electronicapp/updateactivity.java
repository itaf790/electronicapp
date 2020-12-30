package com.app.electronicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class updateactivity extends AppCompatActivity {
    Button buttonUpdate;

    String value;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    TextView ID;
    EditText Name;
    EditText phone;
    EditText time;
    EditText date;

    String dburl,dbname,dbtime,dbdate,dbphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateactivity);


        Name  = (EditText) findViewById(R.id.upname);
        date  = (EditText) findViewById(R.id.datee);
        time = (EditText) findViewById(R.id.tiime);
        phone  = (EditText) findViewById(R.id.phhone);
        value=getIntent().getStringExtra("updateID").toString();

        buttonUpdate=findViewById(R.id.btnUpdate);
        ID = findViewById(R.id.updateID);
        ID.setText(value);

        databaseReference =
                FirebaseDatabase.getInstance().getReference(MainActivity.Database_Path);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                dbname = snapshot.child(value).child("imageName").getValue(String.class);
                dbdate = snapshot.child(value).child("imagedate").getValue(String.class);
                dbtime = snapshot.child(value).child("imagetime").getValue(String.class);
                dbphone = snapshot.child(value).child("imagephone").getValue(String.class);
                dburl = snapshot.child(value).child("imageURL").getValue(String.class);
                Name.setText(dbname);
                time.setText(dbtime);
                date.setText(dbdate);
                phone.setText(dbphone);
                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String TempImageName = Name.getText().toString().trim();
                        String TempImagetime = time.getText().toString().trim();
                        String TempImagephone = phone.getText().toString().trim();
                        String TempImagedate = date.getText().toString().trim();




                        Toast.makeText(getApplicationContext(),
                                " Updated Successfully ",
                                Toast.LENGTH_LONG).show();

                        uploadinfo imageUploadInfo = new uploadinfo(
                                TempImageName,TempImagetime,TempImagedate,TempImagephone,dburl);
                        databaseReference.child(String.valueOf(value)).setValue(imageUploadInfo);







                    }

                });



            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}

