package com.example.foodrecpie.ui.Search.Data;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("meals")
    private List<CategoryDTO> meals;

    public List<CategoryDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<CategoryDTO> meals) {
        this.meals = meals;
    }

    public static class CategoryDTO {
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
