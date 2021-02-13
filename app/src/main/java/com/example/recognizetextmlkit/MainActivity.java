package com.example.recognizetextmlkit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_GALLERY = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doProcessCamera(View view) {
        dispatchTakePictureIntent("camera");
    }

    public void doProcessGallery(View view) {
        dispatchTakePictureIntent("gallery");
    }

    private void dispatchTakePictureIntent(String mode) {
        if (mode.equals("gallery")) {
            Intent intent = new Intent(Intent.ACTION_PICK).setType("image/*");
            startActivityForResult(intent, REQUEST_GALLERY);
        } else if (mode.equals("camera")) {
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] { Manifest.permission.CAMERA }, 1);
            } else {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            imageUri = data.getData();
            startActivity(new Intent(this, RecognizeGallery.class).putExtra("imageUri", imageUri.toString()));
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            startActivity(new Intent(this, RecognizeGallery.class).putExtra("imageBitmap", imageBitmap));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 1) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            if (requestCode == 1) {
                Toast.makeText(MainActivity.this, "Debe aceptar los permisos para la cámara", Toast.LENGTH_LONG).show();
            }
        }
        return;
    }
}