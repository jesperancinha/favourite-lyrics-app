package org.jesperancinhalyrics.domain.data

import org.assertj.core.api.Assertions
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.junit.jupiter.api.Test

class LyricsTest {
    @Test
    fun givenName_whenLyricsWithName_thenNameIsSet() {
        val (lyrics, participatingArtist) = LyricsDto(
            participatingArtist = "Lady Gaga",
            lyrics = "You can read my Pokerface"
        )
        Assertions.assertThat(participatingArtist).isEqualTo("Lady Gaga")
        Assertions.assertThat(lyrics).isEqualTo("You can read my Pokerface")
    }

    @Test
    fun givenTwoLyrics_whenSameNameAndLyrics_thenEqual() {
        val lyricsDto1 = LyricsDto(
            participatingArtist = "Freddy Mercury",
            lyrics = "We are the champions"
        )
        val lyricsDto2 = LyricsDto(
            participatingArtist = "Freddy Mercury",
            lyrics = "We are the champions"
        )

        Assertions.assertThat(lyricsDto1).isEqualTo(lyricsDto2)
    }

    @Test
    fun givenTwoLyrics_whenDifferentParticipatingArtist_thenNotEqual() {
        val lyricsDto1 = LyricsDto(
            participatingArtist = "Ariana Grande",
            lyrics = "I've got one less problem"
        )

        val lyricsDto2 = LyricsDto(
            participatingArtist = "Iggy Azalea",
            lyrics = "I've got one less problem"
        )

        Assertions.assertThat(lyricsDto1).isNotEqualTo(lyricsDto2)
    }

    @Test
    fun givenTwoLyrics_whenDifferentLyrics_thenNotEqual() {
        val lyricsDto1 = LyricsDto(
            participatingArtist = "Ariana Grande",
            lyrics = "I've got one less problem"
        )

        val lyricsDto2 = LyricsDto(
            participatingArtist = "Ariana Grande",
            lyrics = "don't call me angerl"
        )

        Assertions.assertThat(lyricsDto1).isNotEqualTo(lyricsDto2)
    }

    @Test
    fun givenTwoLyrics_whenSameParticipatingArtistSameLyrics_thenSameHashCode() {
        val lyricsDto1 = LyricsDto(
            participatingArtist = "Cardi B",
            lyrics = "Bloody shoes"
        )

        val lyricsDto2 = LyricsDto(
            participatingArtist = "Cardi B",
            lyrics = "Bloody shoes"
        )

        Assertions.assertThat(lyricsDto1.hashCode()).isEqualTo(lyricsDto2.hashCode())
    }

    @Test
    fun givenTwoLyrics_whenDifferentParticipatingArtist_thenNotSameHashCode() {
        val lyricsDto1 = LyricsDto(
            participatingArtist = "BTS",
            lyrics = "boy with luv"
        )

        val lyricsDto2 = LyricsDto(
            participatingArtist = "Halsey",
            lyrics = "boy with luv"
        )

        Assertions.assertThat(lyricsDto1.hashCode()).isNotEqualTo(lyricsDto2.hashCode())
    }

    @Test
    fun givenLyrics_whenToString_thenSeeADescriptiveString() {
        val lyricsDto1 = LyricsDto(
            participatingArtist = "Ed Sheeran",
            lyrics = "I'm in love with the shape of you"
        )

        Assertions.assertThat(lyricsDto1.toString())
            .isEqualTo("LyricsDto[lyrics=I'm in love with the shape of you, participatingArtist=Ed Sheeran]")
    }
}