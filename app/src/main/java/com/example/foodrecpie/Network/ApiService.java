package com.example.foodrecpie.Network;

import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.Model.MealDetailResponse;
import com.example.foodrecpie.Model.RandemMealsPojo;
import com.example.foodrecpie.ui.Search.Data.AreaResponse;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("random.php")
    Single<RandemMealsPojo> getRandemMeal();

    @GET("filter.php")
    Observable<AreaResponse> getAreas(@Query("a") String areaName);

    @GET("filter.php")
    Single<CategoryResponse> getCategories(@Query("c") String categoryName);

    @GET("filter.php")
    Single<IngredientResponse> getIngredients(@Query("i") String ingredientName);

    @GET("search.php")
    Single<RandemMealsPojo> getMealsBySearch(@Query("s") String searchLitter);
    @GET("lookup.php")
    Single<MealDetailResponse> getMealDetails(@Query("i") String id);
    @GET("list.php?i=list")
    Single<RandemMealsPojo> getIngredientsList();
    @GET("list.php?a=list")
    Observable<SelectedResponse> getAreasList();

    @GET("list.php?c=list")
    Observable<CategoryResponse> getCategoriesList();

    @GET("search.php?f=a")
    Single<RandemMealsPojo> getRandomMeals();

    @GET("search.php?s")
    Single<RandemMealsPojo> getAllMeals();
}
