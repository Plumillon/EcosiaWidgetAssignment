package com.plumillonforge.ewa.data.repositories;

import android.database.Cursor;

import com.plumillonforge.ewa.data.datasources.MediaStoreMusicsDataSource;
import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.domain.interfaces.MusicsRepository;
import com.plumillonforge.ewa.domain.mappers.Mapper;

import java.util.List;

/**
 * Created by Flavien Norindr
 */
public class MusicsRepositoryImpl implements MusicsRepository {
    private MediaStoreMusicsDataSource dataSource;
    private Mapper<Cursor, List<MusicEntity>> mapper;

    public MusicsRepositoryImpl(MediaStoreMusicsDataSource dataSource, Mapper<Cursor, List<MusicEntity>> mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    @Override
    public List<MusicEntity> getMusics() {
        return mapper.map(dataSource.getMusics());
    }
}
