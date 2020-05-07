package com.plumillonforge.ewa.presentation.app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.plumillonforge.ewa.R;
import com.plumillonforge.ewa.presentation.services.PlayerService;

/**
 * Created by Flavien Norindr
 */
public class PlayerAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();

        if (action != null) {
            switch (action) {
                case AppWidgetManager.ACTION_APPWIDGET_DELETED:
                    Intent serviceIntent = new Intent(context, PlayerService.class);
                    serviceIntent.putExtra(PlayerService.KEY_COMMAND, PlayerService.COMMAND_DESTROY);
                    context.startService(serviceIntent);
                    break;

                case AppWidgetManager.ACTION_APPWIDGET_UPDATE:
                    update(context, intent);
                    break;
            }
        }
    }

    private void update(Context context, Intent intent) {
        String title = intent.getStringExtra(PlayerService.KEY_TITLE);
        String artist = intent.getStringExtra(PlayerService.KEY_ARTIST);
        String duration = intent.getStringExtra(PlayerService.KEY_DURATION);
        boolean isPlaying = intent.getBooleanExtra(PlayerService.KEY_PLAYING, false);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.player_widget);
        boolean nothingPlay = nothingPlay(title, artist, duration, isPlaying);

        Intent playPauseIntent = new Intent(context, PlayerService.class);
        playPauseIntent.putExtra(PlayerService.KEY_COMMAND, PlayerService.COMMAND_PLAY);
        views.setOnClickPendingIntent(R.id.imagePlayPause, PendingIntent.getService(context, 0, playPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        Intent nextIntent = new Intent(context, PlayerService.class);
        nextIntent.putExtra(PlayerService.KEY_COMMAND, PlayerService.COMMAND_NEXT);
        views.setOnClickPendingIntent(R.id.imageNext, PendingIntent.getService(context, 1, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        views.setViewVisibility(R.id.textPressPlay, (nothingPlay ? View.VISIBLE : View.GONE));
        views.setViewVisibility(R.id.layoutInfos, (nothingPlay ? View.GONE : View.VISIBLE));
        views.setTextViewText(R.id.textTitle, title);
        views.setTextViewText(R.id.textArtist, artist);
        views.setTextViewText(R.id.textDuration, duration);
        views.setImageViewResource(R.id.imagePlayPause, (isPlaying ? R.drawable.ic_pause : R.drawable.ic_play));

        ComponentName componentName = new ComponentName(context, PlayerAppWidgetProvider.class);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(componentName);

        for (int widgetId : widgetIds) {
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }

    private boolean nothingPlay(String title, String artist, String duration, boolean isPlaying) {
        return (title == null && artist == null && duration == null && !isPlaying);
    }
}
