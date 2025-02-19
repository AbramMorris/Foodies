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
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryResponse.MealsDTO> categories;
    private Context context;
    private String url_part1="https://www.themealdb.com/images/category/";

    private  String url_part2=".png";
    private CategoryMealsAdapter.OnCatClickListener listener;

    public CategoryAdapter(Context context, List<CategoryResponse.MealsDTO> categories, CategoryMealsAdapter.OnCatClickListener listener) {
        this.context = context;
        this.categories = categories != null ? categories : new ArrayList<>();
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
        CategoryResponse.MealsDTO category = categories.get(position);
        holder.categoryName.setText(category.getStrCategory());
        holder.layout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCatClick(category.getStrCategory());
            }
        });


        // Set image if available, or use default
        Glide.with(context).load(url_part1+category.getStrCategory()+url_part2).into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void updateData(List<CategoryResponse.MealsDTO> newCategories) {
        categories.clear();
        categories.addAll(newCategories);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView categoryName;
        ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay);
            categoryName = itemView.findViewById(R.id.text_country);
            categoryImage = itemView.findViewById(R.id.imageView);
        }
    }
}

