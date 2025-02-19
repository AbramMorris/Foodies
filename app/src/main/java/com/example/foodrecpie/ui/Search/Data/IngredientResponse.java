package com.example.foodrecpie.ui.Search.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {
    @SerializedName("meals")
    private List<MealsDTO> meals;

    public List<MealsDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<MealsDTO> meals) {
        this.meals = meals;
    }

    public static class MealsDTO {
        private String idIngredient;
        @SerializedName("strIngredient")
        private String strIngredient;
        private String strDescription;
        private Object strType;

        public String getIdIngredient() {
            return idIngredient;
        }

        public void setIdIngredient(String idIngredient) {
            this.idIngredient = idIngredient;
        }

        public String getStrIngredient() {
            return strIngredient;
        }

        public void setStrIngredient(String strIngredient) {
            this.strIngredient = strIngredient;
        }

        public String getStrDescription() {
            return strDescription;
        }

        public void setStrDescription(String strDescription) {
            this.strDescription = strDescription;
        }

        public Object getStrType() {
            return strType;
        }

        public void setStrType(Object strType) {
            this.strType = strType;
        }
    }
//    private List<Ingredient> meals;
//
//    public List<Ingredient> getMeals() { return meals; }
//    public static class Ingredient {
//        @SerializedName("strIngredient")
//        private String ingredientName;
//
//        public String getIngredientName() {
//            return ingredientName;
//        }
//    }

//    @SerializedName("meals")
//    private List<Ingredient> meals;
    
}
