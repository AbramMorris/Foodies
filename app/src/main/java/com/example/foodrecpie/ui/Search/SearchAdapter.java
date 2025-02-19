package com.example.foodrecpie.ui.Search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecpie.CountryArea.AreaOnClickListner;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.R;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.SelectedCountryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Meal> country;
    private final Context context;
    private final SelectedCountryAdapter.OnMealClickListener listener;

    public SearchAdapter(List<Meal> country, Context context, SelectedCountryAdapter.OnMealClickListener listener) {
        this.context = context;
        this.country = country ;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.area_view, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = country.get(position);
        Log.d("Abram", "onBindViewHolder: "+meal.getStrArea());

        if (meal != null && meal.getStrArea() != null) {
            holder.nationality.setText(meal.getStrArea());

            // Load image dynamically
            int imageResId = context.getResources().getIdentifier(
                    meal.getStrArea().toLowerCase(), "drawable", context.getPackageName());

            if (imageResId != 0) {
                holder.thumbnails.setImageResource(imageResId);
            } else {
                holder.thumbnails.setImageResource(R.drawable.ic_launcher_background);
                Log.e("ImageError", "Image not found for: " + meal.getStrArea());
            }

            // Handle click event
            holder.layout.setOnClickListener(v -> {
                listener.onMealClick(meal.getStrArea());
                Log.d("Meal Clicked", "Clicked on: " + meal.getStrArea());
            });
        } else {
            Log.e("MealError", "Meal or StrArea is null at position: " + position);
            holder.nationality.setText("Unknown");
            holder.thumbnails.setImageResource(R.drawable.ic_launcher_background);
        }
    }


    @Override
    public int getItemCount() {
        return country.size();
    }

    public void updateData(List<Meal> newCountry) {
        if (newCountry != null) {
            country.clear();
            country.addAll(newCountry);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView nationality;
        ImageView thumbnails;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay);
            nationality = itemView.findViewById(R.id.text_country);
            thumbnails = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.area_view_Card);
        }
    }
}
