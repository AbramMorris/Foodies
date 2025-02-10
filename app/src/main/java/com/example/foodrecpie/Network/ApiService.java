package com.example.foodrecpie.Network;

import com.example.foodrecpie.Model.RandemMealsPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("RandemMeals")
    Call<RandemMealsPojo> getRandemMeal();
}
