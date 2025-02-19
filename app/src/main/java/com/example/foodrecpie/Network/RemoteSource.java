package com.example.foodrecpie.Network;

import com.example.foodrecpie.Model.NetworkCallArea;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.NetworkCallCategoriesMeals;

import io.reactivex.rxjava3.core.Observable;

public interface RemoteSource {
    public Observable<AreaSearchModel> resultMealsSelectedArea (NetworkCallArea network , String nationality);

    public  void resultMealsSelectedCategory (NetworkCallCategoriesMeals networkDelegate , String category);

    public  void resultMealsSelectedIngredient (NetworkCallBack networkDelegate , String ingredient);

    public  void resultIngredientCategory (NetworkCallBack networkDelegate );

    public  void resultCategory (NetworkCallBack networkDelegate );

    //create method get random meals
    public void resultRandomMeals(NetworkCallBack networkDelegate);

    public void resultAllMeals(NetworkCallBack networkDelegate);

    public void resultMealBySearch(NetworkCallBack networkDelegate , String searchLitter);


}
