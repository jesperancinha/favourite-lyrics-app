package org.jesperancinha.lyrics.domain.data

import java.util.*

@JvmRecord
data class LyricsFullDto @Builder constructor(val uuid: UUID, val lyrics: String, val participatingArtist: String) {
    init {
        participatingArtist = participatingArtist
        lyrics = lyrics
    }
}