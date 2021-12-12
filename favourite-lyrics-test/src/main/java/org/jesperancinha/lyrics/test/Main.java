package org.jesperancinha.lyrics.test;

import lombok.val;
import org.jesperancinha.lyrics.core.service.LyricsService;
import org.jesperancinha.lyrics.core.service.LyricsServiceImpl;
import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * This is just a test application. The goal is not to use test frameworks. The goal is just to show how easy it is to implement an adapter that is specifically designed to test nothing else but the core of the application.
 * <p>
 * Please run this with -ea JVM command.
 */
public class Main {

    public static void main(String[] args) {
        val lyricsService = new LyricsServiceImpl(createMockLyricsPersistencePort());
        val lyricsDto = LyricsDto.builder()
                .participatingArtist("Aretha Franklin")
                .lyrics("find out what it means to me")
                .build();
        lyricsService.addLyrics(lyricsDto);
        lyricsService.updateLyrics(lyricsDto);
        lyricsService.removeLyrics(lyricsDto);
        val allLyricsDtos = lyricsService.getAllLyrics();
        val id = UUID.randomUUID();
        final LyricsDto lyricsDtoById = lyricsService.getLyricsById(id);
        assert allLyricsDtos.size() == 1;
        final LyricsDto lyricsDto1 = allLyricsDtos.get(0);
        assert lyricsDto1
                .participatingArtist()
                .equals("Gloria Gaynor");
        assert lyricsDto1
                .lyrics()
                .equals("First I was afraid, I was petrified");
        assert lyricsDtoById.participatingArtist()
                .equals("Alesha Dixon");
        assert lyricsDtoById.lyrics()
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
                        .participatingArtist("Gloria Gaynor")
                        .lyrics("First I was afraid, I was petrified")
                        .build();
                return Collections.singletonList(lyricsDto);
            }

            @Override
            public LyricsDto getLyricsById(UUID lyricsId) {
                return LyricsDto.builder()
                        .participatingArtist("Alesha Dixon")
                        .lyrics("Does he wash up? Never wash up")
                        .build();
            }
        };
    }
}
