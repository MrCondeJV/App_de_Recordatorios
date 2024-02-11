package com.anonproject.appfinal.ui.actividades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActividadesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ActividadesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Actividades");
    }

    public LiveData<String> getText() {
        return mText;
    }
}