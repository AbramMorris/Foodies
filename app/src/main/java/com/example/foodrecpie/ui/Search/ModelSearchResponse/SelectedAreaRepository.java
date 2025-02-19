package com.example.foodrecpie.ui.Search.ModelSearchResponse;

import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.NetworkCallArea;
import com.example.foodrecpie.Network.DailyInspireRemoteDataSorce;

import io.reactivex.rxjava3.core.Observable;

public class SelectedAreaRepository implements SelectedAreaContract.Repository {

    private DailyInspireRemoteDataSorce remoteDataSource;

    public SelectedAreaRepository() {
        this.remoteDataSource = DailyInspireRemoteDataSorce.getInstance();

    }

    @Override
    public Observable<AreaSearchModel> resultMealsSelectedArea(NetworkCallArea network, String nationality) {
        return remoteDataSource.resultMealsSelectedArea(network, nationality);
    }
}

