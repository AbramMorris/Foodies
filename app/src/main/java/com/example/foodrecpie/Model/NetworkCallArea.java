package com.example.foodrecpie.Model;

import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;

import java.util.List;

public interface NetworkCallArea {
    void onSuccessArea(List<AreaSearchModel.MealsDTO> mealList);
}
