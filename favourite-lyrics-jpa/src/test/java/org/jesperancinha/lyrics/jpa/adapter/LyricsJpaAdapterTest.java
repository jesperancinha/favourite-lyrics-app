package org.jesperancinha.lyrics.jpa.adapter;

import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException;
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort;
import org.jesperancinha.lyrics.jpa.model.LyricsEntity;
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        LyricsJpaAdapter.class,
        LyricsPersistencePort.class})
public class LyricsJpaAdapterTest {

    private static final String TEST_AUTHOR = "Beyonce";
    private static final String TEST_AUTHOR_2 = "Lady Gaga";
    private static final String TEST_LYRICS = "Sweet dreams or a beautiful nightmare";
    private static final String TEST_LYRICS_2 = "Put your hands up";
    private static final String TEST_LYRICS_3 = "videophone";

    @Autowired
    private LyricsPersistencePort lyricsPersistencePort;

    @MockBean
    private LyricsRepository mockLyricsRepository;

    @Captor
    private ArgumentCaptor<LyricsEntity> lyricsEntityArgumentCaptor;

    @Test
    void givenLyrics_whenAddLyrics_thenEntityIsPortedToRepository() {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .author(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();

        lyricsPersistencePort.addLyrics(testLyricsDto);

        verify(mockLyricsRepository, only()).save(lyricsEntityArgumentCaptor.capture());
        final LyricsEntity lyricsEntity = lyricsEntityArgumentCaptor.getValue();
        assertThat(lyricsEntity.getAuthor()).isEqualTo(TEST_AUTHOR);
        assertThat(lyricsEntity.getLyrics()).isEqualTo(TEST_LYRICS);
    }

    @Test
    void givenLyrics_whenRemoveLyrics_thenEntityRemovalIsPortedToRepository() {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .author(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();

        lyricsPersistencePort.removeLyrics(testLyricsDto);

        verify(mockLyricsRepository, only()).deleteAllByAuthor(testLyricsDto.getAuthor());
    }

    @Test
    void givenCallToAllLyricss_whenNoParams_thenFindAllIsPortedToRepository() {
        final LyricsEntity testLyrics = LyricsEntity.builder()
                .author(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();
        final List<LyricsEntity> testListLyricss = Collections.singletonList(testLyrics);
        when(mockLyricsRepository.findAll()).thenReturn(testListLyricss);

        final List<LyricsDto> allLyricsDtos = lyricsPersistencePort.getAllLyrics();

        verify(mockLyricsRepository, only()).findAll();
        assertThat(allLyricsDtos).hasSize(1);
        final LyricsDto lyricsDto = allLyricsDtos.get(0);
        assertThat(lyricsDto.getAuthor()).isEqualTo(TEST_AUTHOR);
        assertThat(lyricsDto.getLyrics()).isEqualTo(TEST_LYRICS);
    }

    @Test
    void givenArtisId_whenCallingGetLyricsById_thenFindByIdToRepository() {
        final LyricsEntity testLyrics = LyricsEntity.builder()
                .author(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();
        when(mockLyricsRepository.findById(1L)).thenReturn(Optional.of(testLyrics));

        final LyricsDto lyricsDtoById = lyricsPersistencePort.getLyricsById(1L);

        verify(mockLyricsRepository, only()).findById(1L);
        assertThat(lyricsDtoById.getAuthor()).isEqualTo(TEST_AUTHOR);
        assertThat(lyricsDtoById.getLyrics()).isEqualTo(TEST_LYRICS);
    }

    @Test
    void givenUnexistingArtisId_whenCallingGetLyricsById_thenFindByIdToRepositoryFails() {
        when(mockLyricsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(LyricsNotFoundException.class, () -> lyricsPersistencePort.getLyricsById(1L));
    }

    @Test
    void givenAnExistingAuthor_whenUpdateLyrics_thenUpdateLyrics() {
        final LyricsEntity testLyrics = LyricsEntity.builder()
                .author(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .author(TEST_AUTHOR)
                .lyrics(TEST_LYRICS_2)
                .build();
        when(mockLyricsRepository.findByAuthor(TEST_AUTHOR)).thenReturn(testLyrics);

        lyricsPersistencePort.updateLyrics(testLyricsDto);

        verify(mockLyricsRepository, times(1)).findByAuthor(TEST_AUTHOR);
        verify(mockLyricsRepository, times(1)).save(lyricsEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(mockLyricsRepository);
        final LyricsEntity lyricsEntity = lyricsEntityArgumentCaptor.getValue();
        assertThat(lyricsEntity).isNotNull();
        assertThat(lyricsEntity.getAuthor()).isEqualTo(TEST_AUTHOR);
        assertThat(lyricsEntity.getLyrics()).isEqualTo(TEST_LYRICS_2);
    }

    @Test
    void givenAnExistingLyrics_whenUpdateLyrics_thenUpdateAuthor() {
        final LyricsEntity testLyrics = LyricsEntity.builder()
                .author(TEST_AUTHOR)
                .lyrics(TEST_LYRICS_3)
                .build();
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .author(TEST_AUTHOR_2)
                .lyrics(TEST_LYRICS_3)
                .build();
        when(mockLyricsRepository.findByLyrics(TEST_LYRICS_3)).thenReturn(testLyrics);

        lyricsPersistencePort.updateLyrics(testLyricsDto);

        verify(mockLyricsRepository, times(1)).findByAuthor(TEST_AUTHOR_2);
        verify(mockLyricsRepository, times(1)).findByLyrics(TEST_LYRICS_3);
        verify(mockLyricsRepository, times(1)).save(lyricsEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(mockLyricsRepository);
        final LyricsEntity lyricsEntity = lyricsEntityArgumentCaptor.getValue();
        assertThat(lyricsEntity).isNotNull();
        assertThat(lyricsEntity.getAuthor()).isEqualTo(TEST_AUTHOR_2);
        assertThat(lyricsEntity.getLyrics()).isEqualTo(TEST_LYRICS_3);
    }
}