package org.jesperancinha.lyrics.core.service;

import org.jesperancinha.lyrics.domain.data.LyricsDto;

import java.util.List;

public interface LyricsService {

    void addLyrics(LyricsDto lyricsDto);

    void removeLyrics(LyricsDto lyricsDto);

    void updateLyrics(LyricsDto lyricsDto);

    List<LyricsDto> getAllLyrics();

    LyricsDto getLyricsById(Long lyricsId);

}



