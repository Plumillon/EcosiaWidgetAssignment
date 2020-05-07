package com.plumillonforge.ewa.utils;

import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.presentation.models.MusicModel;

import java.util.ArrayList;
import java.util.List;

public class DomainTestUtil {
    public static List<MusicEntity> generateMusicEntityList() {
        List<MusicEntity> musics = new ArrayList<>();

        for (int i = 0; i < 101; i++) {
            musics.add(
                    new MusicEntity(
                            "id" + i,
                            "Title " + i,
                            "Artist " + i,
                            (long) (1000 + i),
                            "/path/to/music/" + i,
                            "Name " + i)
            );
        }

        return musics;
    }

    public static List<MusicModel> generateMusicModelList() {
        List<MusicModel> musics = new ArrayList<>();

        for (int i = 0; i < 101; i++) {
            musics.add(
                    new MusicModel(
                            "Title " + i,
                            "Artist " + i,
                            (long) (1000 + i),
                            "/path/to/music/" + i,
                            "Name " + i)
            );
        }

        return musics;
    }
}
