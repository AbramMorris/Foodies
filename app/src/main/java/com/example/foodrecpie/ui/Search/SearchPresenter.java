package com.example.foodrecpie.ui.Search;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.NetworkCallArea;
import com.example.foodrecpie.Network.NetworkCallCategories;
import com.example.foodrecpie.Network.Repo;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;

import com.example.foodrecpie.Network.ApiService;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.NetworkCallCategoriesMeals;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.NetworkCallIngredientsMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;

public class SearchPresenter implements NetworkCallBackCountry, NetworkCallCategories , NetworkCallCategoriesMeals, NetworkCallIngredientsMeals, NetworkCallArea {

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

    @SuppressLint("CheckResult")
    public void fetchSelectedAreas(String nationality){
        repository.resultMealsSelectedArea(this,nationality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {view.showMeals(response.getMeals());
                        },
                        throwable -> view.showError(throwable.getMessage())
                );



    }



@SuppressLint("CheckResult")
public void getCatMeals(String categoryName)
{
repository.getResulrCategoryMeals(this ,categoryName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                response -> view.ShowCategoryMeals(response.getMeals()),
                throwable -> view.showError(throwable.getMessage())
        );
}
    @SuppressLint("CheckResult")
    public void getAreaMeals(String area) {
        repository.resultMealArea(this, area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.showMeals(response.getMeals()),  // Success case
                        throwable -> {
                            view.showError(throwable.getMessage());  // Error case
                            Log.e("MealError", "Error fetching meals: ", throwable);
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
    public void filter(String query) {


    }

    @SuppressLint("CheckResult")
    public void fetchIngredients() {
//        apiService = getData().create(ApiService.class);
        repository.getIngredient(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.showIngredients(response.getMeals()),
                        throwable -> view.showError(throwable.getMessage())
                );
    }
@SuppressLint("CheckResult")
public void getIngMeals(String ingredient){
        repository.getIngMealsName(this,ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.showIngMeals(response.getMeals()),
                        throwable -> view.showError(throwable.getMessage())
                );


}

    @Override
    public void onSuccessCountry(List<Meal> meals) {
    }

    @Override
    public void onSuccess(List<CategoryResponse.MealsDTO> categories) {
        view.showCategories(categories);
    }

    @Override
    public void onFailure(String errorMessage) {

    }

    @Override
    public void onSuccessIngredients(List<IngredientResponse.MealsDTO> ingredients) {
        view.showIngredients(ingredients);
    }

    @Override
    public void onSuccessArea(List<AreaSearchModel.MealsDTO> mealList) {

    }

    @Override
    public void onSuccessCategories(List<CategoryMealResponse.MealsDTO> categories) {
        view.ShowCategoryMeals(categories);
    }

    @Override
    public void onSuccessIngredientsMeal(List<IngredientMealResponse.MealsDTO> ingredientMeals) {
        view.showIngMeals(ingredientMeals);
    }
//    public void addToFavorite(Meal meal) {
//        repository.insert(meal);
//    }
//    public void removeFromFavorite(Meal meal) {
//        repository.delete(meal);
//    }
//public void addtoFav(IngredientMealResponse.MealsDTO meal){
//        repository.insert(meal);
//}

}
