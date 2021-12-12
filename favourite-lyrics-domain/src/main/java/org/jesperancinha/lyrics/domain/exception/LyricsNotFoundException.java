package org.jesperancinha.lyrics.domain.exception;

import java.util.UUID;

public class LyricsNotFoundException extends RuntimeException {

    public LyricsNotFoundException(UUID id) {
        super("Lyrics with id %s not found!".formatted(id));
    }
}
