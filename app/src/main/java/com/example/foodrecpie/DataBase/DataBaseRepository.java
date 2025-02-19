package com.example.foodrecpie.DataBase;

import android.content.Context;


import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.PlanModel;

import java.util.List;




import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DataBaseRepository implements LocalSource {
    private Context context ;
    private MealDAO mealDAO;
    private Observable<List<Meal>> storedMeals;

    public DataBaseRepository(Context context) {
        this.context = context;
        AppDataBase db =AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.mealsDAO();
//        storedMeals = mealDAO.getAllmeals();
    }

    private static  DataBaseRepository repo = null ;

    public static  DataBaseRepository getInstance(Context context){
        if(repo ==null){
            repo = new DataBaseRepository(context);
        }
        return repo;
    }


    @Override
    public Completable insertFavMeal(FavModel meal) {
        return mealDAO.insertFavMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deleteFavMeal(FavModel meal) {
        return mealDAO.deleteFavMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Single<List<FavModel>> getFavMeals(String id) {
        return mealDAO.getFavMeals(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertPlanMeal(PlanModel meal) {
        return mealDAO.insertPlanMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Completable deletePlanMeal(PlanModel meal) {
        return mealDAO.deletePlanMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Single<List<PlanModel>> getPlanMeals(String id , String date) {
        return mealDAO.getPlanMeals(id,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
