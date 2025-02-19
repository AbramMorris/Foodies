package com.example.foodrecpie.ui.Search.ModelSearchResponse;


import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.NetworkCallArea;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SelectedAreaContract {
    interface View {
        void showMeals(List<AreaSearchModel.MealsDTO> meals);
        void showError(String error);
    }

    interface Presenter {
        void getMealsByArea(String nationality);
        void onDestroy();
    }

    interface Repository {
        Observable<AreaSearchModel> resultMealsSelectedArea(NetworkCallArea network, String nationality);
    }
}
