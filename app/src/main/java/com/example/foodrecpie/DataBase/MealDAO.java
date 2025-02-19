package com.example.foodrecpie.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.PlanModel;

import java.util.List;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface MealDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertFavMeal(FavModel meal);
    @Delete
    Completable deleteFavMeal(FavModel meal);
    @Query("SELECT * FROM fav_meal_table WHERE userId = :id")
    Single<List<FavModel>> getFavMeals(String id);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertPlanMeal(PlanModel meal);
    @Delete
    Completable deletePlanMeal(PlanModel meal);
    @Query("SELECT * FROM plan_meal_table WHERE userId = :id AND date = :date")
    Single<List<PlanModel>> getPlanMeals(String id , String date);


}
