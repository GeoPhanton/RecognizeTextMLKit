package com.example.recognizetextmlkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;

import java.io.IOException;

public class RecognizeGallery extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize_gallery);

        imageView = findViewById(R.id.ivphoto);
        textView = findViewById(R.id.txtdisplay);
        textView.setMovementMethod(new ScrollingMovementMethod());
        bundle = getIntent().getExtras();
        captureImage();
    }

    private void captureImage() {
        if (bundle.getString("imageUri") != null) {
            Uri myUri = Uri.parse(bundle.getString("imageUri"));
            imageView.setImageURI(myUri);

            recognizeText(convertUri(myUri));
        } else {
            Bitmap myBitmap = (Bitmap) bundle.get("imageBitmap");
            imageView.setImageBitmap(myBitmap);

            recognizeText(convertBitmap(myBitmap));
        }
    }

    private InputImage convertUri(Uri uri) {
        InputImage image = null;
        try {
            image = InputImage.fromFilePath(this, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private InputImage convertBitmap(Bitmap bitmap) {
        int rotationDegree = 0;
        InputImage image = InputImage.fromBitmap(bitmap, rotationDegree);
        return image;
    }

    private void recognizeText(InputImage image) {
        if (image == null) {
            return;
        }
        TextRecognizer recognizer = TextRecognition.getClient();

        Task<Text> result =
                recognizer.process(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text visionText) {
                                processTextBlock(visionText);
                                Toast.makeText(getApplicationContext(), "Texto detectado", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        textView.setText("No hay texto");
                                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                    }
                                });
    }

    private void processTextBlock(Text result) {
        String resultText = result.getText();
        for (Text.TextBlock block : result.getTextBlocks()) {
            String blockText = block.getText();
            Point[] blockCornerPoints = block.getCornerPoints();
            Rect blockFrame = block.getBoundingBox();
            for (Text.Line line : block.getLines()) {
                String lineText = line.getText();
                Point[] lineCornerPoints = line.getCornerPoints();
                Rect lineFrame = line.getBoundingBox();
                for (Text.Element element : line.getElements()) {
                    String elementText = element.getText();
                    Point[] elementCornerPoints = element.getCornerPoints();
                    Rect elementFrame = element.getBoundingBox();
                }
            }
        }

        if (resultText.equals("")) {
            textView.setText("No hay texto");
        } else {
            textView.setText(resultText);
        }
    }

    public void pressback(View view) {
        finish();
    }

    public void refreshpress(View view) {
        captureImage();
    }
}