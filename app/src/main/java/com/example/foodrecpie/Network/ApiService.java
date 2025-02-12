package com.example.foodrecpie.Network;

import com.example.foodrecpie.Model.RandemMealsPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("random.php")
    Call<RandemMealsPojo> getRandemMeal();
}
