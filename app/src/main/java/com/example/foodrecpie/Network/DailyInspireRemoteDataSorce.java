package com.example.foodrecpie.Network;

import android.util.Log;

import com.example.foodrecpie.Model.RandemMealsPojo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io. reactivex. rxjava3.core. Scheduler;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
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
    private static Retrofit retrofit;

    private DailyInspireRemoteDataSorce(){}
    public static Retrofit getData() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void makeNetworkCall(NetworkCallBack networkCallBack ) {
        apiService = getData().create(ApiService.class);
        apiService.getRandemMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
                                Log.d(TAG, "onSuccess: " + response.getMeals().get(0).getStrMeal());
                                networkCallBack.onSuccess(response.getMeals().get(0));
                            }
                        },
                        error -> {
                            Log.e("Error", "Error fetching data", error);
                            networkCallBack.onFailure(error.getMessage());
                        }
                );

    }






//        call.enqueue(new Callback<RandemMealsPojo>() {
//            @Override
//            public void onResponse(Call<RandemMealsPojo> call, Response<RandemMealsPojo> response) {
//                if(response.isSuccessful()){
//                    Log.d(TAG, "onResponse: "+response.raw()+response.body().getMeals().get(0).getStrMeal());
//                    networkCallBack.onSuccess(response.body().getMeals().get(0));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RandemMealsPojo> call, Throwable t) {
//                Log.e("Error", "Error fetching data");
//                networkCallBack.onFailure(t.getMessage());
//                t.printStackTrace();
//            }
//        });
    }

