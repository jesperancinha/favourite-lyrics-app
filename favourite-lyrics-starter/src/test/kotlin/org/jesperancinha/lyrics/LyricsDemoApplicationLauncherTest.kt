package org.jesperancinha.lyrics

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.collection.IsCollectionWithSize
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.jpa.model.LyricsEntity
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.Network
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
class LyricsDemoApplicationLauncherTest {
    @Autowired
    private val mvc: MockMvc? = null

    @SpyBean
    private val lyricsRepository: LyricsRepository? = null

    @Captor
    private val lyricsEntityArgumentCaptor: ArgumentCaptor<LyricsEntity>? = null
    @Test
    @Transactional
    @Throws(Exception::class)
    fun givenLyrics_whenAddAndUpdateAndThenRemoveLyrics_thenEntity() {
        val testLyricsDto: LyricsDto = LyricsDto.builder()
            .participatingArtist(TEST_ARTIST_NAME)
            .lyrics(TEST_LYRICS)
            .build()
        mvc!!.perform(
            MockMvcRequestBuilders.post("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
        Mockito.verify(lyricsRepository, Mockito.times(1)).save(
            lyricsEntityArgumentCaptor!!.capture()
        )
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/" + lyricsEntityArgumentCaptor.value.getId())
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").value(TEST_ARTIST_NAME))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").value(TEST_LYRICS))
        val testLyricsDto2: Any = LyricsDto.builder()
            .participatingArtist(TEST_ARTIST_NAME)
            .lyrics(TEST_LYRICS_2)
            .build()
        mvc.perform(
            MockMvcRequestBuilders.put("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        Mockito.verify(lyricsRepository, Mockito.times(2)).save(
            lyricsEntityArgumentCaptor.capture()
        )
        val lastId: UUID = lyricsEntityArgumentCaptor.value.getId()
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/$lastId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").value(TEST_ARTIST_NAME))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").value(TEST_LYRICS_2))
        mvc.perform(
            MockMvcRequestBuilders.delete("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/$lastId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").doesNotExist())
    }

    @Test
    @Throws(Exception::class)
    fun givenCallToAllLyrics_whenNoParams_thenFindAll() {
        mvc!!.perform(
            MockMvcRequestBuilders.get("/lyrics")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize<Any>(5)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].participatingArtist").value("William Orbit"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].lyrics").value("Sky fits heaven so fly it"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].participatingArtist").value("Ava Max"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].lyrics").value("Baby I'm torn"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].participatingArtist").value("Faun"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].lyrics").value("Wenn wir uns wiedersehen"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[3].participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[3].participatingArtist").value("Abel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[3].lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[3].lyrics").value("Het is al lang verleden tijd"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].participatingArtist").value("Billie Eilish"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].lyrics").value("Chest always so puffed guy"))
    }

    @Test
    @Throws(Exception::class)
    fun givenArtisId_whenCallingGetLyricsById_thenReturnsLyrics() {
        val id: UUID = lyricsRepository!!.findAll()[0].getId()
        mvc!!.perform(
            MockMvcRequestBuilders.get("/lyrics/$id")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").value("William Orbit"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").value("Sky fits heaven so fly it"))
    }

    @Test
    @Throws(Exception::class)
    fun givenUnexistingArtisId_whenCallingGetLyricsById_thenIsNotFound() {
        mvc!!.perform(
            MockMvcRequestBuilders.get("/lyrics/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist").doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics").doesNotExist())
    }

    companion object {
        private const val TEST_ARTIST_NAME = "Florence and the Machine"
        private const val TEST_LYRICS = "Sweet Nothings"
        private const val TEST_LYRICS_2 = "You've got the love I need to see me through"
        private val objectMapper = ObjectMapper()

        @Container
        val postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer<SELF>("postgres")
            .withDatabaseName("fla")
            .withUsername("postgres")
            .withPassword("admin")
            .withExposedPorts(5432)
            .withNetwork(Network.newNetwork())
            .withNetworkAliases("postgres")

        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgreSQLContainer.jdbcUrl + "&currentSchema=hexagonal" }
            registry.add("spring.datasource.username") { postgreSQLContainer.username }
            registry.add("spring.datasource.password") { postgreSQLContainer.password }
        }
    }
}