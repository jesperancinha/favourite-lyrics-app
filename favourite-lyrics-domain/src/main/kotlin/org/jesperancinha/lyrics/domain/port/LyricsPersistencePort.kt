package org.jesperancinha.lyrics.domain.port

import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import java.util.*

interface LyricsPersistencePort {
    fun addLyrics(lyricsDto: LyricsDto?)
    fun removeLyrics(lyricsDto: LyricsDto?)
    fun updateLyrics(lyricsDto: LyricsDto?)
    @JvmField
    val allLyrics: List<LyricsDto?>?
    val allLFullLyrics: List<LyricsFullDto?>?
    fun getLyricsById(lyricsId: UUID?): LyricsDto?
}