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
    @Query("SELECT * FROM meals WHERE DAY LIKE '0'")
    Observable<List<Meal>> getAllmeals();
    @Query("DELETE FROM meals")
    Completable deleteAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    io.reactivex.rxjava3.core.Completable insertMeal (Meal meal);
    @Delete
    io.reactivex.rxjava3.core.Completable deleteMeal (Meal meal);
    @Query("SELECT * FROM meals WHERE DAY LIKE '1'")
    Observable<List<Meal>> getSaturdaymeals();
    @Query("SELECT * FROM meals WHERE DAY LIKE '2'")
    Observable<List<Meal>> getSundaymeals();
    @Query("SELECT * FROM meals WHERE DAY LIKE '3'")
    Observable<List<Meal>> getMondaymeals();
    @Query("SELECT * FROM meals WHERE DAY LIKE '4'")
    Observable<List<Meal>> getTusdaymeals();
    @Query("SELECT * FROM meals WHERE DAY LIKE '5'")
    Observable<List<Meal>> getWednsdaymeals();
    @Query("SELECT * FROM meals WHERE DAY LIKE '6'")
    Observable<List<Meal>> getThursdaymeals();
    @Query("SELECT * FROM meals WHERE DAY LIKE '7'")
    Observable<List<Meal>> getFridaymeals();




}
