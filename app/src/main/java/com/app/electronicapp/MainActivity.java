package com.app.electronicapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnDatePicker, btnTimePicker;
    EditText date, time, duration;
    private int mYear, mMonth, mDay, mHour, mMinute;
    FirebaseUser currentUser;//used to store current user of account
    FirebaseAuth mAuth;//Used for firebase authentication
    Button insert, choose;
    EditText firstName, phone, adress, gender, LastName;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog;
    static String Storage_Path = "Uploads/";
    static String Database_Path = "phd_Database";
    int ImageUploadId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        choose = (Button) findViewById(R.id.Choose);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        date = (EditText) findViewById(R.id.in_date);
        time = (EditText) findViewById(R.id.in_time);
        duration = (EditText) findViewById(R.id.DURATION);


        insert = (Button) findViewById(R.id.Insert);
        firstName = (EditText) findViewById(R.id.fname);
        LastName = (EditText) findViewById(R.id.lastname);
        phone = (EditText) findViewById(R.id.number);
        adress = (EditText) findViewById(R.id.adress);
        gender = (EditText) findViewById(R.id.gender);


        imgview = (ImageView) findViewById(R.id.imageView);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {

                                                 if (view == btnDatePicker) {

                                                     // Get Current Date
                                                     final Calendar c = Calendar.getInstance();
                                                     mYear = c.get(Calendar.YEAR);
                                                     mMonth = c.get(Calendar.MONTH);
                                                     mDay = c.get(Calendar.DAY_OF_MONTH);

                                                     DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                                                             new DatePickerDialog.OnDateSetListener() {

                                                                 @Override
                                                                 public void onDateSet(DatePicker view, int year,
                                                                                       int monthOfYear, int dayOfMonth) {

                                                                     date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                                                 }
                                                             }, mYear, mMonth, mDay);
                                                     datePickerDialog.show();
                                                 }

                                             }
                                         });

                      btnTimePicker.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View view) {

                                                 if (view == btnTimePicker) {

                                                     // Get Current Time
                                                     final Calendar c = Calendar.getInstance();
                                                     mHour = c.get(Calendar.HOUR_OF_DAY);
                                                     mMinute = c.get(Calendar.MINUTE);

                                                     // Launch Time Picker Dialog
                                                     TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                                                             new TimePickerDialog.OnTimeSetListener() {

                                                                 @Override
                                                                 public void onTimeSet(TimePicker view, int hourOfDay,
                                                                                       int minute) {

                                                                     time.setText(hourOfDay + ":" + minute);
                                                                 }
                                                             }, mHour, mMinute, false);
                                                     timePickerDialog.show();
                                                 }
                                             }
                                                       });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImageFileToFirebaseStorage();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImageFileToFirebaseStorage() {
        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {
            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");
            // Showing progressDialog.
            progressDialog.show();
            // Creating second StorageReference.
            final StorageReference storageReference2 =
                    storageReference.child(Storage_Path +
                            System.currentTimeMillis() + "." +
                            GetFileExtension(FilePathUri));
            // Adding CompleteListener to second StorageReference.
            storageReference2.putFile(FilePathUri).continueWithTask(
                    new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task)
                                throws Exception {
                            if (!task.isSuccessful()){
                                throw task.getException();
                            }
                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");
                            return storageReference2.getDownloadUrl();

                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        // Getting image download Url
                        Uri downUri = task.getResult();
                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String TempImagefirstname = firstName.getText().toString().trim();
                    String TempImagelastname = LastName.getText().toString().trim();
                    String TempImageadress = adress.getText().toString().trim();
                    String TempImagephone = phone.getText().toString().trim();
                    String TempImagegender = gender.getText().toString().trim();
                    String TempImagedate = date.getText().toString().trim();
                    String TempImagetime = time.getText().toString().trim();
                    String TempImageduration = duration.getText().toString().trim();
                    // Hiding the progressDialog after done uploading.
                    progressDialog.dismiss();
                    // Showing toast message after done uploading.
                    Toast.makeText(getApplicationContext(),
                            "Image Uploaded Successfully ",
                            Toast.LENGTH_LONG).show();
                    @SuppressWarnings("VisibleForTests")

                    uploadinfo imageUploadInfo = new uploadinfo(
                            TempImagefirstname,TempImagelastname,TempImageadress,TempImagephone,TempImagegender,TempImagedate,TempImagetime,TempImageduration,uri.toString());
                    // Getting image upload ID.

                    // Adding image upload id s child element into databaseReference.
                    databaseReference.child(String.valueOf(ImageUploadId+1)).setValue(imageUploadInfo);
                    ImageUploadId =ImageUploadId+1;

                }
            })
                    // If something goes wrong
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Hiding the progressDialog.
                            progressDialog.dismiss();
                            // Showing exception error message.
                            Toast.makeText(MainActivity.this,
                                    exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else {
            Toast.makeText(MainActivity.this,

                    "Please Select Image ",
                    Toast.LENGTH_LONG).show();
        }
    }
}
