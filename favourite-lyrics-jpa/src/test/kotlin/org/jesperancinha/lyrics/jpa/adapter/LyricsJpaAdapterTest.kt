package org.jesperancinha.lyrics.jpa.adapter

import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import org.jesperancinha.lyrics.jpa.model.LyricsEntity
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest(classes = [LyricsJpaAdapter::class, LyricsPersistencePort::class])
class LyricsJpaAdapterTest @Autowired constructor(
    val lyricsPersistencePort: LyricsPersistencePort
) {
    @MockkBean(relaxed = true)
    lateinit var lyricsRepository: LyricsRepository

    @Test
    fun `given lyrics when add lyrics then entity is ported to repository`() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        every { lyricsRepository.save(any()) } answers { firstArg() }

        lyricsPersistencePort.addLyrics(testLyricsDto)
        val savedEntitySlot = slot<LyricsEntity>()
        verify(exactly = 1) { lyricsRepository.save(capture(savedEntitySlot)) }
        confirmVerified(lyricsRepository)
        val lyricsEntity = savedEntitySlot.captured
        assertThat(lyricsEntity.participatingArtist).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsEntity.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun `given lyrics when remove lyrics then entity removal is ported to repository`() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        lyricsPersistencePort.removeLyrics(testLyricsDto)
        verify(exactly = 1) {
            lyricsRepository.deleteAllByParticipatingArtist(requireNotNull(testLyricsDto.participatingArtist))
        }
        confirmVerified(lyricsRepository)
    }

    @Test
    fun `given call to all lyrics when no params then findAll is ported to repository`() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val testListLyricss: List<LyricsEntity?> = listOf(testLyrics)
        every { lyricsRepository.findAll() } returns testListLyricss
        val allLyricsDtos = lyricsPersistencePort.getAllLyrics()
        verify(exactly = 1) { lyricsRepository.findAll() }
        confirmVerified(lyricsRepository)
        assertThat(allLyricsDtos).hasSize(1)
        val lyricsDto = allLyricsDtos[0]
        assertThat(lyricsDto.participatingArtist).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsDto.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun `given artist id when calling get lyrics by id then find by id in repository`() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val testId = UUID.randomUUID()
        every { lyricsRepository.findById(testId) } returns Optional.of(testLyrics)
        val lyricsDtoById = lyricsPersistencePort.getLyricsById(testId)
        verify(exactly = 1) { lyricsRepository.findById(testId) }
        confirmVerified(lyricsRepository)
        assertThat(lyricsDtoById.participatingArtist).isEqualTo(TEST_AUTHOR)
        assertThat(lyricsDtoById.lyrics).isEqualTo(TEST_LYRICS)
    }

    @Test
    fun `given unexisting artist id when calling get lyrics by id then find by id in repository fails`() {
        val testId = UUID.randomUUID()
        every { lyricsRepository.findById(testId) } returns Optional.empty()
        org.junit.jupiter.api.Assertions.assertThrows(LyricsNotFoundException::class.java) {
            lyricsPersistencePort.getLyricsById(
                testId
            )
        }
    }

    @Test
    fun `given an existing participating artist when update lyrics then update lyrics`() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS_2
        )

        every { lyricsRepository.save(any()) } answers { testLyrics }
        every { lyricsRepository.findByParticipatingArtist(TEST_AUTHOR) } returns testLyrics
        lyricsPersistencePort.updateLyrics(testLyricsDto)
        verify(exactly = 1) { lyricsRepository.findByParticipatingArtist(TEST_AUTHOR) }
        val savedEntitySlot = slot<LyricsEntity>()
        verify(exactly = 1) { lyricsRepository.save(capture(savedEntitySlot)) }
        val lyricsEntity = savedEntitySlot.captured
        assertThat(lyricsEntity).isNotNull
//        assertThat(lyricsEntity.participatingArtist).isEqualTo(TEST_AUTHOR)
//        assertThat(lyricsEntity.lyrics).isEqualTo(TEST_LYRICS_2)
    }

    @Test
    fun `given an existing lyrics when update lyrics then update participating artist`() {
        val testLyrics = LyricsEntity(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS_3
        )

        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR_2,
            lyrics = TEST_LYRICS_3
        )

        every { lyricsRepository.save(any()) } answers { firstArg() }
        every { lyricsRepository.findByParticipatingArtist(TEST_AUTHOR_2) } returns LyricsEntity(
            participatingArtist = TEST_AUTHOR_2,
            lyrics = "dummy"
        )
        every { lyricsRepository.findByLyrics(TEST_LYRICS_3) } returns testLyrics
        lyricsPersistencePort.updateLyrics(testLyricsDto)
        verify(exactly = 1) { lyricsRepository.findByParticipatingArtist(TEST_AUTHOR_2) }
        verify(exactly = 1) { lyricsRepository.findByLyrics(TEST_LYRICS_3) }
        val savedEntitySlot = slot<LyricsEntity>()
        verify(exactly = 1) { lyricsRepository.save(capture(savedEntitySlot)) }
        confirmVerified(lyricsRepository)
        val lyricsEntity = savedEntitySlot.captured
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