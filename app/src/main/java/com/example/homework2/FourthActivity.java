package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class FourthActivity extends AppCompatActivity {

    private TextView textView_anun;
    private ImageView imageView_nkar;
    private TextView textView_abv;
    private TextView textView_firstBrewed;
    private TextView description;
    private TextView foodPairings;
    private TextView brewersTips;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        textView_anun = findViewById(R.id.textView_anun);
        imageView_nkar = findViewById(R.id.imageView_nkar);
        textView_abv = findViewById(R.id.textView5_abv);
        textView_firstBrewed = findViewById(R.id.textView_dateFirstBrewed);
        description = findViewById(R.id.textView_beerInfo);
        foodPairings = findViewById(R.id.textView_foodPairings);
        brewersTips = findViewById(R.id.textView_brewersTips);

        Intent intent = getIntent();

        textView_anun.setText(intent.getStringExtra("name"));
        Picasso.get().load(intent.getStringExtra("image_url")).into(imageView_nkar);
        textView_abv.setText("abv: " + intent.getStringExtra("abv"));
        textView_firstBrewed.setText("First brewed: " + intent.getStringExtra("first_brewed"));
        description.setText(intent.getStringExtra("description"));
        foodPairings.setText(intent.getStringExtra("food_pairing"));
        brewersTips.setText(intent.getStringExtra("brewers_tips"));

    }
}
