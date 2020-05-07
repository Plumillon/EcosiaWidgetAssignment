package com.plumillonforge.ewa.presentation.app;

import android.content.ContentResolver;
import android.content.Context;

import com.plumillonforge.ewa.data.datasources.MediaStoreMusicsDataSource;
import com.plumillonforge.ewa.data.repositories.MusicsRepositoryImpl;
import com.plumillonforge.ewa.domain.interfaces.MusicsRepository;
import com.plumillonforge.ewa.domain.mappers.MusicEntityMapper;
import com.plumillonforge.ewa.domain.mappers.MusicModelMapper;
import com.plumillonforge.ewa.domain.useCases.GetRandomMusicUseCase;
import com.plumillonforge.ewa.presentation.asyncs.GetRandomMusicAsyncTask;
import com.plumillonforge.ewa.presentation.viewModels.PlayerWidgetConfigViewModel;
import com.plumillonforge.ewa.presentation.viewModels.ViewModelFactory;

public class DependenciesProvider {
    public static Context providesContext() {
        return EWAApp.getInstance();
    }

    public static ContentResolver providesContentResolver() {
        return EWAApp.getInstance().getContentResolver();
    }

    public static ViewModelFactory providesViewModelFactory() {
        return new ViewModelFactory();
    }

    private static MusicsRepository providesMusicsRepository() {
        return new MusicsRepositoryImpl(new MediaStoreMusicsDataSource(), new MusicEntityMapper());
    }

    private static GetRandomMusicUseCase providesRandomMusicUseCase() {
        return new GetRandomMusicUseCase(providesMusicsRepository());
    }

    public static PlayerWidgetConfigViewModel providesPlayerWidgetConfigViewModel() {
        return new PlayerWidgetConfigViewModel();
    }

    public static GetRandomMusicAsyncTask providesRandomMusicAsyncTask() {
        return new GetRandomMusicAsyncTask(providesRandomMusicUseCase(), new MusicModelMapper());
    }
}
