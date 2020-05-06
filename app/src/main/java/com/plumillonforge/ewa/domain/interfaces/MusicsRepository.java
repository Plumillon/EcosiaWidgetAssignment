package com.plumillonforge.ewa.domain.interfaces;

import com.plumillonforge.ewa.domain.entities.MusicEntity;

import java.util.List;

public interface MusicsRepository {
    List<MusicEntity> getMusics();
}
