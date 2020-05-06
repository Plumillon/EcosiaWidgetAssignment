package com.plumillonforge.ewa.presentation.app;

import android.content.ContentResolver;
import android.content.Context;

import com.plumillonforge.ewa.data.repositories.MusicsRepositoryImpl;
import com.plumillonforge.ewa.domain.interfaces.MusicsRepository;
import com.plumillonforge.ewa.domain.mappers.MusicModelMapper;
import com.plumillonforge.ewa.domain.useCases.GetRandomMusicUseCase;
import com.plumillonforge.ewa.presentation.asyncs.GetRandomMusicAsyncTask;
import com.plumillonforge.ewa.presentation.viewModels.PlayerWidgetViewModel;
import com.plumillonforge.ewa.presentation.viewModels.ViewModelFactory;

public class DependenciesProvider {
    public static Context providesContext() {
        return EWAApp.getInstance();
    }

    public static ContentResolver providesContentResolver() {
        return EWAApp.getInstance().getContentResolver();
    }

    public static PermissionsChecker providesPermissionChecker() {
        return new PermissionsChecker(providesContext());
    }

    public static ViewModelFactory providesViewModelFactory() {
        return new ViewModelFactory();
    }

    private static MusicsRepository providesMusicsRepository() {
        return new MusicsRepositoryImpl();
    }

    private static GetRandomMusicUseCase providesRandomMusicUseCase() {
        return new GetRandomMusicUseCase(providesMusicsRepository());
    }

    public static PlayerWidgetViewModel providesPlayerWidgetViewModel() {
        return new PlayerWidgetViewModel(providesRandomMusicUseCase(), new MusicModelMapper());
    }

    public static GetRandomMusicAsyncTask providesRandomMusicAsyncTask() {
        return new GetRandomMusicAsyncTask(providesRandomMusicUseCase(), new MusicModelMapper());
    }
}
