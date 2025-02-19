package com.example.foodrecpie.ui.Search.ModelSearchResponse;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecpie.Network.Repo;
import com.example.foodrecpie.R;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.ui.Search.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SelectedCountryAdapter extends RecyclerView.Adapter<SelectedCountryAdapter.ViewHolder> {

    private List<AreaSearchModel.MealsDTO> meals;
    private Context context;
    private OnMealClickListener listener;
    private SearchPresenter presenter;

    public SelectedCountryAdapter(Context context, List<AreaSearchModel.MealsDTO> meals, OnMealClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_area_view, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AreaSearchModel.MealsDTO meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());


        // Load meal image using Glide
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);

        // Handle click event
//        holder.layout.setOnClickListener(v -> {
//            if (listener != null) {
//                listener.onMealClick(meal.);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updateData(List<AreaSearchModel.MealsDTO> newMeals) {
        meals.clear();
        meals.addAll(newMeals);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView mealName;
        ImageView mealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_Selected_area);
            mealName = itemView.findViewById(R.id.tv_calender_meal_name);
            mealImage = itemView.findViewById(R.id.img_calender_meal);
        }
    }

    public interface OnMealClickListener {
        void onMealClick(String meal);
    }
}
