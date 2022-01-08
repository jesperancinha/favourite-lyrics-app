package org.jesperancinha.lyrics;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;
import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.jpa.model.LyricsEntity;
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
public class LyricsDemoApplicationLauncherTest {
    private static final String TEST_ARTIST_NAME = "Florence and the Machine";
    private static final String TEST_LYRICS = "Sweet Nothings";
    private static final String TEST_LYRICS_2 = "You've got the love I need to see me through";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @SpyBean
    private LyricsRepository lyricsRepository;

    @Captor
    private ArgumentCaptor<LyricsEntity> lyricsEntityArgumentCaptor;

    @Test
    @Transactional
    void givenLyrics_whenAddAndUpdateAndThenRemoveLyrics_thenEntity() throws Exception {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .participatingArtist(TEST_ARTIST_NAME)
                .lyrics(TEST_LYRICS)
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/lyrics")
                        .content(objectMapper.writeValueAsString(testLyricsDto))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());


        verify(lyricsRepository, times(1)).save(lyricsEntityArgumentCaptor.capture());

        mvc.perform(MockMvcRequestBuilders.get("/lyrics/" + lyricsEntityArgumentCaptor.getValue().getId())
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participatingArtist").exists())
                .andExpect(jsonPath("$.participatingArtist").value(TEST_ARTIST_NAME))
                .andExpect(jsonPath("$.lyrics").exists())
                .andExpect(jsonPath("$.lyrics").value(TEST_LYRICS));

        val testLyricsDto2 = LyricsDto.builder()
                .participatingArtist(TEST_ARTIST_NAME)
                .lyrics(TEST_LYRICS_2)
                .build();

        mvc.perform(MockMvcRequestBuilders.put("/lyrics")
                        .content(objectMapper.writeValueAsString(testLyricsDto2))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(lyricsRepository, times(2)).save(lyricsEntityArgumentCaptor.capture());

        final UUID lastId = lyricsEntityArgumentCaptor.getValue().getId();
        mvc.perform(MockMvcRequestBuilders.get("/lyrics/" + lastId)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participatingArtist").exists())
                .andExpect(jsonPath("$.participatingArtist").value(TEST_ARTIST_NAME))
                .andExpect(jsonPath("$.lyrics").exists())
                .andExpect(jsonPath("$.lyrics").value(TEST_LYRICS_2));

        mvc.perform(MockMvcRequestBuilders.delete("/lyrics")
                        .content(objectMapper.writeValueAsString(testLyricsDto))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/lyrics/" + lastId)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.participatingArtist").doesNotExist())
                .andExpect(jsonPath("$.lyrics").doesNotExist());
    }

    @Test
    void givenCallToAllLyrics_whenNoParams_thenFindAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/lyrics")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
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
                .andExpect(jsonPath("$[4].lyrics").value("Chest always so puffed guy"));
    }

    @Test
    void givenArtisId_whenCallingGetLyricsById_thenReturnsLyrics() throws Exception {
        final UUID id = lyricsRepository.findAll().get(0).getId();
        mvc.perform(MockMvcRequestBuilders.get("/lyrics/"+id)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participatingArtist").exists())
                .andExpect(jsonPath("$.participatingArtist").value("William Orbit"))
                .andExpect(jsonPath("$.lyrics").exists())
                .andExpect(jsonPath("$.lyrics").value("Sky fits heaven so fly it"));
    }

    @Test
    void givenUnexistingArtisId_whenCallingGetLyricsById_thenIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/lyrics/" + UUID.randomUUID())
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.participatingArtist").doesNotExist())
                .andExpect(jsonPath("$.lyrics").doesNotExist());
    }

    @Container
    final static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("fla")
            .withUsername("postgres")
            .withPassword("admin")
            .withExposedPorts(5432)
            .withNetwork(Network.newNetwork())
            .withNetworkAliases("postgres");

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> postgreSQLContainer.getJdbcUrl() + "&currentSchema=hexagonal");
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

}