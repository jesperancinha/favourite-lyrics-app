package org.jesperancinha.lyrics.domain.port;

import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.data.LyricsFullDto;

import java.util.List;
import java.util.UUID;

public interface LyricsPersistencePort {

    void addLyrics(LyricsDto lyricsDto);

    void removeLyrics(LyricsDto lyricsDto);

    void updateLyrics(LyricsDto lyricsDto);

    List<LyricsDto> getAllLyrics();

    List<LyricsFullDto> getAllLFullLyrics();

    LyricsDto getLyricsById(UUID lyricsId);
}
