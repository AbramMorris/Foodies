package com.example.foodrecpie.Network;

import static com.example.foodrecpie.Network.DailyInspireRemoteDataSorce.getData;

import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.NetworkCallArea;
import com.example.foodrecpie.Model.RandemMealsPojo;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.NetworkCallCategoriesMeals;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.NetworkCallIngredientsMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Repo implements RepoInterface {
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

    public Observable<IngredientResponse> getIngredient(NetworkCallCategories networkCallCategoreies) {
        return dailyInspireRemoteDataSorce.getIngredients(networkCallCategoreies);

    }


//    @Override
//    public Completable delete(Meal meal) {
//
//        return null;
//    }

//    @Override
//    public Completable insert(Meal meal) {
//
//        return null;
//    }

//    @Override
//    public void deleteAllMeals() {
//
//    }



    @Override
    public Observable<AreaSearchModel> resultMealsSelectedArea(NetworkCallArea network, String nationality) {
        return dailyInspireRemoteDataSorce.resultMealsSelectedArea(network,nationality);
    }


    public Single<AreaSearchModel> resultMealArea(NetworkCallArea networkCalBack, String area){
        return dailyInspireRemoteDataSorce.resultMealArea(networkCalBack,area);
    }

public Single<CategoryMealResponse> getResulrCategoryMeals(NetworkCallCategoriesMeals networkCalBack, String category){
       return dailyInspireRemoteDataSorce.getCategoryMeals(networkCalBack,category);

}
public Single<IngredientMealResponse> getIngMealsName(NetworkCallIngredientsMeals networkCalBack, String ingredient){
        return dailyInspireRemoteDataSorce.getIngMealss(networkCalBack,ingredient);
}

    @Override
    public void resultMealsSelectedCategory(NetworkCallCategoriesMeals networkDelegate, String category) {

    }

    @Override
    public void resultMealsSelectedIngredient(NetworkCallBack networkDelegate, String ingredient) {

    }

    @Override
    public void resultIngredientCategory(NetworkCallBack networkDelegate) {

    }

    @Override
    public void resultCategory(NetworkCallBack networkDelegate) {

    }

    @Override
    public void resultRandomMeals(NetworkCallBack networkDelegate) {

    }

    @Override
    public void resultAllMeals(NetworkCallBack networkDelegate) {

    }

    @Override
    public void resultMealBySearch(NetworkCallBack networkDelegate, String searchLitter) {

    }

    @Override
    public Completable insertFavMeal(FavModel meal) {
        return null;
    }

    @Override
    public Completable deleteFavMeal(FavModel meal) {
        return null;
    }

//    public Observable<AreaResponse> getAreas() {
//        apiService = getData().create(ApiService.class);
//        return apiService.getAreas();
//    }
}

