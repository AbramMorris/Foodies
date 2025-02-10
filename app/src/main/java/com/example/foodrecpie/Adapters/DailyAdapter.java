package com.example.foodrecpie.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.R;

import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyViewHolder> {
    private Context context;
    private List<MealsPOJO> dailyMealList;
    private MealsPOJO DailyMeal;
    private ImageView DailyMealImageView;
    private TextView titleTextView;
    private TextView descriptionTextView;

    public DailyAdapter( ) {

    }

//    public DailyAdapter(Context context, List<MealsPOJO> dailyMealList, MealsPOJO dailyMeal, ImageView dailyMealImageView, TextView titleTextView, TextView descriptionTextView) {
//        this.context = context;
//        DailyMealList = dailyMealList;
//        DailyMeal = dailyMeal;
//        DailyMealImageView = dailyMealImageView;
//        this.titleTextView = titleTextView;
//        this.descriptionTextView = descriptionTextView;
//    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, int position) {
        MealsPOJO DailyMeal = dailyMealList.get(position);

        holder.DailyMealImageView.setImageResource(Integer.parseInt(DailyMeal.getStrMealThumb()));
        holder.titleTextView.setText(DailyMeal.getStrMeal());
        holder.descriptionTextView.setText(DailyMeal.getStrInstructions());
        dailyMealList= new ArrayList<>();
        holder.titleTextView.setText(DailyMeal.getStrMeal());
        holder.descriptionTextView.setText(DailyMeal.getStrInstructions());
        Glide.with(context)
                .load(DailyMeal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.DailyMealImageView);
    }

    @Override
    public int getItemCount() {
        return dailyMealList.size();
    }

    public void setDailyMealList(List<MealsPOJO> DailyMealList) {
        this.dailyMealList = DailyMealList;
        notifyDataSetChanged();
    }


    public static class DailyViewHolder extends RecyclerView.ViewHolder {
        private ImageView DailyMealImageView;
        private TextView titleTextView;
        private TextView descriptionTextView;

        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            DailyMealImageView = itemView.findViewById(R.id.imageView5);
            titleTextView = itemView.findViewById(R.id.item_name);
            descriptionTextView = itemView.findViewById(R.id.item_description);
        }
    }
}