package com.plumillonforge.ewa;

import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.domain.interfaces.MusicsRepository;
import com.plumillonforge.ewa.domain.useCases.GetRandomMusicUseCase;
import com.plumillonforge.ewa.utils.DomainTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GetRandomMusicUseCaseTest {
    @Mock
    private MusicsRepository musicsRepository;

    @InjectMocks
    private GetRandomMusicUseCase getRandomMusicUseCase;

    @Test
    public void testGetRandomMusic() {
        List<MusicEntity> musics = DomainTestUtil.generateMusicEntityList();

        Mockito
                .when(musicsRepository.getMusics())
                .thenReturn(musics);
        MusicEntity randomMusic = getRandomMusicUseCase.execute();

        assertNotNull(randomMusic);
        assertThat("Checking if usecase pick a random music from repository list", true, is(musics.contains(randomMusic)));
    }
}