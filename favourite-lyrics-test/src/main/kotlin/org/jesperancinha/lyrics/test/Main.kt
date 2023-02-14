package org.jesperancinha.lyrics.test

import org.jesperancinha.lyrics.core.service.LyricsServiceImpl
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import java.util.*

/**
 * This is just a test application. The goal is not to use test frameworks. The goal is just to show how easy it is to implement an adapter that is specifically designed to test nothing else but the core of the application.
 *
 *
 * Please run this with -ea JVM command.
 */
object Main {
    @JvmStatic
    fun main(args: Array<String> = emptyArray()) {
        val lyricsService = LyricsServiceImpl(createMockLyricsPersistencePort())
        val lyricsDto = LyricsDto(
            participatingArtist = "Aretha Franklin",
            lyrics = "find out what it means to me"
        )
        lyricsService.addLyrics(lyricsDto)
        lyricsService.updateLyrics(lyricsDto)
        lyricsService.removeLyrics(lyricsDto)
        val allLyricsDtos = lyricsService.getAllLyrics()
        val id = UUID.randomUUID()
        val lyricsDtoById = lyricsService.getLyricsById(id)
        assert(allLyricsDtos.size == 1)
        val lyricsDto1 = allLyricsDtos[0]
        assert(
            lyricsDto1.participatingArtist
                    == "Gloria Gaynor"
        )
        assert(
            lyricsDto1.lyrics
                    == "First I was afraid, I was petrified"
        )
        assert(
            lyricsDtoById.participatingArtist
                    == "Alesha Dixon"
        )
        assert(
            lyricsDtoById.lyrics
                    == "Does he wash up? Never wash up"
        )
    }

    private fun createMockLyricsPersistencePort(): LyricsPersistencePort {
        return object : LyricsPersistencePort {
            override fun addLyrics(lyricsDto: LyricsDto) {}
            override fun removeLyrics(lyricsDto: LyricsDto) {}
            override fun updateLyrics(lyricsDto: LyricsDto) {}
            override fun getAllLyrics(): List<LyricsDto> = listOf(
                LyricsDto(
                    participatingArtist = "Gloria Gaynor",
                    lyrics = "First I was afraid, I was petrified"
                )
            )

            override fun getAllLFullLyrics(): List<LyricsFullDto> = listOf(
                LyricsFullDto(
                    participatingArtist = "Metallica",
                    lyrics = "Sad but true"
                )
            )

            override fun getLyricsById(lyricsId: UUID): LyricsDto {
                return LyricsDto(
                    participatingArtist = "Alesha Dixon",
                    lyrics = "Does he wash up? Never wash up"
                )
            }
        }
    }
}