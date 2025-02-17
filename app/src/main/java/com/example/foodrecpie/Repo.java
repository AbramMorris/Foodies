package com.example.foodrecpie;

import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.RandemMealsPojo;
import com.example.foodrecpie.Network.DailyInspireRemoteDataSorce;
import com.example.foodrecpie.Network.NetworkCallBack;

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
    public void getListAreaCountry(NetworkCallBackCountry networkCallBack){
        dailyInspireRemoteDataSorce.getListCountry(networkCallBack);
    }
}
