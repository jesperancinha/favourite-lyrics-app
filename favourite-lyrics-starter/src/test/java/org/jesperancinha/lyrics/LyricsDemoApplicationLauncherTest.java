package org.jesperancinha.lyrics;

import org.jesperancinha.lyrics.domain.data.LyricsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LyricsDemoApplicationLauncherTest {
    private static final String TEST_ARTIST_NAME = "Florence and the Machine";
    private static final String TEST_LYRICS = "Sweet Nothings";
    private static final String TEST_LYRICS_2 = "You've got the love I need to see me through";

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void givenLyrics_whenAddAndUpdateAndThenRemoveLyrics_thenEntity() throws Exception {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .author(TEST_ARTIST_NAME)
                .lyrics(TEST_LYRICS)
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(MockMvcRequestBuilders.get("/lyrics/6")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.author").value(TEST_ARTIST_NAME))
                .andExpect(jsonPath("$.lyrics").exists())
                .andExpect(jsonPath("$.lyrics").value(TEST_LYRICS));

        testLyricsDto.setLyrics(TEST_LYRICS_2);

        mvc.perform(MockMvcRequestBuilders.put("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/lyrics/6")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.author").value(TEST_ARTIST_NAME))
                .andExpect(jsonPath("$.lyrics").exists())
                .andExpect(jsonPath("$.lyrics").value(TEST_LYRICS_2));

        mvc.perform(MockMvcRequestBuilders.delete("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/lyrics/6")
                .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.author").doesNotExist())
                .andExpect(jsonPath("$.lyrics").doesNotExist());
    }

    @Test
    void givenCallToAllLyrics_whenNoParams_thenFindAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/lyrics")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].author").exists())
                .andExpect(jsonPath("$[0].author").value("William Orbit"))
                .andExpect(jsonPath("$[0].lyrics").exists())
                .andExpect(jsonPath("$[0].lyrics").value("Sky fits heaven so fly it"))
                .andExpect(jsonPath("$[1].author").exists())
                .andExpect(jsonPath("$[1].author").value("Ava Max"))
                .andExpect(jsonPath("$[1].lyrics").exists())
                .andExpect(jsonPath("$[1].lyrics").value("Baby I'm torn"))
                .andExpect(jsonPath("$[2].author").exists())
                .andExpect(jsonPath("$[2].author").value("Faun"))
                .andExpect(jsonPath("$[2].lyrics").exists())
                .andExpect(jsonPath("$[2].lyrics").value("Wenn wir uns wiedersehen"))
                .andExpect(jsonPath("$[3].author").exists())
                .andExpect(jsonPath("$[3].author").value("Abel"))
                .andExpect(jsonPath("$[3].lyrics").exists())
                .andExpect(jsonPath("$[3].lyrics").value("Het is al lang verleden tijd"))
                .andExpect(jsonPath("$[4].author").exists())
                .andExpect(jsonPath("$[4].author").value("Billie Eilish"))
                .andExpect(jsonPath("$[4].lyrics").exists())
                .andExpect(jsonPath("$[4].lyrics").value("Chest always so puff guy"));
    }

    @Test
    void givenArtisId_whenCallingGetLyricsById_thenReturnsLyrics() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/lyrics/1")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.author").value("William Orbit"))
                .andExpect(jsonPath("$.lyrics").exists())
                .andExpect(jsonPath("$.lyrics").value("Sky fits heaven so fly it"));
    }

    @Test
    void givenUnexistingArtisId_whenCallingGetLyricsById_thenIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/lyrics/7")
                .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.author").doesNotExist())
                .andExpect(jsonPath("$.lyrics").doesNotExist());
    }
}