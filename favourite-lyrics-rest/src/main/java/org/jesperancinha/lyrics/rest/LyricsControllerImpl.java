package org.jesperancinha.lyrics.rest;

import org.jesperancinha.lyrics.core.port.LyricsServicePort;
import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class LyricsControllerImpl implements LyricsController {

    private final LyricsServicePort lyricsServicePort;

    public LyricsControllerImpl(LyricsServicePort lyricsServicePort) {
        this.lyricsServicePort = lyricsServicePort;
    }

    @Override
    public ResponseEntity<Void> addLyrics(LyricsDto lyricsDto) {
        lyricsServicePort.addLyrics(lyricsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> removeLyrics(LyricsDto lyricsDto) {
        lyricsServicePort.removeLyrics(lyricsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateLyrics(LyricsDto lyricsDto) {
        lyricsServicePort.updateLyrics(lyricsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LyricsDto> getLyricsById(Long lyricsId) {
        try {
            return new ResponseEntity<>(lyricsServicePort.getLyricsById(lyricsId), HttpStatus.OK);
        } catch (LyricsNotFoundException ex) {
            log.error("Error!", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<LyricsDto>> getLyrics() {
        return new ResponseEntity<>(lyricsServicePort.getAllLyrics(), HttpStatus.OK);
    }
}
