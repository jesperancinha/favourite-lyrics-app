package org.jesperancinha.lyrics.domain.exception

import java.util.*

class LyricsNotFoundException(id: UUID) : RuntimeException("Lyrics with id $id not found!")