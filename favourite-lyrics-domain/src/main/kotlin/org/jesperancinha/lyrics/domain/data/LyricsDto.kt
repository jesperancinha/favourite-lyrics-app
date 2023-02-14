package org.jesperancinha.lyrics.domain.data

import lombok.Builder

@JvmRecord
data class LyricsDto @Builder constructor(@JvmField val lyrics: String, @JvmField val participatingArtist: String)