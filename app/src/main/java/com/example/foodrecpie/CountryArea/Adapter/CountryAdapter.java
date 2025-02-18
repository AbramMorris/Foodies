package com.example.foodrecpie.CountryArea.Adapter;

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
import com.example.foodrecpie.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter <CountryAdapter.MyViewHolder>{

    Context context;
    List<Meal> country;

    AreaOnClickListner listner;

    public CountryAdapter(Context context, List<Meal> country ,AreaOnClickListner listner) {
        this.context = context;
        this.country = country;
        this.listner=listner;
    }

    public void setAreas(List<Meal> country){
        this.country=country;
        Log.d("Abram", "setAreas: "+country.size());
    }


    @NonNull
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.area_view , parent , false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.MyViewHolder holder, int position) {
        Meal meal = country.get(position);

        holder.nationality.setText(meal.getStrArea());
//        holder.thumbnails.setImageResource(meal.);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClick(meal.getStrArea());
            }
        });

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
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView nationality ;
        ImageView thumbnails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay);
            nationality = itemView.findViewById(R.id.text_country);
            thumbnails = itemView.findViewById(R.id.imageView);

        }


    }

}
