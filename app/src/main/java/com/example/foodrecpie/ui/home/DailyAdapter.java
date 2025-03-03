package com.example.foodrecpie.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecpie.Home.MealDetailsViewInterface;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.R;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyViewHolder> {
    private Context context;
    private List<MealsPOJO> dailyMealList;
    private AdapterHelper Listner;
    private MealsPOJO DailyMeal;
    private MealDetailsViewInterface view;




    public DailyAdapter(Context context, List<MealsPOJO> dailyMealList,AdapterHelper helper) {
        this.context = context;
        this.dailyMealList = dailyMealList;
        DailyMeal = new MealsPOJO();
        this.Listner = helper;
    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.random_meals, parent, false);
        return new DailyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, int position) {
        MealsPOJO DailyMeal = dailyMealList.get(position);
        holder.titleTextView.setText(DailyMeal.getStrMeal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Listner.showDetails(DailyMeal.getIdMeal());
                                               }
                                           });
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


        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            DailyMealImageView = itemView.findViewById(R.id.img_random_meal);
            titleTextView = itemView.findViewById(R.id.item_Random_name);
        }
    }
    interface AdapterHelper{
        void showDetails(String id);

    }
}
