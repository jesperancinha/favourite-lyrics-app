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
        final LyricsDto mockLyricsDto = mock(LyricsDto.class);

        lyricsServicePort.addLyrics(mockLyricsDto);

        verify(lyricsPersistencePort, only()).addLyrics(mockLyricsDto);
    }

    @Test
    public void givenLyrics_whenRemove_thenRemovePortCalled() {
        final LyricsDto mockLyricsDto = mock(LyricsDto.class);

        lyricsServicePort.removeLyrics(mockLyricsDto);

        verify(lyricsPersistencePort, only()).removeLyrics(mockLyricsDto);
    }

    @Test
    public void givenLyrics_whenUpdate_thenUpdateLyricsPortCalled() {
        final LyricsDto mockLyricsDto = mock(LyricsDto.class);

        lyricsServicePort.updateLyrics(mockLyricsDto);

        verify(lyricsPersistencePort, only()).updateLyrics(mockLyricsDto);
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
        final LyricsDto mockLyricsDto = mock(LyricsDto.class);
        when(lyricsPersistencePort.getLyricsById(testLyricsId)).thenReturn(mockLyricsDto);

        final LyricsDto lyricsDto = lyricsServicePort.getLyricsById(testLyricsId);

        assertThat(lyricsDto).isSameAs(mockLyricsDto);
        verify(lyricsPersistencePort, only()).getLyricsById(testLyricsId);
    }

}