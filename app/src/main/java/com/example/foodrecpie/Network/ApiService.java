package com.example.foodrecpie.Network;

import com.example.foodrecpie.Model.RandemMealsPojo;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("random.php")
    Single<RandemMealsPojo> getRandemMeal();
}
