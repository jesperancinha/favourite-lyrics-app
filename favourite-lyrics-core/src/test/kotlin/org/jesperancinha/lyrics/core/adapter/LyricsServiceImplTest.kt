package org.jesperancinha.lyrics.core.adapter

import org.assertj.core.api.Assertions
import org.jesperancinha.lyrics.core.service.LyricsServiceImpl
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class LyricsServiceImplTest {
    @InjectMocks
    lateinit var lyricsServicePort: LyricsServiceImpl

    @Mock
    lateinit var lyricsPersistencePort: LyricsPersistencePort

    @Mock
    lateinit var mockLyricsDtoList: List<LyricsDto>

    @Test
    fun givenLyrics_whenAdd_thenAddPortCalled() {
        val testLyricsDto = LyricsDto()
        lyricsServicePort.addLyrics(testLyricsDto)
        Mockito.verify(lyricsPersistencePort, Mockito.only()).addLyrics(testLyricsDto)
    }

    @Test
    fun givenLyrics_whenRemove_thenRemovePortCalled() {
        val testLyricsDto = LyricsDto()
        lyricsServicePort.removeLyrics(testLyricsDto)
        Mockito.verify(lyricsPersistencePort, Mockito.only()).removeLyrics(testLyricsDto)
    }

    @Test
    fun givenLyrics_whenUpdate_thenUpdateLyricsPortCalled() {
        val testLyricsDto = LyricsDto()
        lyricsServicePort.updateLyrics(testLyricsDto)
        Mockito.verify(lyricsPersistencePort, Mockito.only()).updateLyrics(testLyricsDto)
    }

    @Test
    fun givenCallToAllLyrics_whenNothingSpecified_thenGetAllLyricssPortCalled() {
        Mockito.`when`(lyricsPersistencePort.getAllLyrics()).thenReturn(mockLyricsDtoList)
        val allLyricsDtos = lyricsServicePort.getAllLyrics()
        Assertions.assertThat(allLyricsDtos).isSameAs(mockLyricsDtoList)
        Mockito.verify(lyricsPersistencePort, Mockito.only()).getAllLyrics()
    }

    @Test
    fun givenLyricsId_whenGetLyricssById_thenGetLyricsByIdPortCalled() {
        val testLyricsId = UUID.randomUUID()
        val testLyricsDto = LyricsDto()
        Mockito.`when`(lyricsPersistencePort.getLyricsById(testLyricsId)).thenReturn(testLyricsDto)
        val lyricsDto = lyricsServicePort.getLyricsById(testLyricsId)
        Assertions.assertThat(lyricsDto).isSameAs(testLyricsDto)
        Mockito.verify(lyricsPersistencePort, Mockito.only()).getLyricsById(testLyricsId)
    }
}