package com.example.foodrecpie.Home;

import static com.example.foodrecpie.Network.DailyInspireRemoteDataSorce.getData;

import android.util.Log;

import com.example.foodrecpie.Network.ApiService;
import com.example.foodrecpie.Network.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenter {
    private MealDetailsViewInterface view;
    Repo repo;
    private ApiService apiService;

    public MealDetailsPresenter(MealDetailsViewInterface view, Repo repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getMealDetails(String strMeal) {
        apiService = getData().create(ApiService.class);
        apiService.getMealDetails(strMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null && !response.getMeals().isEmpty()) {
                                view.showMealDetails(response.getMeals().get(0));
                                Log.i("Abraaaaaam", "getMealDetails: "+response.getMeals().get(0));
                            } else {
                                view.showError("No meal details found.");
                            }
                        },
                        error -> {
                            view.showError("Error: " + error.getMessage());
                        }
                );
    }
}
