package com.example.foodrecpie.ui.sign_in;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Sign_inViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Sign_inViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}