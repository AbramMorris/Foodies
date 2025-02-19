package com.example.foodrecpie.ui.Search;


import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientMealResponse;

import java.util.List;

    public interface SearchViewInterface {
        void showCategories(List<CategoryResponse.MealsDTO> categories);
        void showAreas(List<Meal> areas);
        void showMeals(List<AreaSearchModel.MealsDTO> meals);
        void showIngredients(List<IngredientResponse.MealsDTO> ingredients);
        void showError(String message);
        void ShowCategoryMeals(List<CategoryMealResponse.MealsDTO> meals);
        void showIngMeals(List<IngredientMealResponse.MealsDTO>meals);

    }
