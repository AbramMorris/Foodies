package com.example.foodrecpie.Network;

import com.example.foodrecpie.Model.MealsPOJO;

import java.util.List;

public interface NetworkCallBack {
    void onSuccess(List<MealsPOJO> mealList);
    void onFailure(String errorMessage);

}
