package com.example.foodrecpie.Presenter;

import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.DataBase.DataBaseRepository;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.NetworkCallArea;
import com.example.foodrecpie.Network.Repo;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class MealDetailPresenter implements MealDetailPresenterInterface , NetworkCallArea, NetworkCallBackCountry {

    Repo GR;


    MealDetailViewInterface view ;

    public MealDetailPresenter(Repo GR , MealDetailViewInterface view) {
        this.GR = GR;
        this.view=view;

    }

//    @Override
//    public void addToFavorite(Meal meal) {
//       GR.insert(meal);
//    }


    @Override
    public void getSelectedMealDetails(String mealName) {
        GR.resultMealsSelectedArea(this,mealName);
    }


    @Override
    public void onSuccessCountry(List<Meal> response) {
        view.showSelectedMealDetails((ArrayList<Meal>)response);

    }

    @Override
    public void onFailure(String errorMessage) {

    }

    @Override
    public void onSuccessArea(List<AreaSearchModel.MealsDTO> mealList) {

    }
}
