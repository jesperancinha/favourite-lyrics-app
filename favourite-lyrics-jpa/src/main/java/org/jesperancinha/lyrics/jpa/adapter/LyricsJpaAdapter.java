package org.jesperancinha.lyrics.jpa.adapter;

import lombok.SneakyThrows;
import org.jesperancinha.lyrics.domain.data.LyricsDto;
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException;
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort;
import org.jesperancinha.lyrics.jpa.model.LyricsEntity;
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class LyricsJpaAdapter implements LyricsPersistencePort {

    private final LyricsRepository lyricsRepository;

    public LyricsJpaAdapter(LyricsRepository lyricsRepository) {
        this.lyricsRepository = lyricsRepository;
    }

    @Override
    public void addLyrics(LyricsDto lyricsDto) {
        final LyricsEntity lyricsEntity = getLyricsEntity(lyricsDto);
        lyricsRepository.save(lyricsEntity);
    }

    @Override
    public void removeLyrics(LyricsDto lyricsDto) {
        lyricsRepository.deleteAllByParticipatingArtist(lyricsDto.getParticipatingArtist());
    }

    @Override
    public void updateLyrics(LyricsDto lyricsDto) {
        final LyricsEntity byParticipatingArtist = lyricsRepository.findByParticipatingArtist(lyricsDto.getParticipatingArtist());
        if (Objects.nonNull(byParticipatingArtist)) {
            byParticipatingArtist.setLyrics(lyricsDto.getLyrics());
            lyricsRepository.save(byParticipatingArtist);
        } else {
            final LyricsEntity byLyrics = lyricsRepository.findByLyrics(lyricsDto.getLyrics());
            if (Objects.nonNull(byLyrics)) {
                byLyrics.setParticipatingArtist(lyricsDto.getParticipatingArtist());
                lyricsRepository.save(byLyrics);
            }
        }
    }

    @Override
    public List<LyricsDto> getAllLyrics() {
        return lyricsRepository.findAll()
                .stream()
                .map(this::getLyrics)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public LyricsDto getLyricsById(UUID lyricsId) {
        return getLyrics(lyricsRepository.findById(lyricsId)
                .orElseThrow((Supplier<Throwable>) () -> new LyricsNotFoundException(lyricsId)));
    }

    private LyricsEntity getLyricsEntity(LyricsDto lyricsDto) {
        return LyricsEntity.builder()
                .participatingArtist(lyricsDto.getParticipatingArtist())
                .lyrics(lyricsDto.getLyrics())
                .build();
    }

    private LyricsDto getLyrics(LyricsEntity lyricsEntity) {
        return LyricsDto.builder()
                .participatingArtist(lyricsEntity.getParticipatingArtist())
                .lyrics(lyricsEntity.getLyrics())
                .build();
    }

}
