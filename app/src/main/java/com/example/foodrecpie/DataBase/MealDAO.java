package com.example.foodrecpie.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodrecpie.CountryArea.Model.Meal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meal_table")
    Observable<List<Meal>> getAllmeals();
    @Query("DELETE FROM meal_table")
    Completable deleteAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    io.reactivex.rxjava3.core.Completable insertMeal (Meal meal);
    @Delete
    io.reactivex.rxjava3.core.Completable deleteMeal (Meal meal);




}
