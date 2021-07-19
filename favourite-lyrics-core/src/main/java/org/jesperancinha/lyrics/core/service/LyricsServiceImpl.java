package org.jesperancinha.lyrics.core.service;

import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LyricsServiceImpl implements LyricsService {

    private final LyricsPersistencePort lyricsPersistencePort;

    public LyricsServiceImpl(LyricsPersistencePort lyricsPersistencePort) {
        this.lyricsPersistencePort = lyricsPersistencePort;
    }

    @Override
    public void addLyrics(LyricsDto lyricsDto) {
        lyricsPersistencePort.addLyrics(lyricsDto);
    }

    @Override
    @Transactional
    public void removeLyrics(LyricsDto lyricsDto) {
        lyricsPersistencePort.removeLyrics(lyricsDto);
    }

    @Override
    public void updateLyrics(LyricsDto lyricsDto) {
        lyricsPersistencePort.updateLyrics(lyricsDto);
    }

    @Override
    public List<LyricsDto> getAllLyrics() {
        return lyricsPersistencePort.getAllLyrics();
    }

    @Override
    public LyricsDto getLyricsById(Long lyricsId) {
        return lyricsPersistencePort.getLyricsById(lyricsId);
    }





}



