package com.example.foodrecpie.Presenter;


import com.example.foodrecpie.ui.home.HomeViewInterface;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Network.NetworkCallBack;
import com.example.foodrecpie.Repo;

public class HomePressenter implements HomePressenterInterface , NetworkCallBack  {


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
    public void onSuccess(MealsPOJO mealList) {
        view.showRandomMeals(mealList);
    }

    @Override
    public void onFailure(String errorMessage) {

    }
}

