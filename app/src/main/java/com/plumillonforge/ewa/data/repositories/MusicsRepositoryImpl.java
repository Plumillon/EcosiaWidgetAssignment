package com.plumillonforge.ewa.data.repositories;

import android.database.Cursor;

import com.plumillonforge.ewa.data.datasources.MediaStoreMusicsDataSource;
import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.domain.interfaces.MusicsRepository;
import com.plumillonforge.ewa.domain.mappers.MusicEntityMapper;

import java.util.ArrayList;
import java.util.List;

public class MusicsRepositoryImpl implements MusicsRepository {
    private MediaStoreMusicsDataSource dataSource = new MediaStoreMusicsDataSource();
    private MusicEntityMapper mapper = new MusicEntityMapper();

    @Override
    public List<MusicEntity> getMusics() {
        Cursor musicsCursor = dataSource.getMusics();
        List<MusicEntity> musics = new ArrayList<>();

        if (musicsCursor != null) {
            while (musicsCursor.moveToNext()) {
                musics.add(mapper.map(musicsCursor));
            }
        }

        return musics;
    }
}
