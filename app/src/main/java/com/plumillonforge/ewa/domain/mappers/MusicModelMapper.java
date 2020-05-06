package com.plumillonforge.ewa.domain.mappers;

import androidx.annotation.NonNull;

import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.presentation.models.MusicModel;

public class MusicModelMapper implements Mapper<MusicEntity, MusicModel> {
    @Override
    public MusicModel map(@NonNull MusicEntity source) {
        return new MusicModel(
                source.title,
                source.artist,
                source.duration,
                source.path,
                source.name
        );
    }
}
