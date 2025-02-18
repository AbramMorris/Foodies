package com.example.foodrecpie;

import static com.example.foodrecpie.Network.DailyInspireRemoteDataSorce.getData;

import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.RandemMealsPojo;
import com.example.foodrecpie.Network.ApiService;
import com.example.foodrecpie.Network.DailyInspireRemoteDataSorce;
import com.example.foodrecpie.Network.NetworkCallBack;
import com.example.foodrecpie.Network.NetworkCallCategories;
import com.example.foodrecpie.ui.Search.Data.AreaResponse;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Repo {
    DailyInspireRemoteDataSorce dailyInspireRemoteDataSorce;
    private RandemMealsPojo randemMealsPojo;
    private ApiService apiService;

    private static Repo repo=null;


    public static Repo getInstance(){
        if(repo==null){
            repo=new Repo();
        }
        return repo;
    }
    private Repo(){
        dailyInspireRemoteDataSorce= DailyInspireRemoteDataSorce.getInstance();
        apiService = getData().create(ApiService.class);
//        this.randemMealsPojo=randemMealsPojo;
    }
    public void getAllProducts(NetworkCallBack networkCallBack){
        dailyInspireRemoteDataSorce.makeNetworkCall(networkCallBack);
    }
    public Observable<SelectedResponse> getListAreaCountry(NetworkCallBackCountry networkCallBack){
        return dailyInspireRemoteDataSorce.getListCountry(networkCallBack);

    }
    public Observable<CategoryResponse> getCategories(NetworkCallCategories networkCallCategoreies) {
//        apiService = getData().create(ApiService.class);
        return dailyInspireRemoteDataSorce.getCategoriesList(networkCallCategoreies);

    }

//    public Observable<IngredientResponse> getIngredients() {
//        apiService = getData().create(ApiService.class);
////        return apiService.getIngredients();
//    }

//    public Observable<AreaResponse> getAreas() {
//        apiService = getData().create(ApiService.class);
//        return apiService.getAreas();
//    }
}

