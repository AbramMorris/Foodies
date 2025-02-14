package com.example.foodrecpie.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecpie.Adapters.DailyAdapter;
import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Presenter.HomePressenter;
import com.example.foodrecpie.R;
import com.example.foodrecpie.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeViewInterface ,HomeOnClickListner {

    private FragmentHomeBinding binding;
    private DailyAdapter dailyAdapter;
//    RecyclerView recyclerView;
    HomePressenter homePressenter;

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
//        recyclerView=view.findViewById(R.id.recyclerDailyInspiration);

        dailyAdapter = new DailyAdapter(getContext(), new ArrayList<>());
        homePressenter = new HomePressenter(this);
        homePressenter.getDailyRandomMeals();
        binding.recyclerDailyInspiration.setAdapter(dailyAdapter);
//        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showRandomMeals(MealsPOJO Meals) {

        List<MealsPOJO> MealsList = new ArrayList<>();
        MealsList.add(Meals);
        dailyAdapter.setDailyMealList(MealsList);
        dailyAdapter.notifyDataSetChanged();
        binding.recyclerDailyInspiration.setAdapter(dailyAdapter);
    }

    @Override
    public void onAddToFavorite(MealsPOJO meal) {

    }

    @Override
    public void showMealDetails(MealsPOJO meal) {

    }
}