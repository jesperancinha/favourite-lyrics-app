package org.jesperancinha.lyrics.jpa.adapter

import org.assertj.core.api.Assertions
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import org.jesperancinha.lyrics.jpa.model.LyricsEntity
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest(classes = [LyricsJpaAdapter::class, LyricsPersistencePort::class])
class LyricsJpaAdapterTest {
    @Autowired
    private val lyricsPersistencePort: LyricsPersistencePort? = null

    @MockBean
    private val mockLyricsRepository: LyricsRepository? = null

    @Captor
    private val lyricsEntityArgumentCaptor: ArgumentCaptor<LyricsEntity>? = null
    @Test
    fun givenLyrics_whenAddLyrics_thenEntityIsPortedToRepository() {
        val testLyricsDto: LyricsDto = LyricsDto.builder()
            .participatingArtist(TEST_AUTHOR)
            .lyrics(TEST_LYRICS)
            .build()
        lyricsPersistencePort!!.addLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.only()).save(
            lyricsEntityArgumentCaptor!!.capture()
        )
        val lyricsEntity = lyricsEntityArgumentCaptor.value
        assertThat(lyricsEntity.getParticipatingArtist()).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsEntity.getLyrics()).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun givenLyrics_whenRemoveLyrics_thenEntityRemovalIsPortedToRepository() {
        val testLyricsDto: LyricsDto = LyricsDto.builder()
            .participatingArtist(TEST_AUTHOR)
            .lyrics(TEST_LYRICS)
            .build()
        lyricsPersistencePort!!.removeLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.only())
            .deleteAllByParticipatingArtist(testLyricsDto.participatingArtist)
    }

    @Test
    fun givenCallToAllLyricss_whenNoParams_thenFindAllIsPortedToRepository() {
        val testLyrics: LyricsEntity = LyricsEntity.builder()
            .participatingArtist(TEST_AUTHOR)
            .lyrics(TEST_LYRICS)
            .build()
        val testListLyricss: List<LyricsEntity?> = listOf(testLyrics)
        Mockito.`when`(mockLyricsRepository!!.findAll()).thenReturn(testListLyricss)
        val allLyricsDtos = lyricsPersistencePort!!.allLyrics
        Mockito.verify(mockLyricsRepository, Mockito.only()).findAll()
        Assertions.assertThat(allLyricsDtos).hasSize(1)
        val lyricsDto = allLyricsDtos!![0]
        Assertions.assertThat(lyricsDto!!.participatingArtist).isEqualTo(TEST_AUTHOR)
        Assertions.assertThat(lyricsDto.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun givenArtisId_whenCallingGetLyricsById_thenFindByIdToRepository() {
        val testLyrics: LyricsEntity = LyricsEntity.builder()
            .participatingArtist(TEST_AUTHOR)
            .lyrics(TEST_LYRICS)
            .build()
        val testId = UUID.randomUUID()
        Mockito.`when`(mockLyricsRepository!!.findById(testId)).thenReturn(Optional.of(testLyrics))
        val lyricsDtoById = lyricsPersistencePort!!.getLyricsById(testId)
        Mockito.verify(mockLyricsRepository, Mockito.only()).findById(testId)
        Assertions.assertThat(lyricsDtoById!!.participatingArtist).isEqualTo(TEST_AUTHOR)
        Assertions.assertThat(lyricsDtoById.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun givenUnexistingArtisId_whenCallingGetLyricsById_thenFindByIdToRepositoryFails() {
        val testId = UUID.randomUUID()
        Mockito.`when`(mockLyricsRepository!!.findById(testId)).thenReturn(Optional.empty())
        org.junit.jupiter.api.Assertions.assertThrows(LyricsNotFoundException::class.java) {
            lyricsPersistencePort!!.getLyricsById(
                testId
            )
        }
    }

    @Test
    fun givenAnExistingParticipatingArtist_whenUpdateLyrics_thenUpdateLyrics() {
        val testLyrics: LyricsEntity = LyricsEntity.builder()
            .participatingArtist(TEST_AUTHOR)
            .lyrics(TEST_LYRICS)
            .build()
        val testLyricsDto: LyricsDto = LyricsDto.builder()
            .participatingArtist(TEST_AUTHOR)
            .lyrics(TEST_LYRICS_2)
            .build()
        Mockito.`when`(mockLyricsRepository!!.findByParticipatingArtist(TEST_AUTHOR)).thenReturn(testLyrics)
        lyricsPersistencePort!!.updateLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).findByParticipatingArtist(TEST_AUTHOR)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).save(
            lyricsEntityArgumentCaptor!!.capture()
        )
        Mockito.verifyNoMoreInteractions(mockLyricsRepository)
        val lyricsEntity = lyricsEntityArgumentCaptor.value
        Assertions.assertThat(lyricsEntity).isNotNull
        assertThat(lyricsEntity.getParticipatingArtist()).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsEntity.getLyrics()).isEqualTo(TEST_LYRICS_2)
    }

    @Test
    fun givenAnExistingLyrics_whenUpdateLyrics_thenUpdateParticipatingArtist() {
        val testLyrics: LyricsEntity = LyricsEntity.builder()
            .participatingArtist(TEST_AUTHOR)
            .lyrics(TEST_LYRICS_3)
            .build()
        val testLyricsDto: LyricsDto = LyricsDto.builder()
            .participatingArtist(TEST_AUTHOR_2)
            .lyrics(TEST_LYRICS_3)
            .build()
        Mockito.`when`(mockLyricsRepository!!.findByLyrics(TEST_LYRICS_3)).thenReturn(testLyrics)
        lyricsPersistencePort!!.updateLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).findByParticipatingArtist(TEST_AUTHOR_2)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).findByLyrics(TEST_LYRICS_3)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).save(
            lyricsEntityArgumentCaptor!!.capture()
        )
        Mockito.verifyNoMoreInteractions(mockLyricsRepository)
        val lyricsEntity = lyricsEntityArgumentCaptor.value
        Assertions.assertThat(lyricsEntity).isNotNull
        assertThat(lyricsEntity.getParticipatingArtist()).isEqualTo(TEST_AUTHOR_2)
        assertThat(lyricsEntity.getLyrics()).isEqualTo(TEST_LYRICS_3)
    }

    companion object {
        private const val TEST_AUTHOR = "Beyonce"
        private const val TEST_AUTHOR_2 = "Lady Gaga"
        private const val TEST_LYRICS = "Sweet dreams or a beautiful nightmare"
        private const val TEST_LYRICS_2 = "Put your hands up"
        private const val TEST_LYRICS_3 = "videophone"
    }
}