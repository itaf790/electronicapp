package com.app.electronicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    FirebaseUser currentUser;//used to store current user of account
    FirebaseAuth mAuth;//Used for firebase authentication
    Button insert, choose;
    EditText firstName,phone,adress,gender,LastName ;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    static String Storage_Path = "Uploads/";
    static String Database_Path = "phd_Database";
    int ImageUploadId =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        choose = (Button)findViewById(R.id.Choose);


        insert= (Button)findViewById(R.id.Insert);
        firstName = (EditText)findViewById(R.id.fname);
        LastName = (EditText)findViewById(R.id.lastname);
        phone = (EditText)findViewById(R.id.number);
        adress = (EditText)findViewById(R.id.adress);
        gender = (EditText)findViewById(R.id.gender);

        imgview = (ImageView)findViewById(R.id.imageView);
        progressDialog = new ProgressDialog(MainActivity.this);// context name as per your project name


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

                Intent intent = new Intent(MainActivity.this,Calinder.class);
                startActivity(intent);


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

                    // Hiding the progressDialog after done uploading.
                    progressDialog.dismiss();
                    // Showing toast message after done uploading.
                    Toast.makeText(getApplicationContext(),
                            "Your appointment has been booked successfully ",
                            Toast.LENGTH_LONG).show();
                    @SuppressWarnings("VisibleForTests")

                    uploadinfo imageUploadInfo = new uploadinfo(
                            TempImagefirstname,TempImagelastname,TempImagephone,TempImageadress, TempImagegender,uri.toString());
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

                    "Please Select Image or Add Image Name",
                    Toast.LENGTH_LONG).show();
        }
    }
}

