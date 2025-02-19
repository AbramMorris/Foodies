package com.example.foodrecpie.Details;

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

import java.util.ArrayList;

public class MealDetailAdapter extends  RecyclerView.Adapter<MealDetailAdapter.MealViewHolder> {
    Context context;
    ArrayList<MealIngredients> ingredients;

    private String url_part1="https://www.themealdb.com/images/ingredients/";

    private  String url_part2=".png";



    public MealDetailAdapter(Context context) {
        this.context = context;
        ingredients = new ArrayList<>();

    }

    public  void  setList(ArrayList<MealIngredients> ingredients){
        this.ingredients=ingredients;
    }
    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_daetail_view, parent ,false);
        MealViewHolder vh = new MealViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
          MealIngredients ing  = ingredients.get(position);
          holder.ingredientName.setText(ing.getStrIngredient());
        Glide.with(context).load(url_part1+ing.getStrIngredient()+url_part2).into(holder.ingredientImage);
        holder.measure.setText(ing.getStrMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientName ;
        private ImageView ingredientImage;

        private  TextView measure;




        ConstraintLayout layout ;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_random_meal);
            ingredientImage=itemView.findViewById(R.id.img_random_meal);
            ingredientName=itemView.findViewById(R.id.tv_random_meals_name);
            measure=itemView.findViewById(R.id.tv_random_meals_measure);

        }
    }

}
