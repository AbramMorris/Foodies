package com.example.foodrecpie.Model;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "plan_meal_table", primaryKeys = {"userId","mealId","date"} )
public class PlanModel implements Serializable {
    @NonNull
    private String userId;
    @NonNull
    private String mealId;
    @NonNull
    private String date;
    private MealDetailResponse.MealsDTO meal;

    public PlanModel() {
    }

    public PlanModel(@NonNull String userId, @NonNull String date, MealDetailResponse.MealsDTO meal) {
        this.userId = userId;
        this.mealId = meal.getIdMeal();
        this.date = date;
        this.meal = meal;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
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
