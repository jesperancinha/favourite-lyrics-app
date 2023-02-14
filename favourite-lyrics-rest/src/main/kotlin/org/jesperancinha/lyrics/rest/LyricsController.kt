package org.jesperancinha.lyrics.rest

import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

interface LyricsController {
    @PostMapping("/lyrics")
    fun addLyrics(
        @RequestBody lyricsDto: LyricsDto?
    ): ResponseEntity<Void>

    @DeleteMapping("/lyrics")
    fun removeLyrics(
        @RequestBody lyricsDto: LyricsDto?
    ): ResponseEntity<String>

    @PutMapping("/lyrics")
    fun updateLyrics(
        @RequestBody lyricsDto: LyricsDto?
    ): ResponseEntity<String>

    @GetMapping("/lyrics/{lyricsId}")
    fun getLyricsById(
        @PathVariable lyricsId: UUID?
    ): ResponseEntity<LyricsDto?>

    @GetMapping("/lyrics")
    fun lyrics(): ResponseEntity<List<LyricsDto>>

    @GetMapping("/lyrics/complete")
    fun complete(): ResponseEntity<List<LyricsFullDto>>

    @get:GetMapping("/lyrics/random")
    val randomLyric: ResponseEntity<LyricsDto?>
}