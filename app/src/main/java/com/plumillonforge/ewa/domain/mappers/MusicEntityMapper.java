package com.plumillonforge.ewa.domain.mappers;

import android.database.Cursor;
import android.provider.MediaStore;

import androidx.annotation.NonNull;

import com.plumillonforge.ewa.domain.entities.MusicEntity;

public class MusicEntityMapper implements Mapper<Cursor, MusicEntity> {
    @Override
    public MusicEntity map(@NonNull Cursor source) {
        int idIndex = source.getColumnIndex(MediaStore.Audio.Media._ID);
        int titleIndex = source.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int artistIndex = source.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int durationIndex = source.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int pathIndex = source.getColumnIndex(MediaStore.Audio.Media.DATA);
        int nameIndex = source.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);

        return new MusicEntity(
                source.getString(idIndex),
                source.getString(titleIndex),
                source.getString(artistIndex),
                source.getLong(durationIndex),
                source.getString(pathIndex),
                source.getString(nameIndex)
        );
    }
}
