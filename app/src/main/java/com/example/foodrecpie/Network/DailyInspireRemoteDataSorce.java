package com.example.foodrecpie.Network;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyInspireRemoteDataSorce implements RemoteSource {
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
    @SuppressLint("CheckResult")
    public void makeNetworkCall(NetworkCallBack networkCallBack ) {
        apiService = getData().create(ApiService.class);
        apiService.getRandemMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
                                Log.d(TAG, "onSuccess: " + response.getMeals().get(0).getStrMeal());
                                networkCallBack.onSuccess(response.getMeals());
                            }
                        },
                        error -> {
                            Log.e("Error", "Error fetching data", error);
                            networkCallBack.onFailure(error.getMessage());
                        }
                );

    }
    @SuppressLint("CheckResult")
    public void getListCountry(NetworkCallBackCountry networkCalBack) {
        apiService = getData().create(ApiService.class);
        apiService.getAreasList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SelectedResponse>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {
                               }

                               @Override
                               public void onSuccess(@NonNull SelectedResponse selectedResponse) {
                                   networkCalBack.onSuccessCountry(selectedResponse.getAreaMeals());
                                   for (int i = 0; i < selectedResponse.getAreaMeals().size(); i++) {
                                       Log.d("Countryyyyyyy", "onSuccess: " + selectedResponse.getAreaMeals().get(i).getStrArea());
                                   }
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
                                   Log.e("Error", "Error fetching data");
                               }
                           }
                );

    }


    @Override
    public void resultMealsSelectedCategory(NetworkCallBack networkCalBack, String category) {

    }

    @Override
    public void resultMealsSelectedIngredient(NetworkCallBack networkCalBack, String ingredient) {

    }

    @Override
    public void resultIngredientCategory(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultCategory(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultRandomMeals(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultAllMeals(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultMealBySearch(NetworkCallBack networkCalBack, String searchLitter) {

    }

}

