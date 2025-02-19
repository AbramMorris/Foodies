package com.example.foodrecpie.Presenter;


import com.example.foodrecpie.CountryArea.Model.Meal;

public interface MealDetailPresenterInterface {
    public void addToFavorite(Meal meal);

    public void getSelectedMealDetails(String mealName);
}
