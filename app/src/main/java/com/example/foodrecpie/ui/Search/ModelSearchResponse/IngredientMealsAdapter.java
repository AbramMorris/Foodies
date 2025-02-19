package com.example.foodrecpie.ui.Search.ModelSearchResponse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecpie.R;
import com.example.foodrecpie.ui.Search.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class IngredientMealsAdapter extends RecyclerView.Adapter<IngredientMealsAdapter.ViewHolder> {

    private List<IngredientMealResponse.MealsDTO> meals;
    private Context context;
    private OnIngredientClickListener listener;
    private SearchPresenter presenter;

    public IngredientMealsAdapter(Context context, List<IngredientMealResponse.MealsDTO> meals, OnIngredientClickListener listener) {
        this.context = context;
        this.meals = meals != null ? meals : new ArrayList<>();
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
        IngredientMealResponse.MealsDTO meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());
//        holder.addToFav.setChecked(false);
//        holder.addToFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                presenter.addToFavorite();
//            }
//        });

        // Load meal image using Glide
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);

        // Handle click event
        holder.layout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onIngredientMealClick(meal.getIdMeal());  // Pass meal ID for further details
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updateData(List<IngredientMealResponse.MealsDTO> newMeals) {
        meals.clear();
        meals.addAll(newMeals);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView mealName;
        ImageView mealImage;
        ToggleButton addToFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_Selected_area);
            mealName = itemView.findViewById(R.id.tv_calender_meal_name);
            mealImage = itemView.findViewById(R.id.img_calender_meal);
            addToFav = itemView.findViewById(R.id.btn_addToFav);
        }
    }

    public interface OnIngredientClickListener {
        void onIngredientMealClick(String mealId);
//        void onAddToFavorite(IngredientMealResponse.MealsDTO meal);
    }
}

