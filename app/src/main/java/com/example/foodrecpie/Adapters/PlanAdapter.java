package com.example.foodrecpie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.PlanModel;
import com.example.foodrecpie.R;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.AreaHolder>{

    ArrayList<PlanModel> myMeals;
    private onItemClickListener listener;


    public PlanAdapter(ArrayList<PlanModel> myMeals) {
        this.myMeals = myMeals;
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public void setMyMeals(ArrayList<PlanModel> myMeals) {
        this.myMeals = myMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AreaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selected_area_view , parent , false);
        AreaHolder vh = new AreaHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaHolder holder, int position) {
        PlanModel meal = myMeals.get(position);
        holder.title.setText(meal.getMeal().getStrMeal());
        Glide.with(holder.itemView.getContext()).load(meal.getMeal().getStrMealThumb()).into(holder.thumbnails);
        holder.addToFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveClick(meal);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(meal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myMeals.size();
    }

    public class AreaHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView title ;

        ImageView thumbnails;

        ToggleButton addToFavourite ;

        AutoCompleteTextView autoCompleteTextView;


        public AreaHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_Selected_area);
            title = itemView.findViewById(R.id.tv_calender_meal_name);
            thumbnails = itemView.findViewById(R.id.img_calender_meal);
            addToFavourite= itemView.findViewById(R.id.btn_addToFav);
            autoCompleteTextView=itemView.findViewById(R.id.days_drop_dawn);


        }


    }
    public interface onItemClickListener {
        void onItemClick(PlanModel meal);
        void onRemoveClick(PlanModel meal);
    }
}

