package com.plumillonforge.ewa.data.datasources;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.plumillonforge.ewa.presentation.app.DependenciesProvider;

public class DeviceMusicsDataSource {
    private ContentResolver contentResolver = DependenciesProvider.providesContentResolver();
    private String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
    };

    public Cursor getMusics() {
        return this.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null);
    }
}
