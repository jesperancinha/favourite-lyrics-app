package org.jesperancinha.lyrics.jpa.adapter

import org.jesperancinha.lyrics.domain.data.LyricsDto
import org.jesperancinha.lyrics.domain.data.LyricsFullDto
import org.jesperancinha.lyrics.domain.exception.LyricsNotFoundException
import org.jesperancinha.lyrics.domain.port.LyricsPersistencePort
import org.jesperancinha.lyrics.jpa.model.LyricsEntity
import org.jesperancinha.lyrics.jpa.repository.LyricsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class LyricsJpaAdapter(private val lyricsRepository: LyricsRepository) : LyricsPersistencePort {
    override fun addLyrics(lyricsDto: LyricsDto) {
        val lyricsEntity = getLyricsEntity(lyricsDto)
        lyricsRepository.save(lyricsEntity)
    }

    override fun removeLyrics(lyricsDto: LyricsDto) {
        lyricsRepository.deleteAllByParticipatingArtist(requireNotNull(lyricsDto.participatingArtist))
    }

    override fun updateLyrics(lyricsDto: LyricsDto) {
        val byParticipatingArtist =
            lyricsRepository.findByParticipatingArtist(requireNotNull(lyricsDto.participatingArtist))
        if (isOnlyNullValues(byParticipatingArtist)) {
            lyricsRepository.save(byParticipatingArtist.copy(lyrics = lyricsDto.lyrics))
        } else {
            val findByLyrics: LyricsEntity = lyricsRepository.findByLyrics(requireNotNull(lyricsDto.lyrics))
            if (!isOnlyNullValues(findByLyrics)) {
                lyricsRepository.save(findByLyrics.copy(participatingArtist = lyricsDto.participatingArtist))
            }
        }
    }

    override fun getAllLyrics(): List<LyricsDto> = lyricsRepository.findAll()
        .filterNotNull()
        .map { lyricsEntity -> getLyrics(lyricsEntity) }

    override fun getAllLFullLyrics(): List<LyricsFullDto> = lyricsRepository.findAll()
        .filterNotNull()
        .map { lyricsEntity -> fullLyrics(lyricsEntity) }

    override fun getLyricsById(lyricsId: UUID): LyricsDto = getLyrics(
        requireNotNull(lyricsRepository.findById(lyricsId)
            .orElseThrow { LyricsNotFoundException(lyricsId) })
    )

    private fun getLyricsEntity(lyricsDto: LyricsDto): LyricsEntity {
        return LyricsEntity(
            participatingArtist = lyricsDto.participatingArtist,
            lyrics = lyricsDto.lyrics
        )
    }

    private fun getLyrics(lyricsEntity: LyricsEntity): LyricsDto {
        return LyricsDto(
            participatingArtist = lyricsEntity.participatingArtist,
            lyrics = lyricsEntity.lyrics
        )

    }

    private fun fullLyrics(lyricsEntity: LyricsEntity): LyricsFullDto {
        return LyricsFullDto(
            participatingArtist = requireNotNull(lyricsEntity.participatingArtist),
            lyrics = requireNotNull(lyricsEntity.lyrics),
            uuid = requireNotNull(lyricsEntity.id)
        )
    }
}
fun isOnlyNullValues(lyricsEntity: LyricsEntity)
        = lyricsEntity.participatingArtist == null && lyricsEntity.lyrics == null && lyricsEntity.id == null
