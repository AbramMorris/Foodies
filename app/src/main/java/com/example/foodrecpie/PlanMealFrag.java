package com.example.foodrecpie;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.foodrecpie.Adapters.PlanAdapter;
import com.example.foodrecpie.DataBase.DataBaseRepository;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.Model.PlanModel;
import com.example.foodrecpie.databinding.FragmentPlanMealBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class PlanMealFrag extends Fragment {

    FragmentPlanMealBinding binding;
    FirebaseUser user;
    DataBaseRepository Repo;

    PlanAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = (MainActivity) requireActivity();
        activity.showBottomNav();
        binding = FragmentPlanMealBinding.bind(view);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Repo = DataBaseRepository.getInstance(getContext());
        LinearLayoutManager liner = new LinearLayoutManager(getContext());
        adapter = new PlanAdapter(new ArrayList<>());
        binding.recyclerplan.setLayoutManager(liner);
        binding.recyclerplan.setAdapter(adapter);

        if (user == null || user.getDisplayName() == null) {
            binding.datePicker.setVisibility(View.GONE);
            binding.recyclerplan.setVisibility(View.GONE);
            binding.guestImg.setVisibility(View.VISIBLE);
        }else {
            binding.datePicker.setVisibility(View.VISIBLE);
            binding.recyclerplan.setVisibility(View.VISIBLE);
            binding.guestImg.setVisibility(View.GONE);
            initDatePicker();
        }
    }

    private void initDatePicker(){
        final Calendar calendar = Calendar.getInstance();
        binding.datePicker.setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        binding.datePicker.setMaxDate(calendar.getTimeInMillis());

        String date = binding.datePicker.getDayOfMonth()+"/"+(binding.datePicker.getMonth()+1)+"/"+binding.datePicker.getYear();
        getPlanList(date);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                    getPlanList(date);
                }
            });
        }

    }

    public void getPlanList(String date) {
        String id = user.getUid();
        Repo.getPlanMeals(id,date).subscribe(new SingleObserver<List<PlanModel>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<PlanModel> planModels) {
                showList(planModels);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }
        });
    }

    private void showList(List<PlanModel> planModels) {
        adapter.setMyMeals(new ArrayList<>(planModels));
        adapter.setListener(new PlanAdapter.onItemClickListener() {
            @Override
            public void onItemClick(PlanModel meal) {
                goToDetails(meal);
            }
            @Override
            public void onRemoveClick(PlanModel meal) {
                removeMeal(meal);
            }
        });
        if (planModels == null || planModels.size()==0){
           binding.emptyImg.setVisibility(View.VISIBLE);
        }
        else {
            binding.emptyImg.setVisibility(View.GONE);
        }
    }

    private void removeMeal(PlanModel meal) {
        Repo.deletePlanMeal(meal)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Meal Removed Successfully", Toast.LENGTH_SHORT).show();
                        initDatePicker();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }

    private void goToDetails(PlanModel meal) {
        Navigation.findNavController(requireView()).navigate(PlanMealFragDirections.actionPlanMealFragToPlanDetailFragment(meal));

    }

}