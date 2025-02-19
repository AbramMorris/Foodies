package com.example.foodrecpie.Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MealsTypeConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromMealsDTO(MealDetailResponse.MealsDTO mealsDTO) {
        return mealsDTO == null ? null : gson.toJson(mealsDTO);
    }

    @TypeConverter
    public static MealDetailResponse.MealsDTO toMealsDTO(String mealsDTOString) {
        return mealsDTOString == null ? null : gson.fromJson(mealsDTOString, new TypeToken<MealDetailResponse.MealsDTO>() {}.getType());
    }
}
