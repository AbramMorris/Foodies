package com.example.foodrecpie.Home;

import static com.example.foodrecpie.Network.DailyInspireRemoteDataSorce.getData;

import android.util.Log;

import com.example.foodrecpie.DataBase.DataBaseRepository;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.PlanModel;
import com.example.foodrecpie.Network.ApiService;
import com.example.foodrecpie.Network.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenter {
    private MealDetailsViewInterface view;
    Repo repo;
    DataBaseRepository repository;
    private ApiService apiService;

    public MealDetailsPresenter(MealDetailsViewInterface view, Repo repo,  DataBaseRepository repository) {
        this.view = view;
        this.repo = repo;
        this.repository = repository;
    }
    public void addToFavorite(FavModel meal) {
        repository.insertFavMeal(meal).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                view.showError("Meal added to favourites");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError(e.getMessage());
            }
        });
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

    public void insertPlanMeal(PlanModel planModel){
        repository.insertPlanMeal(planModel)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        view.showError("Meal added to plan");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.getMessage());

                    }
                });
    }
}
