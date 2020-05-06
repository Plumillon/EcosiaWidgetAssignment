package com.plumillonforge.ewa.presentation.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.plumillonforge.ewa.presentation.app.DependenciesProvider;
import com.plumillonforge.ewa.presentation.asyncs.GetRandomMusicAsyncTask;
import com.plumillonforge.ewa.presentation.models.MusicModel;

public class PlayerService extends Service implements GetRandomMusicAsyncTask.GetMusicsAsyncTaskListener {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        load();
    }

    private void load() {
        GetRandomMusicAsyncTask task = DependenciesProvider.providesRandomMusicAsyncTask();
        task.setListener(this);
        task.execute();
    }

    public void onDestroy() {
        player.stop();
    }

    @Override
    public void onSuccess(MusicModel music) {
        play(music);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    private void play(MusicModel music) {
        player = MediaPlayer.create(DependenciesProvider.providesContext(), music.uri);
        player.setLooping(false);
        player.start();
    }
}
