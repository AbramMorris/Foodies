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
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;
import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private List<IngredientResponse.MealsDTO> ingredients;
    private Context context;
    private String url_part1 = "https://www.themealdb.com/images/ingredients/";
    private String url_part2 = ".png";
    private IngredientMealsAdapter.OnIngredientClickListener listener;

    public IngredientAdapter(Context context, List<IngredientResponse.MealsDTO> ingredients, IngredientMealsAdapter.OnIngredientClickListener listener) {
        this.context = context;
        this.ingredients = ingredients != null ? ingredients : new ArrayList<>();
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
        IngredientResponse.MealsDTO ingredient = ingredients.get(position);
        holder.ingredientName.setText(ingredient.getStrIngredient());
        holder.layout.setOnClickListener(v -> {
                listener.onIngredientMealClick(ingredient.getStrIngredient());
        });
        Glide.with(context)
                .load(url_part1 + ingredient.getStrIngredient() + url_part2)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void updateData(List<IngredientResponse.MealsDTO> newIngredients) {
        ingredients.clear();
        if (newIngredients != null)
        {ingredients.addAll(newIngredients);
        notifyDataSetChanged();} else {
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView ingredientName;
        ImageView ingredientImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay);
            ingredientName = itemView.findViewById(R.id.text_country);
            ingredientImage = itemView.findViewById(R.id.imageView);
        }
    }
}
