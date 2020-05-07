package com.plumillonforge.ewa;

import android.database.Cursor;

import com.plumillonforge.ewa.data.datasources.DeviceMusicsDataSource;
import com.plumillonforge.ewa.data.datasources.MediaStoreMusicsDataSource;
import com.plumillonforge.ewa.data.repositories.MusicsRepositoryImpl;
import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.domain.mappers.Mapper;
import com.plumillonforge.ewa.utils.DomainTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetMusicsRepositoryTest {
    @Mock
    private MediaStoreMusicsDataSource deviceMusicsDataSource;

    @Mock
    private Cursor cursor;

    @Mock
    private Mapper<Cursor, List<MusicEntity>> mapper;

    @InjectMocks
    private MusicsRepositoryImpl musicsRepository;

    @Test
    public void testGetMusics() {
        List<MusicEntity> musics = DomainTestUtil.generateMusicEntityList();

        Mockito
                .when(deviceMusicsDataSource.getMusics())
                .thenReturn(cursor);
        Mockito
                .when(mapper.map(cursor))
                .thenReturn(musics);

        assertEquals(musics, musicsRepository.getMusics());
    }
}