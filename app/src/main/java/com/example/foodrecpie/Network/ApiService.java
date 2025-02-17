package com.example.foodrecpie.Network;

import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.Model.MealDetailResponse;
import com.example.foodrecpie.Model.RandemMealsPojo;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("random.php")
    Single<RandemMealsPojo> getRandemMeal();

    @GET("filter.php")
    Single<SelectedResponse> getMealsOfSelectedArea(@Query("a") String areaSelected);

    @GET("filter.php")
    Single<RandemMealsPojo> getMealsOfSelectedCategory(@Query("c") String categorySelected);

    @GET("filter.php")
    Single<RandemMealsPojo> getMealsOfSelectedIngredient(@Query("i") String ingredientSelected);

    @GET("search.php")
    Single<RandemMealsPojo> getMealsBySearch(@Query("s") String searchLitter);
    @GET("lookup.php")
    Single<MealDetailResponse> getMealDetails(@Query("i") String id);
    @GET("list.php?i=list")
    Single<RandemMealsPojo> getIngredientsList();
    @GET("list.php?a=list")
    Single<SelectedResponse> getAreasList();

    @GET("list.php?c=list")
    Single<RandemMealsPojo> getCategoriesList();

    @GET("search.php?f=a")
    Single<RandemMealsPojo> getRandomMeals();

    @GET("search.php?s")
    Single<RandemMealsPojo> getAllMeals();
}
