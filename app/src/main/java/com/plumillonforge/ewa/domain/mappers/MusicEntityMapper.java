package com.plumillonforge.ewa.domain.mappers;

import android.database.Cursor;
import android.provider.MediaStore;

import com.plumillonforge.ewa.domain.entities.MusicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flavien Norindr
 */
public class MusicEntityMapper implements Mapper<Cursor, List<MusicEntity>> {
    @Override
    public List<MusicEntity> map(Cursor source) {
        List<MusicEntity> musics = new ArrayList<>();

        if (source != null) {
            int idIndex = source.getColumnIndex(MediaStore.Audio.Media._ID);
            int titleIndex = source.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = source.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int durationIndex = source.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int pathIndex = source.getColumnIndex(MediaStore.Audio.Media.DATA);
            int nameIndex = source.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);

            while (source.moveToNext()) {
                musics.add(new MusicEntity(
                        source.getString(idIndex),
                        source.getString(titleIndex),
                        source.getString(artistIndex),
                        source.getLong(durationIndex),
                        source.getString(pathIndex),
                        source.getString(nameIndex)
                ));
            }
        }

        return musics;
    }
}
