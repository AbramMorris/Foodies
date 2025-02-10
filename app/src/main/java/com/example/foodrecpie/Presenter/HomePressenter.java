package com.example.foodrecpie.Presenter;


import android.content.Intent;

import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.foodrecpie.Network.AreaNetworkDelegate;
import com.example.foodrecpie.area.selectedArea.model.Meal;

import com.example.foodrecpie.area.selectedArea.model.SelectedResponse;
import com.example.foodrecpie.home.view.HomeViewInterface;

import java.util.ArrayList;

public class HomePressenter implements HomePressenterInterface , AreaNetworkDelegate {

    GeneralRepositoryInterface genral ;
    HomeViewInterface view ;

    public HomePressenter(GeneralRepositoryInterface genral, HomeViewInterface view) {
        this.genral = genral;
        this.view = view;
    }



    @Override
    public void getDailyRandomMeals() {
        genral.resultAllMeals(this);
    }

    @Override
    public void addToFavorite(Meal meal) {
        genral.insert(meal);
    }

    @Override
    public void deleteAllMeals() {
        genral.deleteAllMeals();
    }

    @Override
    public void onSuccessResponse(ArrayList<MealsPOJO> response) {
        view.showRandomMeals(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}

