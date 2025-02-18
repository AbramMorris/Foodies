package com.example.foodrecpie.ui.Search;


import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.ui.Search.Data.AreaResponse;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;

import org.checkerframework.checker.units.qual.Area;

import java.util.List;

    public interface SearchViewInterface {
        void showCategories(List<CategoryResponse.CategoryDTO> categories);
        void showAreas(List<Meal> areas);
        void showIngredients(List<IngredientResponse.Ingredient> ingredients);
        void showError(String message);

    }
