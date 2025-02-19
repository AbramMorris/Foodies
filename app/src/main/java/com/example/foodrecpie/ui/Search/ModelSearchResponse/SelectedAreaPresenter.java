package com.example.foodrecpie.ui.Search.ModelSearchResponse;


import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.NetworkCallArea;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.List;

public class SelectedAreaPresenter implements SelectedAreaContract.Presenter, NetworkCallArea {

    private SelectedAreaContract.View view;
    private SelectedAreaContract.Repository repository;
    private CompositeDisposable disposables;

    public SelectedAreaPresenter(SelectedAreaContract.View view, SelectedAreaContract.Repository repository) {
        this.view = view;
        this.repository = repository;
        this.disposables = new CompositeDisposable();
    }

    @Override
    public void getMealsByArea(String nationality) {
        Disposable disposable = (Disposable) repository.resultMealsSelectedArea(this, nationality)
                .subscribe(
                        response -> onSuccessArea(response.getMeals())
                );

        disposables.add(disposable);
    }





    @Override
    public void onDestroy() {
        disposables.clear();
    }

    @Override
    public void onSuccessArea(List<AreaSearchModel.MealsDTO> mealList) {
        if (view != null) {
            view.showMeals(mealList);
        }
    }
}
