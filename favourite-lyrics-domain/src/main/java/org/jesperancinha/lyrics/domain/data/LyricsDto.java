package org.jesperancinha.lyrics.domain.data;

import lombok.Builder;

public record LyricsDto(
        String lyrics,
        String participatingArtist
) {

    @Builder
    public LyricsDto {
    }
}