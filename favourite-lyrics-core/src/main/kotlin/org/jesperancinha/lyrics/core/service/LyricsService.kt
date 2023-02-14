package org.jesperancinha.lyrics.core.service

import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import java.util.*

interface LyricsService {
    fun addLyrics(lyricsDto: LyricsDto)
    fun removeLyrics(lyricsDto: LyricsDto)
    fun updateLyrics(lyricsDto: LyricsDto)
    fun getAllLyrics(): List<LyricsDto>
    fun getAllFullLyrics(): List<LyricsFullDto>
    fun getLyricsById(lyricsId: UUID): LyricsDto
}