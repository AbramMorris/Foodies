package com.example.foodrecpie.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.MealsTypeConverter;
import com.example.foodrecpie.Model.PlanModel;


@Database(entities = {FavModel.class , PlanModel.class}, version = 5)
@TypeConverters({MealsTypeConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract MealDAO mealsDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,
                            "DataBase2")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
