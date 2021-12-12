package org.jesperancinha.lyrics.jpa.repository;

import org.jesperancinha.lyrics.jpa.model.LyricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LyricsRepository extends JpaRepository<LyricsEntity, UUID> {

    void deleteAllByParticipatingArtist(String name);

    LyricsEntity findByParticipatingArtist(String Name);

    LyricsEntity findByLyrics(String Lyrics);
}
