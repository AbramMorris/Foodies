package com.example.foodrecpie.ui.home;

import com.example.foodrecpie.Model.MealsPOJO;

public interface HomeOnClickListner {
    void onAddToFavorite (MealsPOJO meal);

    void showMealDetails(MealsPOJO meal);
}
