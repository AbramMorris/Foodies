package com.example.foodrecpie.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import com.example.foodrecpie.CountryArea.Adapter.CountryAdapter;
import com.example.foodrecpie.CountryArea.AreaOnClickListner;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.Home.MealDetailsViewInterface;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Network.Repo;
import com.example.foodrecpie.Presenter.HomePressenter;
import com.example.foodrecpie.R;
import com.example.foodrecpie.databinding.FragmentHomeBinding;
import com.example.foodrecpie.ui.Meal_Details;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.SelectedCountryAdapter;
import com.example.foodrecpie.ui.Search.SearchPresenter;
import com.example.foodrecpie.ui.Search.SearchViewInterface;
import com.example.foodrecpie.ui.sign_in.Sign_inFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeViewInterface ,HomeOnClickListner, SearchViewInterface, AreaOnClickListner, SelectedCountryAdapter.OnMealClickListener, DailyAdapter.AdapterHelper {

    private FragmentHomeBinding binding;
    private DailyAdapter dailyAdapter;
    HomePressenter homePressenter;
    CountryAdapter countryAdapter;
    SearchPresenter presenter;
    Button signOut;
    Sign_inFragment sign;
    MealDetailsViewInterface mealDetailsViewInterface;

    public HomeFragment() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countryAdapter = new CountryAdapter(getContext(), new ArrayList<>(),this);
        dailyAdapter = new DailyAdapter(getContext(), new ArrayList<>(),this);
        homePressenter = new HomePressenter(this);
        homePressenter.getDailyRandomMeals();
        Intent received = requireActivity().getIntent();
        homePressenter.getListACountry();
        presenter = new SearchPresenter(this, Repo.getInstance());
        binding.recyclerDailyInspiration.setAdapter(dailyAdapter);
        signOut=view.findViewById(R.id.button3);
//        binding.recyclerArea.setAdapter(countryAdapter);
//        binding.recyclerDailyInspiration.setHasFixedSize(true);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign.signOut();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showRandomMeals(List<MealsPOJO> Meals) {
        dailyAdapter.setDailyMealList(Meals);
        dailyAdapter.notifyDataSetChanged();
        binding.recyclerDailyInspiration.setAdapter(dailyAdapter);
    }

    @Override
    public void showCountry(List<Meal> country) {
        countryAdapter.setAreas(country);
        countryAdapter.notifyDataSetChanged();
        binding.recyclerArea.setAdapter(countryAdapter);
    }

    @Override
    public void onAddToFavorite(MealsPOJO meal) {

    }
    public void showMealDetails(MealsPOJO meal) {
        Meal_Details mealDetailsFragment = new Meal_Details();
        Bundle bundle = new Bundle();
        bundle.putString("strMeal", meal.getStrMeal()); // Pass meal name
        mealDetailsFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.action_navigation_home_to_meal_Details, mealDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(String nationName) {

    }

    @Override
    public void showDetails(String id) {
    HomeFragmentDirections.ActionNavigationHomeToMealDetails action = HomeFragmentDirections.actionNavigationHomeToMealDetails(id);
    action.setMealId(id);
    Navigation.findNavController(requireView()).navigate(action);

    }

    @Override
    public void onMealClick(String meal) {
        presenter.fetchSelectedAreas(meal);
    }

    @Override
    public void showCategories(List<CategoryResponse.MealsDTO> categories) {

    }

    @Override
    public void showAreas(List<Meal> areas) {

    }

    @Override
    public void showMeals(List<AreaSearchModel.MealsDTO> meals) {

    }

    @Override
    public void showIngredients(List<IngredientResponse.MealsDTO> ingredients) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void ShowCategoryMeals(List<CategoryMealResponse.MealsDTO> meals) {

    }

    @Override
    public void showIngMeals(List<IngredientMealResponse.MealsDTO> meals) {

    }
}