package org.jesperancinha.lyrics.core.service;

import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.data.LyricsFullDto;
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    public List<LyricsFullDto> getAllFullLyrics() {
        return lyricsPersistencePort.getAllLFullLyrics();
    }

    @Override
    public LyricsDto getLyricsById(UUID lyricsId) {
        return lyricsPersistencePort.getLyricsById(lyricsId);
    }
}
