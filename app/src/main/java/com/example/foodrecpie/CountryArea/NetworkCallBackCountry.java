package com.example.foodrecpie.CountryArea;

import com.example.foodrecpie.CountryArea.Model.Meal;

import java.util.List;

public interface NetworkCallBackCountry {
    void onSuccessCountry(List<Meal> meals);
    void onFailure(String errorMessage);

}
