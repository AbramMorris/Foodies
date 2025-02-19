package com.example.foodrecpie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodrecpie.Adapters.FavAdapter;
import com.example.foodrecpie.DataBase.DataBaseRepository;
import com.example.foodrecpie.Model.FavModel;
import com.example.foodrecpie.databinding.FragmentFavListBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class FavListFrag extends Fragment {

    FragmentFavListBinding binding;
    FavAdapter adapter;
    FirebaseUser user;
    DataBaseRepository Repo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MainActivity activity = (MainActivity) requireActivity();
        activity.showBottomNav();
        user = FirebaseAuth.getInstance().getCurrentUser();
        Repo = DataBaseRepository.getInstance(getContext());
        LinearLayoutManager liner = new LinearLayoutManager(getContext());
        binding = FragmentFavListBinding.bind(view);
        adapter = new FavAdapter(new ArrayList<>());
        binding.favRecyleView.setLayoutManager(liner);
        binding.favRecyleView.setAdapter(adapter);
        if (user == null || user.getDisplayName() == null) {
            binding.guestImg.setVisibility(View.VISIBLE);
        }else {
            binding.guestImg.setVisibility(View.GONE);
            getFavList();
        }

    }

    public void getFavList() {
        Repo.getFavMeals(user.getUid())
                .subscribe(new SingleObserver<List<FavModel>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<FavModel> favModels) {
                        showList(favModels);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }

    public void showList(List<FavModel> favModels) {
        if (favModels == null || favModels.size()==0){
            binding.emptyImg.setVisibility(View.VISIBLE);
            adapter.setMyMeals(new ArrayList<>());
        }
        else {
            binding.emptyImg.setVisibility(View.GONE);
            adapter.setMyMeals(new ArrayList<>(favModels));
            adapter.setListener(new FavAdapter.onItemClickListener() {
                @Override
                public void onItemClick(FavModel meal) {
                    NavDirections action = FavListFragDirections.actionFavListFragToFavDetailsFragment(meal);
                    Navigation.findNavController(requireView()).navigate(action);
                }

                @Override
                public void onRemoveClick(FavModel meal) {
                    removeMeal(meal);
                }
            });
        }

    }

    public void removeMeal(FavModel meal) {
        Repo.deleteFavMeal(meal).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(getContext(), "Meal Removed Successfully", Toast.LENGTH_SHORT).show();
                getFavList();
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }
        });

    }

}