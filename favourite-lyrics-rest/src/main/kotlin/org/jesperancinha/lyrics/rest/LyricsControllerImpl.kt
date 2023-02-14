package org.jesperancinha.lyrics.rest

import org.jesperancinha.lyrics.core.service.LyricsService
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class LyricsControllerImpl(private val lyricsService: LyricsService) : LyricsController {
    private val random = Random()
    override fun addLyrics(lyricsDto: LyricsDto): ResponseEntity<Void> {
        lyricsService.addLyrics(lyricsDto)
        return ResponseEntity(CREATED)
    }

    override fun removeLyrics(lyricsDto: LyricsDto): ResponseEntity<String> {
        lyricsService.removeLyrics(lyricsDto)
        return ResponseEntity(OK)
    }

    override fun updateLyrics(lyricsDto: LyricsDto): ResponseEntity<String> {
        lyricsService.updateLyrics(lyricsDto)
        return ResponseEntity(OK)
    }

    override fun getLyricsById(lyricsId: UUID): ResponseEntity<LyricsDto> {
        return try {
            ResponseEntity(lyricsService.getLyricsById(lyricsId), OK)
        } catch (ex: LyricsNotFoundException) {
            logger.error("Error!", ex)
            ResponseEntity(NOT_FOUND)
        }
    }

    override fun lyrics(): ResponseEntity<List<LyricsDto>> {
        return ResponseEntity(lyricsService.getAllLyrics(), OK)
    }

    override fun complete(): ResponseEntity<List<LyricsFullDto>> = ResponseEntity(lyricsService.getAllFullLyrics(), OK)

    override fun getRandomLyric(): ResponseEntity<LyricsDto> {
        val allLyrics = lyricsService.getAllLyrics()
        return ResponseEntity<LyricsDto>(allLyrics[random.nextInt(allLyrics.size)], OK)
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(LyricsControllerImpl::class.java)
    }
}