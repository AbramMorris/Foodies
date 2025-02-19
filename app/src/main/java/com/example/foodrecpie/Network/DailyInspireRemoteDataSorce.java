package com.example.foodrecpie.Network;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.CountryArea.NetworkCallBackCountry;
import com.example.foodrecpie.Model.NetworkCallArea;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.NetworkCallCategoriesMeals;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.NetworkCallIngredientsMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyInspireRemoteDataSorce implements RemoteSource {
    private static final String URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String TAG = "Retrofit";
    private ApiService apiService;
    private static DailyInspireRemoteDataSorce Instance;

    public static DailyInspireRemoteDataSorce getInstance() {
        if (Instance == null) {
            Instance = new DailyInspireRemoteDataSorce();
        }
        return Instance;
    }

    private static Retrofit retrofit;

    private DailyInspireRemoteDataSorce() {
    }

    public static Retrofit getData() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(NetworkCallBack networkCallBack) {
        apiService = getData().create(ApiService.class);
        apiService.getRandemMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
                                Log.d(TAG, "onSuccess: " + response.getMeals().get(0).getStrMeal());
                                networkCallBack.onSuccess(response.getMeals());
                            }
                        },
                        error -> {
                            Log.e("Error", "Error fetching data", error);
                            networkCallBack.onFailure(error.getMessage());
                        }
                );

    }

    @SuppressLint("CheckResult")
    public Observable<SelectedResponse> getListCountry(NetworkCallBackCountry networkCalBack) {
        apiService = getData().create(ApiService.class);
        apiService.getAreasList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectedResponse>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull SelectedResponse selectedResponse) {
                                   networkCalBack.onSuccessCountry(selectedResponse.getAreaMeals());
                                   for (int i = 0; i < selectedResponse.getAreaMeals().size(); i++) {
                                       Log.d("Countryyyyyyy", "onSuccess: " + selectedResponse.getAreaMeals().get(i).getStrArea());
                                   }
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
                                   Log.e("Error", "Error fetching data");
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );

        return apiService.getAreasList();
    }

    public Observable<CategoryResponse> getCategoriesList(NetworkCallCategories networkCallCategories) {
        apiService = getData().create(ApiService.class);
        apiService.getCategoriesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResponse>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull CategoryResponse selectedResponse) {
                                   networkCallCategories.onSuccess(selectedResponse.getMeals());
                                   for (int i = 0; i < selectedResponse.getMeals().size(); i++) {
                                       Log.d("Countryyyyyyy", "onSuccess: " + selectedResponse.getMeals().get(i).getStrCategory());
                                   }
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
                                   Log.e("Error", "Error fetching data");
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
        return apiService.getCategoriesList();
    }

    public Observable<IngredientResponse> getIngredients(NetworkCallCategories networkCallCategories) {
        apiService = getData().create(ApiService.class);
        apiService.getIngredientsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IngredientResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull IngredientResponse ingredientResponse) {
                        networkCallCategories.onSuccessIngredients(ingredientResponse.getMeals());
                        for (int i = 0; i < ingredientResponse.getMeals().size(); i++) {
                            Log.d("ingredientyyyy", "onSuccess: " + ingredientResponse.getMeals().get(i).getStrIngredient());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return apiService.getIngredientsList();
    }


    @Override
    public Observable<AreaSearchModel> resultMealsSelectedArea(NetworkCallArea network, String nationality) {
        apiService = getData().create(ApiService.class);
        apiService.getAreas(nationality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaSearchModel>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull AreaSearchModel areaResponse) {
                                   network.onSuccessArea(areaResponse.getMeals());

                               }

                               @Override
                               public void onError(@NonNull Throwable e) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           }

                );
        return apiService.getAreas(nationality);
    }

    public Single<AreaSearchModel> resultMealArea(NetworkCallArea networkCalBack, String area)
    {
        apiService = getData().create(ApiService.class);
        apiService.getMealsAreas(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AreaSearchModel>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onSuccess(@NonNull AreaSearchModel areaSearchModel) {
                               networkCalBack.onSuccessArea(areaSearchModel.getMeals());
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {

                               }
                           }
                );
        return apiService.getMealsAreas(area);
    }

    public Single<CategoryMealResponse> getCategoryMeals(NetworkCallCategoriesMeals networkCalBack, String category)
    {
        apiService = getData().create(ApiService.class);
        apiService.getCategories(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoryMealResponse>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onSuccess(@NonNull CategoryMealResponse categoryResponse) {
                                   networkCalBack.onSuccessCategories(categoryResponse.getMeals());
                               }


                               @Override
                               public void onError(@NonNull Throwable e) {

                               }
                           }
                );


return apiService.getCategories(category);

    }

    public Single<IngredientMealResponse> getIngMealss(NetworkCallIngredientsMeals networkCalBack, String ingredient) {
        apiService = getData().create(ApiService.class);
        apiService.getIngredients(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<IngredientMealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull IngredientMealResponse ingredientMealResponse) {
                        networkCalBack.onSuccessIngredientsMeal(ingredientMealResponse.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return apiService.getIngredients(ingredient);
    }
    @Override
    public void resultMealsSelectedCategory(NetworkCallCategoriesMeals networkCalBack, String category) {

    }

    @Override
    public void resultMealsSelectedIngredient(NetworkCallBack networkCalBack, String ingredient) {

    }

    @Override
    public void resultIngredientCategory(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultCategory(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultRandomMeals(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultAllMeals(NetworkCallBack networkCalBack) {

    }

    @Override
    public void resultMealBySearch(NetworkCallBack networkCalBack, String searchLitter) {

    }

}

