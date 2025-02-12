package com.example.foodrecpie;

import androidx.lifecycle.LiveData;

import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Model.RandemMealsPojo;
import com.example.foodrecpie.Network.DailyInspireRemoteDataSorce;
import com.example.foodrecpie.Network.NetworkCallBack;

import java.util.List;

public class Repo {
    DailyInspireRemoteDataSorce dailyInspireRemoteDataSorce;
    private RandemMealsPojo randemMealsPojo;
    private static Repo repo=null;


    public static Repo getInstance(){
        if(repo==null){
            repo=new Repo();
        }
        return repo;
    }
    private Repo(){
        dailyInspireRemoteDataSorce= DailyInspireRemoteDataSorce.getInstance();

//        this.randemMealsPojo=randemMealsPojo;
    }
//    public LiveData<List<MealsPOJO>> getStoredData(){
//        return productLocalDataSource.getStoredData();
//    }
//    public void delete(MealsPOJO mealsPOJO){
//        productLocalDataSource.delete(product);
//    }
//    public void insert(Product product){
//        productLocalDataSource.insert(product);
//    }
    public void getAllProducts(NetworkCallBack networkCallBack){
        dailyInspireRemoteDataSorce.makeNetworkCall(networkCallBack);
    }
}
