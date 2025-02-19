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
}
