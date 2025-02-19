package com.example.foodrecpie.Network;

import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;

import java.util.List;

public interface NetworkCallCategories {
    void onSuccess(List<CategoryResponse.MealsDTO> categories);
    void onFailure(String message);

    void onSuccessIngredients(List<IngredientResponse.MealsDTO> ingredients);

}
