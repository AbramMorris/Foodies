package com.example.foodrecpie.ui.Search.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {
    private List<Ingredient> meals;

    public List<Ingredient> getMeals() { return meals; }
    public static class Ingredient {
        @SerializedName("strIngredient")
        private String ingredientName;

        public String getIngredientName() {
            return ingredientName;
        }
//        private String strIngredient;
//        public String getStrIngredient() { return strIngredient; }
    }
}
