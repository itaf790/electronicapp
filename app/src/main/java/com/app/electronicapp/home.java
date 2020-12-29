package com.app.electronicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {
    private Button signInButton, signInAdminBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        signInButton = (Button) findViewById(R.id.main_sign_in_btn);
        signInAdminBtn =(Button) findViewById(R.id.main_admin_sign_in_btn);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signInAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }
/// about
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.about:
                startActivity (new Intent (getApplicationContext (),
                        about.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }}
