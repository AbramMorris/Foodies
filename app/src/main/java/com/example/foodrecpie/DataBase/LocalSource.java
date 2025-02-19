package com.example.foodrecpie.DataBase;


import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.Model.FavModel;

import java.util.List;


import io.reactivex.rxjava3.core.Completable;

public interface LocalSource {

    Completable insertFavMeal(FavModel meal);
    Completable deleteFavMeal(FavModel meal);


}
