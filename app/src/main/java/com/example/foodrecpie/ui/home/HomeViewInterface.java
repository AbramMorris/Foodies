package com.example.foodrecpie.ui.home;

    import com.example.foodrecpie.CountryArea.Model.Meal;
    import com.example.foodrecpie.Model.MealsPOJO;

    import java.util.List;

public interface HomeViewInterface {
        public void showRandomMeals(List<MealsPOJO> randomMeals);
        public void showCountry(List<Meal> country);
    }


