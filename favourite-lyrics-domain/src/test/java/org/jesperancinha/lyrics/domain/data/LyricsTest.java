package org.jesperancinha.lyrics.domain.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LyricsTest {

    @Test
    public void givenName_whenLyricsWithName_thenNameIsSet() {
        final LyricsDto lyricsDto = LyricsDto.builder()
                .author("Lady Gaga")
                .lyrics("You can read my Pokerface")
                .build();

        assertThat(lyricsDto.getAuthor()).isEqualTo("Lady Gaga");
        assertThat(lyricsDto.getLyrics()).isEqualTo("You can read my Pokerface");
    }

    @Test
    public void givenTwoLyrics_whenSameNameAndLyrics_thenEqual() {
        final LyricsDto lyricsDto1 = LyricsDto.builder()
                .author("Freddy Mercury")
                .lyrics("We are the champions")
                .build();
        final LyricsDto lyricsDto2 = LyricsDto.builder()
                .author("Freddy Mercury")
                .lyrics("We are the champions")
                .build();

        assertThat(lyricsDto1).isEqualTo(lyricsDto2);
    }

    @Test
    public void givenTwoLyrics_whenDifferentAuthor_thenNotEqual() {
        final LyricsDto lyricsDto1 = LyricsDto.builder()
                .author("Ariana Grande")
                .lyrics("I've got one less problem")
                .build();
        final LyricsDto lyricsDto2 = LyricsDto.builder()
                .author("Iggy Azalea")
                .lyrics("I've got one less problem")
                .build();

        assertThat(lyricsDto1).isNotEqualTo(lyricsDto2);
    }

    @Test
    public void givenTwoLyrics_whenDifferentLyrics_thenNotEqual() {
        final LyricsDto lyricsDto1 = LyricsDto.builder()
                .author("Ariana Grande")
                .lyrics("I've got one less problem")
                .build();
        final LyricsDto lyricsDto2 = LyricsDto.builder()
                .author("Ariana Grande")
                .lyrics("don't call me angerl")
                .build();

        assertThat(lyricsDto1).isNotEqualTo(lyricsDto2);
    }

    @Test
    public void givenTwoLyrics_whenSameAuthorSameLyrics_thenSameHashCode() {
        final LyricsDto lyricsDto1 = LyricsDto.builder()
                .author("Cardi B")
                .lyrics("Bloody shoes")
                .build();
        final LyricsDto lyricsDto2 = LyricsDto.builder()
                .author("Cardi B")
                .lyrics("Bloody shoes")
                .build();

        assertThat(lyricsDto1.hashCode()).isEqualTo(lyricsDto2.hashCode());
    }

    @Test
    public void givenTwoLyrics_whenDifferentAuthor_thenNotSameHashCode() {
        final LyricsDto lyricsDto1 = LyricsDto.builder()
                .author("BTS")
                .lyrics("boy with luv")
                .build();
        final LyricsDto lyricsDto2 = LyricsDto.builder()
                .author("Halsey")
                .lyrics("boy with luv")
                .build();

        assertThat(lyricsDto1.hashCode()).isNotEqualTo(lyricsDto2.hashCode());
    }

    @Test
    public void givenLyrics_whenToString_thenSeeADescriptiveString() {
        final LyricsDto lyricsDto1 = LyricsDto.builder()
                .author("Ed Sheeran")
                .lyrics("I'm in love with the shape of you")
                .build();

        assertThat(lyricsDto1.toString()).isEqualTo("LyricsDto(lyrics=I'm in love with the shape of you, author=Ed Sheeran)");
    }
}