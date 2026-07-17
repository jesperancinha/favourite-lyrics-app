package org.jesperancinha.lyrics

import org.hamcrest.collection.IsCollectionWithSize
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.jpa.model.LyricsEntity
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.Network
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import tools.jackson.databind.ObjectMapper
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
@TestMethodOrder(OrderAnnotation::class)
class LyricsDemoApplicationLauncherTest @Autowired constructor(
    private val mvc: MockMvc,
    @MockitoSpyBean private val lyricsRepository: LyricsRepository,
    private val objectMapper: ObjectMapper,
) {
    private val lyricsEntityArgumentCaptor: ArgumentCaptor<LyricsEntity> =
        ArgumentCaptor.forClass(LyricsEntity::class.java)

    @Test
    @Transactional
    @Execution(SAME_THREAD)
    @Order(1)
    @Throws(Exception::class)
    fun givenLyrics_whenAddAndUpdateAndThenRemoveLyrics_thenEntity() {
        val testLyricsDto = LyricsDto(
            participatingArtist = TEST_ARTIST_NAME,
            lyrics = TEST_LYRICS
        )

        mvc.perform(
            MockMvcRequestBuilders.post("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
        Mockito.verify(lyricsRepository, Mockito.times(1)).save(
            lyricsEntityArgumentCaptor.capture()
        )
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/" + lyricsEntityArgumentCaptor.value.id)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.participatingArtist").exists())
            .andExpect(jsonPath("$.participatingArtist").value(TEST_ARTIST_NAME))
            .andExpect(jsonPath("$.lyrics").exists())
            .andExpect(jsonPath("$.lyrics").value(TEST_LYRICS))
        val testLyricsDto2: Any = LyricsDto(
            participatingArtist = TEST_ARTIST_NAME,
            lyrics = TEST_LYRICS_2
        )

        mvc.perform(
            MockMvcRequestBuilders.put("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
        Mockito.verify(lyricsRepository, Mockito.times(2)).save(
            lyricsEntityArgumentCaptor.capture()
        )
        val lastId = lyricsEntityArgumentCaptor.value.id
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/$lastId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.participatingArtist").exists())
            .andExpect(jsonPath("$.participatingArtist").value(TEST_ARTIST_NAME))
            .andExpect(jsonPath("$.lyrics").exists())
            .andExpect(jsonPath("$.lyrics").value(TEST_LYRICS_2))
        mvc.perform(
            MockMvcRequestBuilders.delete("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/$lastId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.participatingArtist").doesNotExist())
            .andExpect(jsonPath("$.lyrics").doesNotExist())
    }

    @Test
    @Execution(SAME_THREAD)
    @Order(2)
    @Throws(Exception::class)
    fun givenCallToAllLyrics_whenNoParams_thenFindAll() {
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$", IsCollectionWithSize.hasSize<Any>(5)))
            .andExpect(jsonPath("$[0].participatingArtist").exists())
            .andExpect(jsonPath("$[0].participatingArtist").value("William Orbit"))
            .andExpect(jsonPath("$[0].lyrics").exists())
            .andExpect(jsonPath("$[0].lyrics").value("Sky fits heaven so fly it"))
            .andExpect(jsonPath("$[1].participatingArtist").exists())
            .andExpect(jsonPath("$[1].participatingArtist").value("Ava Max"))
            .andExpect(jsonPath("$[1].lyrics").exists())
            .andExpect(jsonPath("$[1].lyrics").value("Baby I'm torn"))
            .andExpect(jsonPath("$[2].participatingArtist").exists())
            .andExpect(jsonPath("$[2].participatingArtist").value("Faun"))
            .andExpect(jsonPath("$[2].lyrics").exists())
            .andExpect(jsonPath("$[2].lyrics").value("Wenn wir uns wiedersehen"))
            .andExpect(jsonPath("$[3].participatingArtist").exists())
            .andExpect(jsonPath("$[3].participatingArtist").value("Abel"))
            .andExpect(jsonPath("$[3].lyrics").exists())
            .andExpect(jsonPath("$[3].lyrics").value("Het is al lang verleden tijd"))
            .andExpect(jsonPath("$[4].participatingArtist").exists())
            .andExpect(jsonPath("$[4].participatingArtist").value("Billie Eilish"))
            .andExpect(jsonPath("$[4].lyrics").exists())
            .andExpect(jsonPath("$[4].lyrics").value("Chest always so puffed guy"))
    }

    @Test
    @Execution(SAME_THREAD)
    @Order(3)
    @Throws(Exception::class)
    fun givenArtisId_whenCallingGetLyricsById_thenReturnsLyrics() {
        val id = lyricsRepository.findAll()[0].id
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/$id")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.participatingArtist").exists())
            .andExpect(jsonPath("$.participatingArtist").value("William Orbit"))
            .andExpect(jsonPath("$.lyrics").exists())
            .andExpect(jsonPath("$.lyrics").value("Sky fits heaven so fly it"))
    }

    @Test
    @Execution(SAME_THREAD)
    @Order(4)
    @Throws(Exception::class)
    fun givenUnexistingArtisId_whenCallingGetLyricsById_thenIsNotFound() {
        mvc.perform(
            MockMvcRequestBuilders.get("/lyrics/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.participatingArtist").doesNotExist())
            .andExpect(jsonPath("$.lyrics").doesNotExist())
    }

    companion object {
        private const val TEST_ARTIST_NAME = "Florence and the Machine"
        private const val TEST_LYRICS = "Sweet Nothings"
        private const val TEST_LYRICS_2 = "You've got the love I need to see me through"

        @Container
        @JvmStatic
        val postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:15")
            .withDatabaseName("fla")
            .withUsername("postgres")
            .withPassword("admin")
            .withEnv("POSTGRES_MULTIPLE_DATABASES", "fla")
            .withNetwork(Network.newNetwork())
            .withNetworkAliases("postgres")

        @DynamicPropertySource
        @JvmStatic
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgreSQLContainer.jdbcUrl + "&currentSchema=hexagonal" }
            registry.add("spring.datasource.username") { postgreSQLContainer.username }
            registry.add("spring.datasource.password") { postgreSQLContainer.password }
        }
    }
}