package com.plumillonforge.ewa.domain.mappers;

import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.presentation.models.MusicModel;

/**
 * Created by Flavien Norindr
 */
public class MusicModelMapper implements Mapper<MusicEntity, MusicModel> {
    @Override
    public MusicModel map(MusicEntity source) {
        return (source != null ?
                new MusicModel(
                        source.title,
                        source.artist,
                        source.duration,
                        source.path,
                        source.name
                ) : null);
    }
}
