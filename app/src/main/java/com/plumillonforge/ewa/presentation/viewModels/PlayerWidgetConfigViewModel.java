package com.plumillonforge.ewa.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayerWidgetConfigViewModel extends ViewModel {
    private MutableLiveData<Boolean> permissionGranted = new MutableLiveData<>();

    public MutableLiveData<Boolean> isPermissionGranted() {
        return permissionGranted;
    }

    public void setPermissionGranted(boolean granted) {
        this.permissionGranted.postValue(granted);
    }
}
