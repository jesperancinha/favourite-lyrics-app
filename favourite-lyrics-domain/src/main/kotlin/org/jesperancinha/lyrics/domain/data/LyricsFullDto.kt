package org.jesperancinha.lyrics.domain.data

import java.util.*

data class LyricsFullDto(
    val uuid: UUID,
    val lyrics: String,
    val participatingArtist: String
)