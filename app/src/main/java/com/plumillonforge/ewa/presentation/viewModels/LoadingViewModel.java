package com.plumillonforge.ewa.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

abstract class LoadingViewModel extends ViewModel {
    protected MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    protected MutableLiveData<Boolean> isEmpty = new MutableLiveData<>();

    abstract void load();

    LiveData<Boolean> isLoading() {
        return isLoading;
    }

    LiveData<Boolean> isEmpty() {
        return isEmpty;
    }
}
