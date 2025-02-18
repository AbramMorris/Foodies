package com.example.foodrecpie.Network;

import com.example.foodrecpie.ui.Search.Data.CategoryResponse;

import java.util.List;

public interface NetworkCallCategories {
    void onSuccess(List<CategoryResponse.CategoryDTO> categories);
    void onFailure(String message);

}
