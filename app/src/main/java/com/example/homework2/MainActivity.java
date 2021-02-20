package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_getStarted);
        imageView = findViewById(R.id.imageView_FindYourBrew);

        Picasso.get().load("file:///android_asset/national-beer-day-ipa.jpg").into(imageView);
        imageView.setScaleX((float)1.50);
        imageView.setScaleY((float)1.50);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        button.setOnClickListener(v -> startActivity(intent));
    }

}