package org.jesperancinha.lyrics.domain.data

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LyricsTest {
    @Test
    fun givenName_whenLyricsWithName_thenNameIsSet() {
        val (lyrics, participatingArtist) = LyricsDto.builder()
            .participatingArtist("Lady Gaga")
            .lyrics("You can read my Pokerface")
            .build()
        Assertions.assertThat(participatingArtist).isEqualTo("Lady Gaga")
        Assertions.assertThat(lyrics).isEqualTo("You can read my Pokerface")
    }

    @Test
    fun givenTwoLyrics_whenSameNameAndLyrics_thenEqual() {
        val lyricsDto1: LyricsDto = LyricsDto.builder()
            .participatingArtist("Freddy Mercury")
            .lyrics("We are the champions")
            .build()
        val lyricsDto2: LyricsDto = LyricsDto.builder()
            .participatingArtist("Freddy Mercury")
            .lyrics("We are the champions")
            .build()
        Assertions.assertThat(lyricsDto1).isEqualTo(lyricsDto2)
    }

    @Test
    fun givenTwoLyrics_whenDifferentParticipatingArtist_thenNotEqual() {
        val lyricsDto1: LyricsDto = LyricsDto.builder()
            .participatingArtist("Ariana Grande")
            .lyrics("I've got one less problem")
            .build()
        val lyricsDto2: LyricsDto = LyricsDto.builder()
            .participatingArtist("Iggy Azalea")
            .lyrics("I've got one less problem")
            .build()
        Assertions.assertThat(lyricsDto1).isNotEqualTo(lyricsDto2)
    }

    @Test
    fun givenTwoLyrics_whenDifferentLyrics_thenNotEqual() {
        val lyricsDto1: LyricsDto = LyricsDto.builder()
            .participatingArtist("Ariana Grande")
            .lyrics("I've got one less problem")
            .build()
        val lyricsDto2: LyricsDto = LyricsDto.builder()
            .participatingArtist("Ariana Grande")
            .lyrics("don't call me angerl")
            .build()
        Assertions.assertThat(lyricsDto1).isNotEqualTo(lyricsDto2)
    }

    @Test
    fun givenTwoLyrics_whenSameParticipatingArtistSameLyrics_thenSameHashCode() {
        val lyricsDto1: LyricsDto = LyricsDto.builder()
            .participatingArtist("Cardi B")
            .lyrics("Bloody shoes")
            .build()
        val lyricsDto2: LyricsDto = LyricsDto.builder()
            .participatingArtist("Cardi B")
            .lyrics("Bloody shoes")
            .build()
        Assertions.assertThat(lyricsDto1.hashCode()).isEqualTo(lyricsDto2.hashCode())
    }

    @Test
    fun givenTwoLyrics_whenDifferentParticipatingArtist_thenNotSameHashCode() {
        val lyricsDto1: LyricsDto = LyricsDto.builder()
            .participatingArtist("BTS")
            .lyrics("boy with luv")
            .build()
        val lyricsDto2: LyricsDto = LyricsDto.builder()
            .participatingArtist("Halsey")
            .lyrics("boy with luv")
            .build()
        Assertions.assertThat(lyricsDto1.hashCode()).isNotEqualTo(lyricsDto2.hashCode())
    }

    @Test
    fun givenLyrics_whenToString_thenSeeADescriptiveString() {
        val lyricsDto1: LyricsDto = LyricsDto.builder()
            .participatingArtist("Ed Sheeran")
            .lyrics("I'm in love with the shape of you")
            .build()
        Assertions.assertThat(lyricsDto1.toString())
            .isEqualTo("LyricsDto[lyrics=I'm in love with the shape of you, participatingArtist=Ed Sheeran]")
    }
}