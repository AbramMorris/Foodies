package com.example.foodrecpie.DataBase;

import android.content.Context;


import com.example.foodrecpie.CountryArea.Model.Meal;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.schedulers.Schedulers;

public class DataBaseRepository implements LocalSource {
    private Context context ;
    private MealDAO mealDAO;
    private Observable<List<Meal>> storedMeals;

    public DataBaseRepository(Context context) {
        this.context = context;
        AppDataBase db =AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.mealsDAO();
        storedMeals = mealDAO.getAllmeals();
    }

    private static  DataBaseRepository repo = null ;

    public static  DataBaseRepository getInstance(Context context){
        if(repo ==null){
            repo = new DataBaseRepository(context);
        }
        return repo;
    }

    @Override
    public Observable<List<Meal>> getStoredmeals() {
        return storedMeals;
    }

    @Override
    public Completable delete(Meal meal) {
       return mealDAO.deleteMeal(meal);
    }

    @Override
    public Completable insert(Meal meal) {
       return mealDAO.insertMeal(meal);
    }


    @Override
    public Observable<List<Meal>> getSutrdaydmeals() {
        return mealDAO.getSaturdaymeals();
    }

    @Override
    public Observable<List<Meal>> getSundaydmeals() {
        return mealDAO.getSundaymeals();
    }

    @Override
    public Observable<List<Meal>> getMondaymeals() {
        return mealDAO.getMondaymeals();
    }

    @Override
    public Observable<List<Meal>> getTusdaymeals() {
        return mealDAO.getTusdaymeals();
    }

    @Override
    public Observable<List<Meal>> getWednsdaymeals() {
        return mealDAO.getWednsdaymeals();
    }

    @Override
    public Observable<List<Meal>> getThursdaymeals() {
        return mealDAO.getThursdaymeals();
    }

    @Override
    public Observable<List<Meal>> getFridaymeals() {
        return mealDAO.getFridaymeals();
    }
}
