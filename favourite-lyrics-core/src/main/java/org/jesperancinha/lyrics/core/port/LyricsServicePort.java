package org.jesperancinha.lyrics.core.port;

import org.jesperancinha.lyrics.domain.data.LyricsDto;

import java.util.List;

public interface LyricsServicePort {

    void addLyrics(LyricsDto lyricsDto);

    void removeLyrics(LyricsDto lyricsDto);

    void updateLyrics(LyricsDto lyricsDto);

    List<LyricsDto> getAllLyrics();

    LyricsDto getLyricsById(Long lyricsId);

}
