package com.example.foodrecpie.ui.Search.Data;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("meals")
    private List<MealsDTO> meals;

    public List<MealsDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<MealsDTO> meals) {
        this.meals = meals;
    }

    public static class MealsDTO {
        @SerializedName("strCategory")
        private String strCategory;

        public String getStrCategory() {
            return strCategory;
        }
        public void setStrCategory(String strCategory) {
            this.strCategory = strCategory;
        }
    }
}
