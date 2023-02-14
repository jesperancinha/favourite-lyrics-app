package org.jesperancinha.lyrics.core.service

import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class LyricsServiceImpl(private val lyricsPersistencePort: LyricsPersistencePort) : LyricsService {
    override fun addLyrics(lyricsDto: LyricsDto) = lyricsPersistencePort.addLyrics(lyricsDto)
    @Transactional
    override fun removeLyrics(lyricsDto: LyricsDto) = lyricsPersistencePort.removeLyrics(lyricsDto)
    override fun updateLyrics(lyricsDto: LyricsDto) = lyricsPersistencePort.updateLyrics(lyricsDto)
    override fun getAllLyrics(): List<LyricsDto> = lyricsPersistencePort.getAllLyrics()
    override fun getAllFullLyrics(): List<LyricsFullDto> = lyricsPersistencePort.getAllLFullLyrics()
    override fun getLyricsById(lyricsId: UUID): LyricsDto = lyricsPersistencePort.getLyricsById(lyricsId)
}