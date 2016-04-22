package com.rateapp.corey.rateapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //get id
        Button nxtBtn=(Button) findViewById(R.id.nxtBtn);
        nxtBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, UploadActivity.class)); //open upload activity
            }
            });
    }
}
