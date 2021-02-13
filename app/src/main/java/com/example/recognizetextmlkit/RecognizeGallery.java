package com.example.recognizetextmlkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.ml.vision.FirebaseVision;
//import com.google.firebase.ml.vision.common.FirebaseVisionImage;
//import com.google.firebase.ml.vision.text.FirebaseVisionText;
//import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
//import com.google.mlkit.vision.common.InputImage;

public class RecognizeGallery extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize_gallery);

        imageView = findViewById(R.id.ivphoto);
        textView = findViewById(R.id.txtdisplay);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("imageUri") != null){
            Uri myUri = Uri.parse(bundle.getString("imageUri"));
            imageView.setImageURI(myUri);
        } else {
            imageView.setImageBitmap((Bitmap) bundle.get("imageBitmap"));
        }
        //imageView.buildDrawingCache();
        //Bitmap bitmap = imageView.getDrawingCache();
        //InputImage image = InputImage.fromBitmap(bitmap,0);
        //FirebaseVision firebaseVision = FirebaseVision.getInstance();
        //FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = firebaseVision.getOnDeviceTextRecognizer();
        //Task<FirebaseVisionText> task = firebaseVisionTextRecognizer.processImage(firebaseVisionImage);
        //task.addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
        //    @Override
        //    public void onSuccess(FirebaseVisionText firebaseVisionText) {
        //        String s = firebaseVisionText.getText();
        //        textView.setText(s);
        //    }
        // });
        //task.addOnFailureListener(new OnFailureListener() {
        //    @Override
        //   public void onFailure(@NonNull Exception e) {
        //       Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        //   }
        //});
    }
}