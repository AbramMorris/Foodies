package com.example.foodrecpie.ui.Search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecpie.CountryArea.AreaOnClickListner;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.R;
import com.example.foodrecpie.ui.Search.Data.AreaResponse;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>  {


    List<Meal> country;
    Context context;
    AreaOnClickListner listner;
    List<Meal> myMeals;

    public SearchAdapter(List<Meal> country, Context context, AreaOnClickListner listner) {
        this.context = context;
        this.country = country;
        this.listner = listner;

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

            holder.nationality.setText(meal.getStrArea());
//        holder.thumbnails.setImageResource(meal.);

            int imageResId = context.getResources().getIdentifier(meal.getStrArea().toLowerCase(), "drawable", context.getPackageName());
            if (imageResId != 0) {
                holder.thumbnails.setImageResource(imageResId);
            } else {
                holder.thumbnails.setImageResource(R.drawable.ic_launcher_background);
                Log.e("ImageError", "Image not found for: ");

        }
    }

    @Override
    public int getItemCount() {
        return country.size();
    }
    public void updateData(List<Meal> newCountry) {
        country.clear();
        country.addAll(newCountry);
        notifyDataSetChanged();
    }
    public void setList(List<Meal> updateMales )
    {this.myMeals = updateMales;}

//    public void filter(String query) {
//        country.clear();
//        for (Object item : fullList) {
//            if (item.toString().toLowerCase().contains(query.toLowerCase())) {
//                country.add(country);
//            }
//        }
//        notifyDataSetChanged();
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView nationality ;
        ImageView thumbnails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay);
            nationality = itemView.findViewById(R.id.text_country);
            thumbnails = itemView.findViewById(R.id.imageView);

        }
    }
}

