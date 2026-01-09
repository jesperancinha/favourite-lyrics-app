package org.jesperancinha.lyrics.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.lyrics.core.service.LyricsService
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

@SpringBootTest
@ContextConfiguration(classes = [LyricsController::class, LyricsControllerImpl::class])
class LyricsControllerImplTest @Autowired constructor(
    val lyricsControllerImpl: LyricsControllerImpl
) {

    lateinit var mvc: MockMvc

    @MockkBean(relaxed = true)
    lateinit var lyricsService: LyricsService
    private val objectMapper = ObjectMapper()

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders
            .standaloneSetup(lyricsControllerImpl)
            .build()
    }

    @Test
    @Throws(Exception::class)
    fun `given lyrics when add lyrics then entity is ported to service`() {
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
        verify(exactly = 1) { lyricsService.addLyrics(testLyricsDto) }
        confirmVerified(lyricsService)
    }

    @Test
    @Throws(Exception::class)
    fun `given lyrics when update lyrics then entity update is ported to service`() {
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
        verify(exactly = 1) { lyricsService.updateLyrics(testLyricsDto) }
        confirmVerified(lyricsService)
    }

    @Test
    @Throws(Exception::class)
    fun `given lyrics when remove lyrics then entity removal is ported to service`() {
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
        verify(exactly = 1) { lyricsService.removeLyrics(testLyricsDto) }
        confirmVerified(lyricsService)
    }

    @Test
    @Throws(Exception::class)
    fun `given call to all lyrics when no params then findAll is ported to service`() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        every { lyricsService.getAllLyrics() } returns listOf(testLyricsDto)
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
        verify(exactly = 1) { lyricsService.getAllLyrics() }
        confirmVerified(lyricsService)
    }

    @Test
    @Throws(Exception::class)
    fun `given artist id when calling get lyrics by id then find by id in service`() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_AUTHOR,
            lyrics = TEST_LYRICS
        )

        val id = UUID.randomUUID()
        every { lyricsService.getLyricsById(id) } returns testLyricsDto
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
        verify(exactly = 1) { lyricsService.getLyricsById(id) }
        confirmVerified(lyricsService)
    }

    @Test
    @Throws(Exception::class)
    fun `given unexisting artist id when calling get lyrics by id then find by id in service fails`() {
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
        verify(exactly = 1) { lyricsService.getLyricsById(id) }
        confirmVerified(lyricsService)
    }

    companion object {
        private const val TEST_AUTHOR = "Rita Ora"
        private const val TEST_LYRICS = "Be there when the sun is rising"
    }
}