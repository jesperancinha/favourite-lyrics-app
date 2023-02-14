package org.jesperancinha.lyrics.rest

import org.jesperancinha.lyrics.core.service.LyricsService
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Slf4j
@RestController
class LyricsControllerImpl(private val lyricsService: LyricsService) : LyricsController {
    private val random = Random()
    override fun addLyrics(lyricsDto: LyricsDto?): ResponseEntity<Void> {
        lyricsService.addLyrics(lyricsDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun removeLyrics(lyricsDto: LyricsDto?): ResponseEntity<String> {
        lyricsService.removeLyrics(lyricsDto)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun updateLyrics(lyricsDto: LyricsDto?): ResponseEntity<String> {
        lyricsService.updateLyrics(lyricsDto)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun getLyricsById(lyricsId: UUID?): ResponseEntity<LyricsDto?> {
        return try {
            ResponseEntity(lyricsService.getLyricsById(lyricsId), HttpStatus.OK)
        } catch (ex: LyricsNotFoundException) {
            LyricsControllerImpl.log.error("Error!", ex)
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    override fun lyrics(): ResponseEntity<List<LyricsDto>> {
        return ResponseEntity(lyricsService.allLyrics, HttpStatus.OK)
    }

    override fun complete(): ResponseEntity<List<LyricsFullDto>> {
        return ResponseEntity(lyricsService.allFullLyrics, HttpStatus.OK)
    }

    override val randomLyric: ResponseEntity<LyricsDto?>
        get() {
            val allLyrics: Any? = lyricsService.allLyrics
            val size: Any = allLyrics.size()
            return ResponseEntity<Any?>(allLyrics.get(random.nextInt(size)), HttpStatus.OK)
        }
}