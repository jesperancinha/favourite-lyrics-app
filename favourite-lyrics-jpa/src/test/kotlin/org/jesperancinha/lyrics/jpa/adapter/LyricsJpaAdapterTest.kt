package org.jesperancinha.lyrics.jpa.adapter

import org.assertj.core.api.Assertions.assertThat
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
    lateinit var lyricsPersistencePort: LyricsPersistencePort

    @MockBean
    lateinit var mockLyricsRepository: LyricsRepository

    @Captor
    lateinit var lyricsEntityArgumentCaptor: ArgumentCaptor<LyricsEntity>

    @Test
    fun givenLyrics_whenAddLyrics_thenEntityIsPortedToRepository() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        lyricsPersistencePort.addLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.only()).save(
            lyricsEntityArgumentCaptor.capture()
        )
        val lyricsEntity = lyricsEntityArgumentCaptor.value
        assertThat(lyricsEntity.participatingArtist).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsEntity.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun givenLyrics_whenRemoveLyrics_thenEntityRemovalIsPortedToRepository() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        lyricsPersistencePort.removeLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.only())
            .deleteAllByParticipatingArtist(requireNotNull(testLyricsDto.participatingArtist))
    }

    @Test
    fun givenCallToAllLyricss_whenNoParams_thenFindAllIsPortedToRepository() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val testListLyricss: List<LyricsEntity?> = listOf(testLyrics)
        Mockito.`when`(mockLyricsRepository.findAll()).thenReturn(testListLyricss)
        val allLyricsDtos = lyricsPersistencePort.getAllLyrics()
        Mockito.verify(mockLyricsRepository, Mockito.only()).findAll()
        assertThat(allLyricsDtos).hasSize(1)
        val lyricsDto = allLyricsDtos[0]
        assertThat(lyricsDto.participatingArtist).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsDto.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun givenArtisId_whenCallingGetLyricsById_thenFindByIdToRepository() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val testId = UUID.randomUUID()
        Mockito.`when`(mockLyricsRepository.findById(testId)).thenReturn(Optional.of(testLyrics))
        val lyricsDtoById = lyricsPersistencePort.getLyricsById(testId)
        Mockito.verify(mockLyricsRepository, Mockito.only()).findById(testId)
        assertThat(lyricsDtoById.participatingArtist).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsDtoById.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun givenUnexistingArtisId_whenCallingGetLyricsById_thenFindByIdToRepositoryFails() {
        val testId = UUID.randomUUID()
        Mockito.`when`(mockLyricsRepository.findById(testId)).thenReturn(Optional.empty())
        org.junit.jupiter.api.Assertions.assertThrows(LyricsNotFoundException::class.java) {
            lyricsPersistencePort.getLyricsById(
                testId
            )
        }
    }

    @Test
    fun givenAnExistingParticipatingArtist_whenUpdateLyrics_thenUpdateLyrics() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val testLyricsDto: LyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS_2
        )

        Mockito.`when`(mockLyricsRepository.findByParticipatingArtist(TEST_AUTHOR)).thenReturn(testLyrics)
        lyricsPersistencePort.updateLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).findByParticipatingArtist(TEST_AUTHOR)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).save(
            lyricsEntityArgumentCaptor.capture()
        )
        Mockito.verifyNoMoreInteractions(mockLyricsRepository)
        val lyricsEntity = lyricsEntityArgumentCaptor.value
        assertThat(lyricsEntity).isNotNull
        assertThat(lyricsEntity.participatingArtist).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsEntity.lyrics).isEqualTo(TEST_LYRICS_2)
    }

    @Test
    fun givenAnExistingLyrics_whenUpdateLyrics_thenUpdateParticipatingArtist() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS_3
        )

        val testLyricsDto: LyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR_2,
            lyrics = TEST_LYRICS_3
        )

        Mockito.`when`(mockLyricsRepository.findByLyrics(TEST_LYRICS_3)).thenReturn(testLyrics)
        lyricsPersistencePort.updateLyrics(testLyricsDto)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).findByParticipatingArtist(TEST_AUTHOR_2)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).findByLyrics(TEST_LYRICS_3)
        Mockito.verify(mockLyricsRepository, Mockito.times(1)).save(
            lyricsEntityArgumentCaptor.capture()
        )
        Mockito.verifyNoMoreInteractions(mockLyricsRepository)
        val lyricsEntity = lyricsEntityArgumentCaptor.value
        assertThat(lyricsEntity).isNotNull
        assertThat(lyricsEntity.participatingArtist).isEqualTo(TEST_AUTHOR_2)
        assertThat(lyricsEntity.lyrics).isEqualTo(TEST_LYRICS_3)
    }

    companion object {
        private const val TEST_AUTHOR = "Beyonce"
        private const val TEST_AUTHOR_2 = "Lady Gaga"
        private const val TEST_LYRICS = "Sweet dreams or a beautiful nightmare"
        private const val TEST_LYRICS_2 = "Put your hands up"
        private const val TEST_LYRICS_3 = "videophone"
    }
}