package com.example.foodrecpie.Network;

public interface RemoteSource {

    public  void resultMealsSelectedCategory (NetworkCallBack networkDelegate , String category);

    public  void resultMealsSelectedIngredient (NetworkCallBack networkDelegate , String ingredient);

    public  void resultIngredientCategory (NetworkCallBack networkDelegate );

    public  void resultCategory (NetworkCallBack networkDelegate );

    //create method get random meals
    public void resultRandomMeals(NetworkCallBack networkDelegate);

    public void resultAllMeals(NetworkCallBack networkDelegate);

    public void resultMealBySearch(NetworkCallBack networkDelegate , String searchLitter);


}
