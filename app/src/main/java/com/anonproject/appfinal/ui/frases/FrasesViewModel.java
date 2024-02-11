package com.anonproject.appfinal.ui.frases;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FrasesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FrasesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Frases");
    }

    public LiveData<String> getText() {
        return mText;
    }
}