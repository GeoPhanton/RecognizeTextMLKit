package com.example.recognizetextmlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class RecognizeGallery extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize_gallery);

        imageView = findViewById(R.id.ivphoto);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("imageUri") != null){
            Uri myUri = Uri.parse(bundle.getString("imageUri"));
            imageView.setImageURI(myUri);
        } else {
            imageView.setImageBitmap((Bitmap) bundle.get("imageBitmap"));
        }
    }
}