package com.example.foodrecpie.ui.Search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.foodrecpie.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
private SearchView searchView;
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
}