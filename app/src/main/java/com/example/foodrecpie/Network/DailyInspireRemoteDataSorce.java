package com.example.foodrecpie.Network;

import android.util.Log;

import com.example.foodrecpie.Model.RandemMealsPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyInspireRemoteDataSorce {
    private static final String URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String TAG = "Retrofit";
    private ApiService apiService;
    private static DailyInspireRemoteDataSorce Instance;
    public static DailyInspireRemoteDataSorce getInstance() {
        if (Instance == null) {
            Instance = new DailyInspireRemoteDataSorce();
        }
        return Instance;
    }
    private static retrofit2.Retrofit retrofit;

    private DailyInspireRemoteDataSorce(){}
    public static retrofit2.Retrofit getData() {
        if (retrofit == null) {

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void makeNetworkCall(NetworkCallBack networkCallBack ){
        apiService=getData().create(ApiService.class);
        Call<RandemMealsPojo> call =apiService.getRandemMeal();
        call.enqueue(new Callback<RandemMealsPojo>() {
            @Override
            public void onResponse(Call<RandemMealsPojo> call, Response<RandemMealsPojo> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.raw()+response.body().getMeals().get(0).getStrMeal());
                    networkCallBack.onSuccess(response.body().getMeals().get(0));
                }
            }

            @Override
            public void onFailure(Call<RandemMealsPojo> call, Throwable t) {
                Log.e("Error", "Error fetching data");
                networkCallBack.onFailure(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
