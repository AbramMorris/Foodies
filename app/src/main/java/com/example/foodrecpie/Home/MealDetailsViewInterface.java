package com.example.foodrecpie.Home;

import com.example.foodrecpie.Model.MealDetailResponse;
import com.example.foodrecpie.Model.MealsPOJO;

public interface MealDetailsViewInterface {
        void showMealDetails(MealDetailResponse.MealsDTO meal);
        void showError(String message);
}
