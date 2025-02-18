package com.example.foodrecpie.ui.Search;

import static com.example.foodrecpie.Network.DailyInspireRemoteDataSorce.getData;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodrecpie.CountryArea.Adapter.CountryAdapter;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Network.NetworkCallBack;
import com.example.foodrecpie.Network.NetworkCallCategories;
import com.example.foodrecpie.Repo;
import com.example.foodrecpie.ui.Search.Data.AreaResponse;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;

import com.example.foodrecpie.Network.ApiService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPresenter implements NetworkCallBackCountry, NetworkCallCategories {

    private SearchViewInterface view;
    private ApiService apiService;
    private Repo repository;
    private List<Meal> cashed;
    private List<Meal> filter;

    public SearchPresenter(SearchViewInterface view, Repo repository) {
        this.view = view;
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void fetchCategories() {
        repository.getCategories(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.showCategories(response.getMeals()),
                        throwable -> view.showError(throwable.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void fetchAreas() {
//        apiService = getData().create(ApiService.class);
        repository.getListAreaCountry(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            view.showAreas(response.getAreaMeals());
                        },
                        throwable -> {
                            Log.e("Error", "Error fetching data" + throwable.getMessage());
                        }
                );

    }
//    public List<Meal> filter(String query) {
////        filter.clear();
////        for ( Meal meal : cashed) {
////            if (meal.toString().toLowerCase().contains(query.toLowerCase())) {
////                filter.add(new Meal());
////            }
////        }
////        notifyDataSetChanged();
//        return cashed.stream().filter(meal -> meal.toString().toLowerCase().startsWith(query.toLowerCase()))
//                .collect(Collectors.toList());
//
//    }

//    public void fetchIngredients() {
//        apiService = getData().create(ApiService.class);
//        apiService.getIngredients()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(response -> {
//                    List<String> ingredients = response.getMeals()
//                            .stream()
//                            .map(IngredientResponse.Ingredient::getIngredientName)
//                            .collect(Collectors.toList());
//                    view.showData(ingredients);
//                }, throwable -> view.showError(throwable.getMessage()));
//    }


    @Override
    public void onSuccessCountry(List<Meal> meals) {
    }

    @Override
    public void onSuccess(List<CategoryResponse.CategoryDTO> categories) {
        view.showCategories(categories);
    }

    @Override
    public void onFailure(String errorMessage) {

    }

}
