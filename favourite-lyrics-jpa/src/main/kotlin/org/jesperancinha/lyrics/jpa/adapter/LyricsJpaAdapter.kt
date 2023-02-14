package org.jesperancinha.lyrics.jpa.adapter

import lombok.SneakyThrows
import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import org.jesperancinha.lyrics.jpa.model.LyricsEntity
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Supplier
import java.util.stream.Collectors

@Service
class LyricsJpaAdapter(private val lyricsRepository: LyricsRepository) : LyricsPersistencePort {
    override fun addLyrics(lyricsDto: LyricsDto?) {
        val lyricsEntity = getLyricsEntity(lyricsDto)
        lyricsRepository.save(lyricsEntity)
    }

    override fun removeLyrics(lyricsDto: LyricsDto?) {
        lyricsRepository.deleteAllByParticipatingArtist(lyricsDto!!.participatingArtist)
    }

    override fun updateLyrics(lyricsDto: LyricsDto?) {
        val byParticipatingArtist = lyricsRepository.findByParticipatingArtist(lyricsDto!!.participatingArtist)
        if (Objects.nonNull(byParticipatingArtist)) {
            byParticipatingArtist.lyrics = lyricsDto.lyrics
            lyricsRepository.save(byParticipatingArtist)
        } else {
            val byLyrics = lyricsRepository.findByLyrics(lyricsDto.lyrics)
            if (Objects.nonNull(byLyrics)) {
                byLyrics.participatingArtist = lyricsDto.participatingArtist
                lyricsRepository.save(byLyrics)
            }
        }
    }

    override val allLyrics: List<LyricsDto>
        get() = lyricsRepository.findAll()
            .stream()
            .map { lyricsEntity: LyricsEntity -> getLyrics(lyricsEntity) }
            .collect(Collectors.toList())
    override val allLFullLyrics: List<LyricsFullDto>
        get() = lyricsRepository.findAll()
            .stream()
            .map { lyricsEntity: LyricsEntity -> fullLyrics(lyricsEntity) }
            .collect(Collectors.toList())

    @SneakyThrows
    override fun getLyricsById(lyricsId: UUID?): LyricsDto? {
        return getLyrics(
            lyricsRepository.findById(lyricsId)
                .orElseThrow(Supplier<Throwable> { LyricsNotFoundException(lyricsId) })!!
        )
    }

    private fun getLyricsEntity(lyricsDto: LyricsDto?): LyricsEntity {
        return LyricsEntity.builder()
            .participatingArtist(lyricsDto!!.participatingArtist)
            .lyrics(lyricsDto.lyrics)
            .build()
    }

    private fun getLyrics(lyricsEntity: LyricsEntity): LyricsDto {
        return LyricsDto.builder()
            .participatingArtist(lyricsEntity.participatingArtist)
            .lyrics(lyricsEntity.lyrics)
            .build()
    }

    private fun fullLyrics(lyricsEntity: LyricsEntity): LyricsFullDto {
        return LyricsFullDto.builder()
            .participatingArtist(lyricsEntity.participatingArtist)
            .lyrics(lyricsEntity.lyrics)
            .uuid(lyricsEntity.id)
            .build()
    }
}