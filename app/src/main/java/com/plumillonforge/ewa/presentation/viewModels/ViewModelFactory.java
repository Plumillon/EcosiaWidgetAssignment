package com.plumillonforge.ewa.presentation.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.plumillonforge.ewa.presentation.app.DependenciesProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (PlayerWidgetConfigViewModel.class.equals(modelClass)) {
            return (T) DependenciesProvider.providesPlayerWidgetConfigViewModel();
        } else {
            throw new IllegalStateException("Unexpected value: " + modelClass);
        }
    }
}
