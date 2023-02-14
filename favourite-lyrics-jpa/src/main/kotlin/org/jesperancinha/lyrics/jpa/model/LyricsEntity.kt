package org.jesperancinha.lyrics.jpa.model

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.util.*

@Entity
@Table(name = "lyrics")
data class LyricsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private val id: UUID? = null,

    @Column
    private val lyrics: String? = null,

    @Column
    private val participatingArtist: String? = null,
) {

    override fun equals(o: Any?): Boolean {
        if (o == null) {
            return false
        }
        if (o !is LyricsEntity) {
            return false
        }
        if (this === o) return true
        return if (Hibernate.getClass(this) != Hibernate.getClass<Any>(o)) false else id != null && id == o.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}