package com.example.foodrecpie.Presenter;


import android.content.Intent;

import com.example.foodrecpie.HomeViewInterface;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Network.NetworkCallBack;
import com.example.foodrecpie.Repo;

import java.util.ArrayList;
import java.util.List;

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

