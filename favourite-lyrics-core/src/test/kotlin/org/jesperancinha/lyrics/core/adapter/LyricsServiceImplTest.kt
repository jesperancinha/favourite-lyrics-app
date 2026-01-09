package org.jesperancinha.lyrics.core.adapter

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.jesperancinha.lyrics.core.service.LyricsServiceImpl
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class LyricsServiceImplTest {
    @InjectMockKs
    lateinit var lyricsServicePort: LyricsServiceImpl

    @MockK(relaxed = true)
    lateinit var lyricsPersistencePort: LyricsPersistencePort

    @MockK
    lateinit var mockLyricsDtoList: List<LyricsDto>

    @Test
    fun givenLyrics_whenAdd_thenAddPortCalled() {
        val testLyricsDto = LyricsDto()
        lyricsServicePort.addLyrics(testLyricsDto)
        verify(exactly = 1) { lyricsPersistencePort.addLyrics(testLyricsDto) }
        confirmVerified(lyricsPersistencePort)
    }

    @Test
    fun givenLyrics_whenRemove_thenRemovePortCalled() {
        val testLyricsDto = LyricsDto()
        lyricsServicePort.removeLyrics(testLyricsDto)
        verify(exactly = 1) { lyricsPersistencePort.removeLyrics(testLyricsDto) }
        confirmVerified(lyricsPersistencePort)
    }

    @Test
    fun givenLyrics_whenUpdate_thenUpdateLyricsPortCalled() {
        val testLyricsDto = LyricsDto()
        lyricsServicePort.updateLyrics(testLyricsDto)
        verify(exactly = 1) { lyricsPersistencePort.updateLyrics(testLyricsDto) }
        confirmVerified(lyricsPersistencePort)
    }

    @Test
    fun givenCallToAllLyrics_whenNothingSpecified_thenGetAllLyricssPortCalled() {
        every { lyricsPersistencePort.getAllLyrics() } returns mockLyricsDtoList
        val allLyricsDtos = lyricsServicePort.getAllLyrics()
        Assertions.assertThat(allLyricsDtos).isSameAs(mockLyricsDtoList)
        verify(exactly = 1) { lyricsPersistencePort.getAllLyrics() }
        confirmVerified(lyricsPersistencePort)
    }

    @Test
    fun givenLyricsId_whenGetLyricssById_thenGetLyricsByIdPortCalled() {
        val testLyricsId = UUID.randomUUID()
        val testLyricsDto = LyricsDto()
        every { lyricsPersistencePort.getLyricsById(testLyricsId) } returns testLyricsDto
        val lyricsDto = lyricsServicePort.getLyricsById(testLyricsId)
        Assertions.assertThat(lyricsDto).isSameAs(testLyricsDto)
        verify(exactly = 1) { lyricsPersistencePort.getLyricsById(testLyricsId) }
        confirmVerified(lyricsPersistencePort)
    }
}