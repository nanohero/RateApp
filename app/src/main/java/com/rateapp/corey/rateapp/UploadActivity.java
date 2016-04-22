package com.rateapp.corey.rateapp;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        //get id's
        TextView serView=(TextView) findViewById(R.id.activty_upload_et_service);
        Button nxtBtn=(Button) findViewById(R.id.activity_upload_btn_images);
    }
    public void openMap(View v) {
        //opne map activity
        startActivity(new Intent(UploadActivity.this, MapActivity.class));
    }
    public void openImages(View v) {
        //acess images
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }
    public void openVideos(View v) {
        //acess videos
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }
}
