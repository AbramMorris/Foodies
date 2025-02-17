package com.example.foodrecpie.CountryArea.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectedResponse {
    @SerializedName("meals")
    private ArrayList<Meal> meals ;


    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }


    public ArrayList<Meal> getAreaMeals() {
        return meals;
    }

    public void setAreaMeals(ArrayList<Meal> areaMeals) {
        this.meals = areaMeals;
    }

    public static ArrayList<HashMap<String , String>> convertListToHashMap (ArrayList<Meal> convertedList){
        ArrayList<HashMap<String , String>> result = new ArrayList<>();
        for(Meal meal :convertedList){
            HashMap<String , String>  resultMap = new HashMap<>();
               resultMap.put("strMeal",meal.getStrMeal());
               resultMap.put("strMealThumb",meal.getStrMealThumb());
               resultMap.put("strArea",meal.getStrArea());
               resultMap.put("day",meal.getDay());
           /*    resultMap.put("strIngredient",meal.getStrIngredient());
               resultMap.put("strCategory",meal.getStrCategory());
               resultMap.put("idMeal",meal.getIdMeal());
               resultMap.put("strInstructions",meal.getStrInstructions());
               resultMap.put("strYoutube",meal.getStrYoutube());
               resultMap.put("strIngredient1", meal.getStrIngredient1());
            resultMap.put("strIngredient2", meal.getStrIngredient2());
            resultMap.put("strIngredient3", meal.getStrIngredient3());
            resultMap.put("strIngredient4", meal.getStrIngredient4());
            resultMap.put("strIngredient5", meal.getStrIngredient5());
            resultMap.put("strIngredient6", meal.getStrIngredient6());
            resultMap.put("strIngredient7", meal.getStrIngredient7());
            resultMap.put("strIngredient8", meal.getStrIngredient8());
            resultMap.put("strIngredient9", meal.getStrIngredient9());
            resultMap.put("strIngredient10", meal.getStrIngredient10());
            resultMap.put("strIngredient11", meal.getStrIngredient11());
            resultMap.put("strIngredient12", meal.getStrIngredient12());
            resultMap.put("strIngredient13", meal.getStrIngredient13());
            resultMap.put("strIngredient14", meal.getStrIngredient14());
            resultMap.put("strIngredient15", meal.getStrIngredient15());
            resultMap.put("strIngredient16", meal.getStrIngredient16());
            resultMap.put("strIngredient17", meal.getStrIngredient17());
            resultMap.put("strIngredient18", meal.getStrIngredient18());
            resultMap.put("strIngredient19", meal.getStrIngredient19());
            resultMap.put("strIngredient20", meal.getStrIngredient20());
            resultMap.put("strMeasure1", meal.getStrMeasure1());
            resultMap.put("strMeasure2", meal.getStrMeasure2());
            resultMap.put("strMeasure3", meal.getStrMeasure3());
            resultMap.put("strMeasure4", meal.getStrMeasure4());
            resultMap.put("strMeasure5", meal.getStrMeasure5());
            resultMap.put("strMeasure6", meal.getStrMeasure6());
            resultMap.put("strMeasure7", meal.getStrMeasure7());
            resultMap.put("strMeasure8", meal.getStrMeasure8());
            resultMap.put("strMeasure9", meal.getStrMeasure9());
            resultMap.put("strMeasure10", meal.getStrMeasure10());
            resultMap.put("strMeasure11", meal.getStrMeasure11());
            resultMap.put("strMeasure12", meal.getStrMeasure12());
            resultMap.put("strMeasure13", meal.getStrMeasure13());
            resultMap.put("strMeasure14", meal.getStrMeasure14());
            resultMap.put("strMeasure15", meal.getStrMeasure15());
            resultMap.put("strMeasure16", meal.getStrMeasure16());
            resultMap.put("strMeasure17", meal.getStrMeasure17());
            resultMap.put("strMeasure18", meal.getStrMeasure18());
            resultMap.put("strMeasure19", meal.getStrMeasure19());
            resultMap.put("strMeasure20", meal.getStrMeasure20());  */

            result.add(resultMap);
        }
              return result;
    }




}
