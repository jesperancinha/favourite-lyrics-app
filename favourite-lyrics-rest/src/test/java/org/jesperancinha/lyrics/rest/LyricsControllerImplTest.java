package org.jesperancinha.lyrics.rest;

import org.jesperancinha.lyrics.core.service.LyricsService;
import org.jesperancinha.lyrics.domain.data.LyricsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LyricsControllerImpl.class)
@ContextConfiguration(classes = {LyricsController.class, LyricsControllerImpl.class})
public class LyricsControllerImplTest {

    private static final String TEST_AUTHOR = "Rita Ora";
    private static final String TEST_LYRICS = "Be there when the sun is rising";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LyricsService lyricsService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void givenLyrics_whenAddLyrics_thenEntityIsPortedToService() throws Exception {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .participatingArtist(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(lyricsService, only()).addLyrics(testLyricsDto);
    }


    @Test
    void givenLyrics_whenUpdateLyrics_thenEntityUpdateIsPortedToService() throws Exception {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .participatingArtist(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();

        mvc.perform(MockMvcRequestBuilders.put("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(lyricsService, only()).updateLyrics(testLyricsDto);
    }

    @Test
    void givenLyrics_whenRemoveLyrics_thenEntityRemovalIsPortedToService() throws Exception {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .participatingArtist(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();

        mvc.perform(MockMvcRequestBuilders.delete("/lyrics")
                .content(objectMapper.writeValueAsString(testLyricsDto))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(lyricsService, only()).removeLyrics(testLyricsDto);
    }

    @Test
    void givenCallToAllLyricss_whenNoParams_thenFindAllIsPortedToService() throws Exception {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .participatingArtist(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();
        when(lyricsService.getAllLyrics()).thenReturn(Collections.singletonList(testLyricsDto));

        mvc.perform(MockMvcRequestBuilders.get("/lyrics")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].participatingArtist")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].participatingArtist")
                        .value(TEST_AUTHOR))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lyrics")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lyrics")
                        .value(TEST_LYRICS));

        verify(lyricsService, only()).getAllLyrics();
    }

    @Test
    void givenArtisId_whenCallingGetLyricsById_thenFindByIdToService() throws Exception {
        final LyricsDto testLyricsDto = LyricsDto.builder()
                .participatingArtist(TEST_AUTHOR)
                .lyrics(TEST_LYRICS)
                .build();
        when(lyricsService.getLyricsById(1L)).thenReturn(testLyricsDto);

        mvc.perform(MockMvcRequestBuilders.get("/lyrics/1")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist")
                        .value(TEST_AUTHOR))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lyrics")
                        .value(TEST_LYRICS));

        verify(lyricsService, only()).getLyricsById(1L);
    }

    @Test
    void givenUnexistingArtisId_whenCallingGetLyricsById_thenFindByIdToServiceFails() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/lyrics/1")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participatingArtist")
                        .doesNotExist());

        verify(lyricsService, only()).getLyricsById(1L);
    }

}