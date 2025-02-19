package com.example.foodrecpie.ui.Search.ModelSearchResponse;




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
import com.example.foodrecpie.R;
import com.example.foodrecpie.CountryArea.Model.Meal;

import java.util.ArrayList;
import java.util.List;

public class CategoryMealsAdapter extends RecyclerView.Adapter<CategoryMealsAdapter.ViewHolder> {

    private List<CategoryMealResponse.MealsDTO> meals;
    private OnCatClickListener listener;

    public CategoryMealsAdapter(List<CategoryMealResponse.MealsDTO> meals) {
        this.meals = meals != null ? meals : new ArrayList<>();
    }

    public void setListener(OnCatClickListener listener) {
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
        CategoryMealResponse.MealsDTO meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());

        // Load meal image using Glide
        Glide.with(holder.itemView.getContext()).load(meal.getStrMealThumb()).into(holder.mealImage);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCatClick(meal.getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updateData(List<CategoryMealResponse.MealsDTO> newMeals) {
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

    public interface OnCatClickListener {
        void onCatClick(String mealId);
    }
}
