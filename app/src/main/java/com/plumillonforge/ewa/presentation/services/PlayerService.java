package com.plumillonforge.ewa.presentation.services;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.plumillonforge.ewa.presentation.app.DependenciesProvider;
import com.plumillonforge.ewa.presentation.asyncs.GetRandomMusicAsyncTask;
import com.plumillonforge.ewa.presentation.models.MusicModel;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerService extends Service implements GetRandomMusicAsyncTask.GetMusicsAsyncTaskListener {
    public static final String KEY_COMMAND = "com.plumillonforge.ewa.command";
    public static final String KEY_TITLE = "com.plumillonforge.ewa.title";
    public static final String KEY_ARTIST = "com.plumillonforge.ewa.artist";
    public static final String KEY_DURATION = "com.plumillonforge.ewa.duration";
    public static final String KEY_PLAYING = "com.plumillonforge.ewa.playing";
    public static final int COMMAND_START = 0;
    public static final int COMMAND_PLAY = 1;
    public static final int COMMAND_PAUSE = 2;
    public static final int COMMAND_NEXT = 3;
    public static final int COMMAND_DESTROY = 4;
    private MediaPlayer player;
    private MusicModel currentMusic;

    private Timer updateTimer;
    private boolean isPlaying = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            int command = intent.getIntExtra(KEY_COMMAND, -1);
            handleCommand(command);
        }

        return START_STICKY;
    }

    private void handleCommand(int command) {
        switch (command) {
            case COMMAND_START:
            case COMMAND_NEXT:
                load();
                break;

            case COMMAND_PLAY:
                playPause();
                break;

            case COMMAND_PAUSE:
                pause();
                break;

            case COMMAND_DESTROY:
                stopSelf();
                break;
        }
    }

    private void playPause() {
        if (player != null) {
            if (player.isPlaying()) {
                pause();
            } else if (currentMusic != null && player.getTrackInfo() != null && player.getTrackInfo().length > 0) {
                play();
            }
        } else {
            load();
        }
    }

    private void play() {
        player.start();
        isPlaying = true;
        startUpdate();
    }

    private void load() {
        GetRandomMusicAsyncTask task = DependenciesProvider.providesRandomMusicAsyncTask();
        task.setListener(this);
        task.execute();
    }

    private void update() {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(PlayerService.KEY_TITLE, currentMusic.title);
        intent.putExtra(PlayerService.KEY_ARTIST, currentMusic.artist);
        intent.putExtra(PlayerService.KEY_DURATION, player != null ? "" + player.getCurrentPosition() : "");
        intent.putExtra(PlayerService.KEY_PLAYING, isPlaying);
        sendBroadcast(intent);
    }

    public void onDestroy() {
        stop();
        player = null;
    }

    @Override
    public void onSuccess(MusicModel music) {
        start(music);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    private void start(MusicModel music) {
        stop();
        player = MediaPlayer.create(DependenciesProvider.providesContext(), music.uri);
        player.setLooping(false);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pause();
            }
        });
        currentMusic = music;
        play();
    }

    private void startUpdate() {
        stopUpdate();
        updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 1000);
    }

    private void stopUpdate() {
        if (updateTimer != null) {
            updateTimer.cancel();
        }
    }

    private void stop() {
        if (player != null) {
            player.stop();
            player.release();
        }

        stopUpdate();
        currentMusic = null;
        isPlaying = false;
    }

    private void pause() {
        if (player != null && player.isPlaying()) {
            stopUpdate();
            player.pause();
        }

        isPlaying = false;
        update();
    }
}
