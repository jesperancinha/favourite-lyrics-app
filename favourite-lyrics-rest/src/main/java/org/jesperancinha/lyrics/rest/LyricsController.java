package org.jesperancinha.lyrics.rest;

import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.data.LyricsFullDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface LyricsController {

    @PostMapping("/lyrics")
    ResponseEntity<Void> addLyrics(
            @RequestBody
                    LyricsDto lyricsDto);

    @DeleteMapping("/lyrics")
    ResponseEntity<String> removeLyrics(
            @RequestBody
                    LyricsDto lyricsDto);

    @PutMapping("/lyrics")
    ResponseEntity<String> updateLyrics(
            @RequestBody
                    LyricsDto lyricsDto);

    @GetMapping("/lyrics/{lyricsId}")
    ResponseEntity<LyricsDto> getLyricsById(
            @PathVariable
                    UUID lyricsId);

    @GetMapping("/lyrics")
    ResponseEntity<List<LyricsDto>> lyrics();

    @GetMapping("/complete/lyrics")
    ResponseEntity<List<LyricsFullDto>> complete();

    @GetMapping("/lyrics/random")
    ResponseEntity<LyricsDto> getRandomLyric();

}
