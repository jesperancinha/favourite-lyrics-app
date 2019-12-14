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
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class LyricsJpaAdapter implements LyricsPersistencePort {

    private LyricsRepository lyricsRepository;

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
        lyricsRepository.deleteAllByAuthor(lyricsDto.getAuthor());
    }

    @Override
    public void updateLyrics(LyricsDto lyricsDto) {
        final LyricsEntity byAuthor = lyricsRepository.findByAuthor(lyricsDto.getAuthor());
        if (Objects.nonNull(byAuthor)) {
            byAuthor.setLyrics(lyricsDto.getLyrics());
            lyricsRepository.save(byAuthor);
        } else {
            final LyricsEntity byLyrics = lyricsRepository.findByLyrics(lyricsDto.getLyrics());
            if (Objects.nonNull(byLyrics)) {
                byLyrics.setAuthor(lyricsDto.getAuthor());
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
    public LyricsDto getLyricsById(Long lyricsId) {
        return getLyrics(lyricsRepository.findById(lyricsId)
                .orElseThrow((Supplier<Throwable>) () -> new LyricsNotFoundException(lyricsId)));
    }

    private LyricsEntity getLyricsEntity(LyricsDto lyricsDto) {
        return LyricsEntity.builder()
                .author(lyricsDto.getAuthor())
                .lyrics(lyricsDto.getLyrics())
                .build();
    }

    private LyricsDto getLyrics(LyricsEntity lyricsEntity) {
        return LyricsDto.builder()
                .author(lyricsEntity.getAuthor())
                .lyrics(lyricsEntity.getLyrics())
                .build();
    }

}
