package com.app.electronicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.media.Image;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.widget.Toast;

public class viewactivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    ListView listView;

    ImageListAdapter adapter ;

    ProgressDialog progressDialog;

    ArrayList<uploadinfo> imagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewactivity);
        progressDialog = new ProgressDialog(viewactivity.this);
        progressDialog.setMessage("Loading appointments  ");
        progressDialog.show();
        databaseReference =
                FirebaseDatabase.getInstance().getReference(MainActivity.Database_Path);
        imagesList = new ArrayList<uploadinfo>();
// Adding Add Value Event Listener to databaseReference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    uploadinfo imageUploadInfo =
                            postSnapshot.getValue(uploadinfo.class);
                    imagesList.add(imageUploadInfo);
                }
                listView = findViewById(R.id.Listview);
                adapter =
                        new ImageListAdapter(getApplicationContext(),
                                R.layout.activity_image_list_adapter, imagesList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        uploadinfo current = (uploadinfo)parent.getItemAtPosition(position);
                        String name= current.getImageName();
                        String time= current.getImagetime();
                        String date= current.getImagedate();
                        String phone= current.getImagephone();
                        String  image =current.getImageURL();
                        Intent intent = new Intent(viewactivity.this , DetailsActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("time",time);
                        intent.putExtra("date",date);
                        intent.putExtra("phone",phone);
                        intent.putExtra("image",image);


                        startActivity(intent);
                    }
                });
                // Hiding the progress dialog.
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Hiding the progress dialog.
                progressDialog.dismiss();
            }
        });
    }

}



