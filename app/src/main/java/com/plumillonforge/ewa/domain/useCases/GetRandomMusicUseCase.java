package com.plumillonforge.ewa.domain.useCases;

import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.domain.interfaces.MusicsRepository;

import java.util.List;
import java.util.Random;

/**
 * Created by Flavien Norindr
 */
public class GetRandomMusicUseCase implements UseCase<MusicEntity> {
    private MusicsRepository repository;

    public GetRandomMusicUseCase(MusicsRepository repository) {
        this.repository = repository;
    }

    @Override
    public MusicEntity execute() {
        List<MusicEntity> musics = this.repository.getMusics();

        if (musics.size() > 0) {
            Random rand = new Random();
            return musics.get(rand.nextInt(musics.size()));
        }

        return null;
    }
}
