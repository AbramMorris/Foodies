package com.example.foodrecpie.DataBase;


import com.example.foodrecpie.CountryArea.Model.Meal;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.rxjava3.core.Completable;

public interface LocalSource {
    public Observable<List<Meal>> getStoredmeals();
    public Completable delete(Meal meal);
    public Completable insert(Meal meal);
    public Observable<List<Meal>> getSutrdaydmeals();
    public Observable<List<Meal>> getSundaydmeals();
    public Observable<List<Meal>> getMondaymeals();
    public Observable<List<Meal>> getTusdaymeals();
    public Observable<List<Meal>> getWednsdaymeals();
    public Observable<List<Meal>> getThursdaymeals();
    public Observable<List<Meal>> getFridaymeals();
}
