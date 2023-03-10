package org.jesperancinha.lyrics.jpa.repository

import org.jesperancinha.lyrics.jpa.model.LyricsEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LyricsRepository : JpaRepository<LyricsEntity, UUID?> {
    fun deleteAllByParticipatingArtist(name: String)
    fun findByParticipatingArtist(name: String): LyricsEntity
    fun findByLyrics(lyrics: String): LyricsEntity
}