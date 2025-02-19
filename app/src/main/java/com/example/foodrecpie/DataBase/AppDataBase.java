package com.example.foodrecpie.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodrecpie.CountryArea.Model.Meal;


@Database(entities = {Meal.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract MealDAO mealsDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,
                            "DataBase2")
                    .build();
        }
        return instance;
    }

}
