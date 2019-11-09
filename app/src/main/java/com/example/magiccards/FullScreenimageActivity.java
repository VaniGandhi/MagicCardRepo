package com.example.magiccards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenimageActivity extends AppCompatActivity {
    ImageView fullscreenimage;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screenimage);
        fullscreenimage=findViewById(R.id.fullscreenimage);
        intent=getIntent();
        Bundle ex = getIntent().getExtras();
        Bitmap bmp2 = ex.getParcelable("image");

        fullscreenimage.setImageBitmap(bmp2);
    }
}
