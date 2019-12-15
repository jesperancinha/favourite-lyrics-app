package org.jesperancinha.lyrics.rest;

import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LyricsController {

    @PostMapping("/lyrics")
    ResponseEntity<Void> addLyrics(@RequestBody LyricsDto lyricsDto);

    @DeleteMapping("/lyrics")
    ResponseEntity<String> removeLyrics(@RequestBody LyricsDto lyricsDto);

    @PutMapping("/lyrics")
    ResponseEntity<String> updateLyrics(@RequestBody LyricsDto lyricsDto);

    @GetMapping("/lyrics/{lyricsId}")
    ResponseEntity<LyricsDto> getLyricsById(@PathVariable Long lyricsId);

    @GetMapping("/lyrics")
    ResponseEntity<List<LyricsDto>> getLyrics();

    @GetMapping("/lyrics/random")
    ResponseEntity<LyricsDto> getRandomLyric();

}
