package com.example.foodrecpie.ui.Search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecpie.CountryArea.Adapter.CountryAdapter;
import com.example.foodrecpie.CountryArea.AreaOnClickListner;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.MainActivity;
import com.example.foodrecpie.Presenter.SelectedAreaViewInterface;
import com.example.foodrecpie.R;
import com.example.foodrecpie.Network.Repo;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;
import com.example.foodrecpie.ui.Search.Data.SelectedAreaOnClickListner;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryAdapter;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryMealsAdapter;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientAdapter;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientMealResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.IngredientMealsAdapter;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.SelectedCountryAdapter;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements SearchViewInterface , SelectedAreaViewInterface,AreaOnClickListner, SelectedAreaOnClickListner, SelectedCountryAdapter.OnMealClickListener, CategoryMealsAdapter.OnCatClickListener, IngredientMealsAdapter.OnIngredientClickListener {
    private RecyclerView recyclerView;
    private SearchPresenter presenter;
    private SearchAdapter adapter;
    private CountryAdapter countryAdapter;
    private ChipGroup chipGroup;
    private EditText searchView;
    private List<Meal> myApiMeals;
    private CategoryAdapter categoryAdapter;
    private IngredientAdapter ingredientAdapter;
    private SelectedCountryAdapter ad;
    private CategoryMealsAdapter categoryMealsAdapter;
    CardView cardView;
    SelectedAreaOnClickListner listner;
    private IngredientMealsAdapter ingredientMealsAdapter;
    List<CategoryResponse.MealsDTO> cachedMeals;
    String selectedChip;
    List<IngredientResponse.MealsDTO> cachedIngredients;


    List<Meal> MyAreas = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        MainActivity activity = (MainActivity) requireActivity();
        activity.showBottomNav();
        recyclerView = view.findViewById(R.id.recycler_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cardView = view.findViewById(R.id.area_view_Card);
        chipGroup = view.findViewById(R.id.chip_group_filter);
        searchView = view.findViewById(R.id.pt_searchForAllMeals);
        presenter = new SearchPresenter(this,Repo.getInstance());
        countryAdapter = new CountryAdapter(getContext(),MyAreas,this);

        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.chip2) {
                presenter.fetchAreas();
                selectedChip = "area";
            } else if (checkedId == R.id.chip3) {
                presenter.fetchCategories();
                selectedChip = "category";
            } else if (checkedId == R.id.chip4) {
                presenter.fetchIngredients();
                selectedChip = "ingredient";
            }
        });



        // Handle Search Query
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter = s.toString();
                if (selectedChip.equals("area")) {
                    filtterArea(filter);
                } else if (selectedChip.equals("category")) {
                    filtterCategory(filter);
                }
                else if (selectedChip.equals("ingredient")) {
                    filtterIngredient(filter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }

//    private void filterResults(String query) {
//        if (adapter != null) {
//            adapter.filter(query);
//        }
//    }


    @Override
    public void showCategories(List<CategoryResponse.MealsDTO> categories) {
        cachedMeals = new ArrayList<>(categories);
        categoryAdapter = new CategoryAdapter(getContext(), categories,this);
        recyclerView.setAdapter(categoryAdapter);
    }


    @Override
    public void showAreas(List<Meal> areas) {
        myApiMeals = new ArrayList<>(areas);
        adapter = new SearchAdapter(areas,getContext(),this);
        Log.d("Abram", "showAreas: "+areas.size());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMeals(List<AreaSearchModel.MealsDTO> meals) {
        ad = new SelectedCountryAdapter(meals);
        recyclerView.setAdapter(ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ad.setListener(new SelectedCountryAdapter.OnMealClickListener() {
            @Override
            public void onMealClick(String meal) {
                navigateToDetails(meal);
            }
        });
    }


    @Override
    public void showIngredients(List<IngredientResponse.MealsDTO> ingredients) {
        cachedIngredients=new ArrayList<>(ingredients);
        ingredientAdapter = new IngredientAdapter(getContext(), ingredients,this);
        recyclerView.setAdapter(ingredientAdapter);
    }
    @Override
    public void showData(ArrayList<Meal> meals) {
        this.myApiMeals = meals;
        ad.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowCategoryMeals(List<CategoryMealResponse.MealsDTO> meals) {
        categoryMealsAdapter = new CategoryMealsAdapter(meals);
        recyclerView.setAdapter(categoryMealsAdapter);
        categoryMealsAdapter.setListener(new CategoryMealsAdapter.OnCatClickListener() {
            @Override
            public void onCatClick(String mealId) {
                navigateToDetails(mealId);
            }
        });
    }

    @Override
    public void showIngMeals(List<IngredientMealResponse.MealsDTO> meals) {
        ingredientMealsAdapter = new IngredientMealsAdapter( meals);
        recyclerView.setAdapter(ingredientMealsAdapter);
        ingredientMealsAdapter.setListener(new IngredientMealsAdapter.OnIngredientClickListener() {
            @Override
            public void onIngredientMealClick(String mealId) {
                navigateToDetails(mealId);
            }
        });

    }

    private void navigateToDetails(String meal) {
        Navigation.findNavController(requireView())
                .navigate(SearchFragmentDirections.actionSearchFragmentToMealDetails(meal));
    }


    @SuppressLint("CheckResult")
    private void filtterArea(String s) {
//        List<Meal> myFilterAreas = myApiMeals.stream()
//                .filter(meal -> meal.getStrArea().toLowerCase().contains(s.toLowerCase()))
//                .collect(Collectors.toList());
        Log.d("filteredArea", "FiltterArea: "+myApiMeals.size());
        Observable.fromIterable(myApiMeals)
                .filter(meal -> meal.getStrArea().toLowerCase().contains(s.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(list ->{
                    countryAdapter.updateData(list);
                    countryAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(countryAdapter);
                } ,throwable -> Log.e("Abram", "FiltterArea: ",throwable));
    }
    @SuppressLint("CheckResult")
    private void filtterCategory(String s) {
//        List<Meal> myFilterAreas = myApiMeals.stream()
//                .filter(meal -> meal.getStrArea().toLowerCase().contains(s.toLowerCase()))
//                .collect(Collectors.toList());
        Log.d("filteredArea", "FiltterArea: "+cachedMeals.size());
        Observable.fromIterable(cachedMeals)
                .filter(meal -> meal.getStrCategory().toLowerCase().contains(s.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(list ->{
                    categoryAdapter.updateData(list);
                    categoryAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(categoryAdapter);
                } ,throwable -> Log.e("Abram", "FiltterArea: ",throwable));
    }
    @SuppressLint("CheckResult")
    private void filtterIngredient(String s) {
//        List<Meal> myFilterAreas = myApiMeals.stream()
//                .filter(meal -> meal.getStrArea().toLowerCase().contains(s.toLowerCase()))
//                .collect(Collectors.toList());
        Log.d("filteredArea", "FiltterArea: "+cachedIngredients.size());
        Observable.fromIterable(cachedIngredients)
                .filter(meal -> meal.getStrIngredient().toLowerCase().contains(s.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(list ->{
                    ingredientAdapter.updateData(list);
                    ingredientAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(ingredientAdapter);
                } ,throwable -> Log.e("Abram", "FiltterArea: ",throwable));
    }



    @Override
    public void ShowMealDetails(Meal meal) {
//        ad = new SelectedCountryAdapter(getContext(),meal,this);
//        recyclerView.setAdapter(ad);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        presenter.fetchSelectedAreas(meal.getStrArea());
    }
    @Override
    public void onMealClick(String meal) {
        presenter.fetchSelectedAreas(meal);
    }

    @Override
    public void onClick(String nationName) {
        Log.d("Abram", "onClick: "+nationName);
//    presenter.fetchSelectedAreas(nationName);
    }

    @Override
    public void onCatClick(String mealId) {
        presenter.getCatMeals(mealId);
    }

    @Override
    public void onIngredientMealClick(String mealId) {
     presenter.getIngMeals(mealId);
    }


    public void addMealToFav(Meal meal)
    {
    }
}

