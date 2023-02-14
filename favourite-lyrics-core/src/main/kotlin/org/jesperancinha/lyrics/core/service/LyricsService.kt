package org.jesperancinha.lyrics.core.service

import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import java.util.*

interface LyricsService {
    fun addLyrics(lyricsDto: LyricsDto?)
    fun removeLyrics(lyricsDto: LyricsDto?)
    fun updateLyrics(lyricsDto: LyricsDto?)
    @JvmField
    val allLyrics: List<LyricsDto?>?
    @JvmField
    val allFullLyrics: List<LyricsFullDto?>?
    fun getLyricsById(lyricsId: UUID?): LyricsDto?
}