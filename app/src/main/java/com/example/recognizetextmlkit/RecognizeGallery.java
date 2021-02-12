package com.example.recognizetextmlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class RecognizeGallery extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize_gallery);
        Uri myUri = Uri.parse(getIntent().getStringExtra("imageUri"));
        imageView = findViewById(R.id.ivphoto);
        imageView.setImageURI(myUri);

    }
}