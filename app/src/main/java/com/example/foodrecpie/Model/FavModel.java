package com.example.foodrecpie.Model;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "fav_meal_table", primaryKeys = {"userId","mealId"} )
public class FavModel implements Serializable {
    @NonNull
    private String userId;
    @NonNull
    private String mealId;
    private MealDetailResponse.MealsDTO meal;

    public FavModel() {
    }
    public FavModel(@NonNull String userId, MealDetailResponse.MealsDTO meal) {
        this.userId = userId;
        this.mealId = meal.getIdMeal();
        this.meal = meal;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public MealDetailResponse.MealsDTO getMeal() {
        return meal;
    }

    public void setMeal(MealDetailResponse.MealsDTO meal) {
        this.meal = meal;
    }
}
