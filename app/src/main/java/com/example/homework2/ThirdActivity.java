package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private TextView textView_result;
    private static ArrayList<Beer> beerList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        beerList = new ArrayList<>();
        textView_result = findViewById(R.id.textView_ResultsFound);
        Intent intent = getIntent();
        textView_result.setText("We found " + intent.getStringExtra("numResults") + " results.");
        recyclerView = findViewById(R.id.recyclerView_beer);


        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("numResults")); i++){
            Beer beer = new Beer(intent.getStringExtra("name" +i), intent.getStringExtra("image_url" +i), intent.getStringExtra("description"+i),
                                 intent.getStringExtra("abv" +i), intent.getStringExtra("first_brewed" +i), intent.getStringExtra("food_pairing" +i), intent.getStringExtra("brewers_tips" +i));
            beerList.add(beer);
        }

        BeerAdapter adapter = new BeerAdapter(beerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
