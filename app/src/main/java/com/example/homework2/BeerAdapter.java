package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beers;
    private List<Beer> selectedBeers;

    public BeerAdapter(List<Beer> beers){

        this.beers = beers;
        this.selectedBeers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View beerView = inflater.inflate(R.layout.item_beer, parent, false);

        ViewHolder viewHolder = new ViewHolder(beerView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Beer beer = beers.get(position);

        holder.textView_name.setText(beer.getName());
        holder.textView_description.setText(beer.getDescription());
        Picasso.get().load(beer.getImage()).into(holder.imageView_image);
        //Picasso.get().load("file:///android_asset/star.png").into(holder.imageView_favorite);

        if(selectedBeers.contains(beer)){
            Picasso.get().load("file:///android_asset/star (1).png").into(holder.imageView_favorite);
        }
        else {
            Picasso.get().load("file:///android_asset/star.png").into(holder.imageView_favorite);
        }


    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView_name;
        TextView textView_description;
        ImageView imageView_image;
        ImageView imageView_favorite;

        public ViewHolder(View itemView){
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_beerName);
            textView_description = itemView.findViewById(R.id.textView_beerDescription);
            imageView_image = itemView.findViewById(R.id.imageView_beer);
            imageView_favorite = itemView.findViewById(R.id.imageView_favorite);
            imageView_image.setOnClickListener(v -> launchNextActivity(v));
            imageView_favorite.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            int selectedBeer = getAdapterPosition();
            Beer selectedB = beers.get(selectedBeer);
                if(selectedBeers.contains(selectedB)){
                    selectedBeers.remove(selectedB);
                }
                else {
                    selectedBeers.add(selectedB);
                }
            notifyDataSetChanged();
        }

        public void launchNextActivity(View view){
            Intent intent = new Intent(view.getContext(), FourthActivity.class);
            int i = getAdapterPosition();
            Beer B = beers.get(i);
                intent.putExtra("name", B.getName());
                intent.putExtra("image_url", B.getImage());
                intent.putExtra("description", B.getDescription());
                intent.putExtra("abv", B.getAbv());
                intent.putExtra("first_brewed", B.getFirst_brewed());
                intent.putExtra("food_pairing", B.getFood_pairings());
                intent.putExtra("brewers_tips", B.getBrewers_tips());
            // just startActivity(intent)?
            view.getContext().startActivity(intent);
        }
    }
}
