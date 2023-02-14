package org.jesperancinha.lyrics.rest

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.lyrics.core.service.LyricsService
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(LyricsControllerImpl::class)
@ContextConfiguration(classes = [LyricsController::class, LyricsControllerImpl::class])
class LyricsControllerImplTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var lyricsService: LyricsService
    private val objectMapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun givenLyrics_whenAddLyrics_thenEntityIsPortedToService() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        mvc.perform(
            MockMvcRequestBuilders.post("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
        Mockito.verify(lyricsService, Mockito.only()).addLyrics(testLyricsDto)
    }

    @Test
    @Throws(Exception::class)
    fun givenLyrics_whenUpdateLyrics_thenEntityUpdateIsPortedToService() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        mvc.perform(
            MockMvcRequestBuilders.put("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        Mockito.verify(lyricsService, Mockito.only()).updateLyrics(testLyricsDto)
    }

    @Test
    @Throws(Exception::class)
    fun givenLyrics_whenRemoveLyrics_thenEntityRemovalIsPortedToService() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        mvc.perform(
            MockMvcRequestBuilders.delete("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        Mockito.verify(lyricsService, Mockito.only()).removeLyrics(testLyricsDto)
    }

    @Test
    @Throws(Exception::class)
    fun givenCallToAllLyricss_whenNoParams_thenFindAllIsPortedToService() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        Mockito.`when`<Any?>(lyricsService.getAllLyrics()).thenReturn(listOf(testLyricsDto))
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].participatingArtist")
                    .exists()
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].participatingArtist")
                    .value(TEST_AUTHOR)
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].lyrics")
                    .exists()
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].lyrics")
                    .value(TEST_LYRICS)
            )
        Mockito.verify(lyricsService, Mockito.only()).getAllLyrics()
    }

    @Test
    @Throws(Exception::class)
    fun givenArtisId_whenCallingGetLyricsById_thenFindByIdToService() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val id = UUID.randomUUID()
        Mockito.`when`(lyricsService.getLyricsById(id)).thenReturn(testLyricsDto)
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/$id")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.participatingArtist")
                    .exists()
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.participatingArtist")
                    .value(TEST_AUTHOR)
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.lyrics")
                    .exists()
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.lyrics")
                    .value(TEST_LYRICS)
            )
        Mockito.verify(lyricsService, Mockito.only()).getLyricsById(id)
    }

    @Test
    @Throws(Exception::class)
    fun givenUnexistingArtisId_whenCallingGetLyricsById_thenFindByIdToServiceFails() {
        val id = UUID.randomUUID()
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/$id")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.participatingArtist")
                    .doesNotExist()
            )
        Mockito.verify(lyricsService, Mockito.only()).getLyricsById(id)
    }

    companion object {
        private const val TEST_AUTHOR = "Rita Ora"
        private const val TEST_LYRICS = "Be there when the sun is rising"
    }
}