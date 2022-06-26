package org.jesperancinha.lyrics.domain.data;

import lombok.Builder;

import java.util.UUID;

public record LyricsFullDto(
        UUID uuid,
        String lyrics,
        String participatingArtist
) {

    @Builder
    public LyricsFullDto {
    }
}