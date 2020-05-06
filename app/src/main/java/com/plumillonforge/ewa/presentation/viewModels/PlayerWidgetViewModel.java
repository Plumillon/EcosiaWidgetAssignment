package com.plumillonforge.ewa.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;

import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.domain.mappers.Mapper;
import com.plumillonforge.ewa.domain.useCases.UseCase;
import com.plumillonforge.ewa.presentation.asyncs.GetRandomMusicAsyncTask;
import com.plumillonforge.ewa.presentation.models.MusicModel;

public class PlayerWidgetViewModel extends LoadingViewModel implements GetRandomMusicAsyncTask.GetMusicsAsyncTaskListener {
    private UseCase<MusicEntity> useCase;
    private Mapper<MusicEntity, MusicModel> mapper;
    private MutableLiveData<MusicModel> music = new MutableLiveData<>();
    private MutableLiveData<Boolean> permissionGranted = new MutableLiveData<>();

    @Override
    public void onSuccess(MusicModel music) {
        this.music.postValue(music);
        this.isLoading.postValue(false);
    }

    @Override
    public void onError(Throwable throwable) {
        this.music.postValue(null);
        this.isLoading.postValue(false);
    }

    public PlayerWidgetViewModel(UseCase<MusicEntity> useCase, Mapper<MusicEntity, MusicModel> mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public void load() {
        this.isLoading.postValue(true);
        GetRandomMusicAsyncTask task = new GetRandomMusicAsyncTask(useCase, mapper);
        task.setListener(this);
        task.execute();
    }

    public MutableLiveData<MusicModel> getMusic() {
        return music;
    }

    public MutableLiveData<Boolean> isPermissionGranted() {
        return permissionGranted;
    }

    public void setPermissionGranted(boolean granted) {
        this.permissionGranted.postValue(granted);
    }
}
