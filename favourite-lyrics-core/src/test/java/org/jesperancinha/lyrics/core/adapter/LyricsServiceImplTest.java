package org.jesperancinha.lyrics.core.adapter;

import org.jesperancinha.lyrics.core.service.LyricsServiceImpl;
import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LyricsServiceImplTest {

    @InjectMocks
    private LyricsServiceImpl lyricsServicePort;

    @Mock
    private LyricsPersistencePort lyricsPersistencePort;

    @Mock
    private List<LyricsDto> mockLyricsDtoList;

    @Test
    public void givenLyrics_whenAdd_thenAddPortCalled() {
        final var testLyricsDto = LyricsDto.builder().build();

        lyricsServicePort.addLyrics(testLyricsDto);

        verify(lyricsPersistencePort, only()).addLyrics(testLyricsDto);
    }

    @Test
    public void givenLyrics_whenRemove_thenRemovePortCalled() {
        final var testLyricsDto = LyricsDto.builder().build();

        lyricsServicePort.removeLyrics(testLyricsDto);

        verify(lyricsPersistencePort, only()).removeLyrics(testLyricsDto);
    }

    @Test
    public void givenLyrics_whenUpdate_thenUpdateLyricsPortCalled() {
        final var testLyricsDto = LyricsDto.builder().build();

        lyricsServicePort.updateLyrics(testLyricsDto);

        verify(lyricsPersistencePort, only()).updateLyrics(testLyricsDto);
    }

    @Test
    public void givenCallToAllLyrics_whenNothingSpecified_thenGetAllLyricssPortCalled() {
        when(lyricsPersistencePort.getAllLyrics()).thenReturn(mockLyricsDtoList);

        final List<LyricsDto> allLyricsDtos = lyricsServicePort.getAllLyrics();

        assertThat(allLyricsDtos).isSameAs(mockLyricsDtoList);
        verify(lyricsPersistencePort, only()).getAllLyrics();
    }

    @Test
    public void givenLyricsId_whenGetLyricssById_thenGetLyricsByIdPortCalled() {
        var testLyricsId = UUID.randomUUID();
        final var testLyricsDto = LyricsDto.builder().build();
        when(lyricsPersistencePort.getLyricsById(testLyricsId)).thenReturn(testLyricsDto);

        final var lyricsDto = lyricsServicePort.getLyricsById(testLyricsId);

        assertThat(lyricsDto).isSameAs(testLyricsDto);
        verify(lyricsPersistencePort, only()).getLyricsById(testLyricsId);
    }

}