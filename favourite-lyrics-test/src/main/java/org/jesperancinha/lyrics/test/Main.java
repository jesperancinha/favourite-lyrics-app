package org.jesperancinha.lyrics.test;

import org.jesperancinha.lyrics.core.adapter.LyricsServiceAdapter;
import org.jesperancinha.lyrics.core.port.LyricsServicePort;
import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort;

import java.util.Collections;
import java.util.List;

/**
 * This is just a test application. The goal is not to use test frameworks. The goal is just to show how easy it is to implement an adapter that is specifically designed to test nothing else but the core of the application.
 * <p>
 * Please run this with -ea JVM command.
 */
public class Main {

    public static void main(String[] args) {
        final LyricsServicePort lyricsServicePort = new LyricsServiceAdapter(createMockLyricsPersistencePort());
        final LyricsDto lyricsDto = LyricsDto.builder()
                .author("Aretha Franklin")
                .lyrics("find out what it means to me")
                .build();
        lyricsServicePort.addLyrics(lyricsDto);
        lyricsServicePort.updateLyrics(lyricsDto);
        lyricsServicePort.removeLyrics(lyricsDto);
        final List<LyricsDto> allLyricsDtos = lyricsServicePort.getAllLyrics();
        final LyricsDto lyricsDtoById = lyricsServicePort.getLyricsById(1L);
        assert allLyricsDtos.size() == 1;
        final LyricsDto lyricsDto1 = allLyricsDtos.get(0);
        assert lyricsDto1
                .getAuthor()
                .equals("Gloria Gaynor");
        assert lyricsDto1
                .getLyrics()
                .equals("First I was afraid, I was petrified");
        assert lyricsDtoById.getAuthor()
                .equals("Alesha Dixon");
        assert lyricsDtoById.getLyrics()
                .equals("Does he wash up? Never wash up");
    }

    private static LyricsPersistencePort createMockLyricsPersistencePort() {
        return new LyricsPersistencePort() {
            @Override
            public void addLyrics(LyricsDto lyricsDto) {

            }

            @Override
            public void removeLyrics(LyricsDto lyricsDto) {

            }

            @Override
            public void updateLyrics(LyricsDto lyricsDto) {

            }

            @Override
            public List<LyricsDto> getAllLyrics() {
                final LyricsDto lyricsDto = LyricsDto.builder()
                        .author("Gloria Gaynor")
                        .lyrics("First I was afraid, I was petrified")
                        .build();
                return Collections.singletonList(lyricsDto);
            }

            @Override
            public LyricsDto getLyricsById(Long lyricsId) {
                return LyricsDto.builder()
                        .author("Alesha Dixon")
                        .lyrics("Does he wash up? Never wash up")
                        .build();
            }
        };
    }
}
