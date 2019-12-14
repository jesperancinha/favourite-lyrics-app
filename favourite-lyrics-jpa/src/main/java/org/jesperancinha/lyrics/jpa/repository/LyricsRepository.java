package org.jesperancinha.lyrics.jpa.repository;

import org.jesperancinha.lyrics.jpa.model.LyricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LyricsRepository extends JpaRepository<LyricsEntity, Long> {

    void deleteAllByAuthor(String name);

    LyricsEntity findByAuthor(String Name);

    LyricsEntity findByLyrics(String Lyrics);
}
