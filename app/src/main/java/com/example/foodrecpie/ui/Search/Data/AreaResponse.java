package com.example.foodrecpie.ui.Search.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaResponse {
    private List<MealsDTO> meals;

    public List<MealsDTO> getMeals() { return meals; }

    public static class MealsDTO {
        @SerializedName("strArea")
        private String areaName;

        public String getAreaName() {
            return areaName;
        }
//        private String strArea;
//        public String getStrArea() { return strArea; }
    }

}
