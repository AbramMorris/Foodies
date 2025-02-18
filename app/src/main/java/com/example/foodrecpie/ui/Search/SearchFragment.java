package com.example.foodrecpie.ui.Search;

import static androidx.core.widget.TextViewKt.addTextChangedListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecpie.CountryArea.Adapter.CountryAdapter;
import com.example.foodrecpie.CountryArea.AreaOnClickListner;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.CountryArea.Model.SelectedResponse;
import com.example.foodrecpie.R;
import com.example.foodrecpie.Repo;
import com.example.foodrecpie.ui.Search.Data.AreaResponse;
import com.example.foodrecpie.ui.Search.Data.CategoryResponse;
import com.example.foodrecpie.ui.Search.Data.IngredientResponse;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.CategoryAdapter;
import com.example.foodrecpie.ui.Search.SearchAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.checkerframework.checker.units.qual.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements SearchViewInterface , AreaOnClickListner {
    private RecyclerView recyclerView;
    private SearchPresenter presenter;
    private SearchAdapter adapter;
    private CountryAdapter countryAdapter;
    private ChipGroup chipGroup;
    private EditText searchView;
    private List<Meal> myApiMeals;
    private CategoryAdapter categoryAdapter;
    List<Meal> MyAreas = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recycler_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        chipGroup = view.findViewById(R.id.chip_group_filter);
        searchView = view.findViewById(R.id.pt_searchForAllMeals);

        presenter = new SearchPresenter(this,Repo.getInstance());

        // Default: Load categories
//        presenter.getCategories();

        // Handle Chip Selection
        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.chip2) {
                presenter.fetchAreas();
            } else if (checkedId == R.id.chip3) {
                presenter.fetchCategories();
            } else if (checkedId == R.id.chip4) {
//                presenter.fetchIngredients();
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
                FiltterArea( filter);
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
    public void showCategories(List<CategoryResponse.CategoryDTO> categories) {
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void showIngredients(List<IngredientResponse.Ingredient> ingredients) {
//        adapter = new SearchAdapter(ingredients);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showAreas(List<Meal> areas) {
        myApiMeals = areas;
        adapter = new SearchAdapter(areas,getContext(),this);
        Log.d("Abram", "showAreas: "+areas.size());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(String nationName) {

    }
    private void FiltterArea(String s) {
//        List<Meal> myFilterAreas = myApiMeals.stream()
//                .filter(meal -> meal.getStrArea().toLowerCase().contains(s.toLowerCase()))
//                .collect(Collectors.toList());
        Observable.fromIterable(myApiMeals)
                .filter(ingredient -> ingredient.getStrIngredient().toLowerCase().contains(s.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(list ->{
                    countryAdapter.updateData(list);
                    countryAdapter.notifyDataSetChanged();
                } ,throwable -> Log.e("Abram", "FiltterArea: ",throwable));
    }
}

