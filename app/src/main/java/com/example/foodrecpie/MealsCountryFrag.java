package com.example.foodrecpie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.AreaSearchModel;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.SelectedAreaContract;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.SelectedAreaPresenter;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.SelectedAreaRepository;
import com.example.foodrecpie.ui.Search.ModelSearchResponse.SelectedCountryAdapter;

import java.util.List;

public class MealsCountryFrag extends Fragment implements SelectedAreaContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView errorMessage;
    private SelectedCountryAdapter adapter;
    private SelectedAreaPresenter presenter;

    public MealsCountryFrag() {
        // Required empty public constructor
    }

    public static MealsCountryFrag newInstance() {
        return new MealsCountryFrag();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meals_country, container, false);

        recyclerView = view.findViewById(R.id.recyle_area_meals);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new SelectedCountryAdapter(getContext(), null, this);
        recyclerView.setAdapter(adapter);

        presenter = new SelectedAreaPresenter(this, new SelectedAreaRepository());
        presenter.getMealsByArea("Canadian"); // Change nationality as needed

        return view;
    }

    @Override
    public void showMeals(List<AreaSearchModel.MealsDTO> meals) {
        recyclerView.setVisibility(View.VISIBLE);
        adapter.updateData(meals);
    }

    @Override
    public void showError(String error) {
        recyclerView.setVisibility(View.GONE);
    }

    private void showMealDetails(Meal meal) {
//        MealDetailsFragment fragment = MealDetailsFragment.newInstance(meal);
//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
