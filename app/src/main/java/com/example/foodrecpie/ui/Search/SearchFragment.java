package com.example.foodrecpie.ui.Search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SearchView;

import com.example.foodrecpie.Model.MealDetailResponse;
import com.example.foodrecpie.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
private SearchView searchView;
private ChipGroup chipGroup;
private RecyclerView recyclerView;
private List<MealDetailResponse.MealsDTO> products;
private List<MealDetailResponse.MealsDTO> filter;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchview);
        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                filterList(newText);
//                return true;
//            }
//        });
    }
//    private void filterList(){
//        List<item> filteredList = new ArrayList<>();
//        for (item item :itemList){
//            if(item.getItemName().toLowerCase().contains(text.toLowerCase)){
//                filteredList.add(item);
//            }
//        }
//        if(filteredList.isEmpty()){
//            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
//        }else{
//            adapter.setFilteredList(filteredList);
//        }
//    }

    private void setupFilterChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        String chipname = chip.getText().toString();
                        filter =new ArrayList<>();
                        for(Product product: products){
                            if (product.getBrand().equals(chipname))
                            {
                                filter.add(product);
                            }
                        }recyclerView.setAdapter(new Adaptor(filter));
                    }else{
                        recyclerView.setAdapter(new Adaptor(products));
                    }
                }
            });
        }
    }
}