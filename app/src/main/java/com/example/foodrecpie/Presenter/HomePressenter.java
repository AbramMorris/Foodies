package com.example.foodrecpie.Presenter;


import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.ui.home.HomeViewInterface;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Network.NetworkCallBack;
import com.example.foodrecpie.Repo;

import java.util.List;

public class HomePressenter implements HomePressenterInterface , NetworkCallBack , NetworkCallBackCountry {


    HomeViewInterface view ;
    Repo repo;

    public HomePressenter(  HomeViewInterface view) {
        this.view = view;
        this.repo = Repo.getInstance();
    }



    @Override
    public void getDailyRandomMeals() {
       repo.getAllProducts(this);
    }
    @Override
    public void onSuccess(List<MealsPOJO> mealList) {
        view.showRandomMeals(mealList);
    }

    @Override
    public void onSuccessCountry(List<Meal> selectedResponse) {
        view.showCountry(selectedResponse);
    }

    @Override
    public void onFailure(String errorMessage) {

    }
    @Override
    public void getListACountry(){
        repo.getListAreaCountry(this);
    }
}

