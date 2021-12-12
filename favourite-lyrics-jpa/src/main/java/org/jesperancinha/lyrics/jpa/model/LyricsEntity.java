package org.jesperancinha.lyrics.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lyrics")
@Getter
@Setter
@ToString
public class LyricsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private UUID lyricsId;

    @Column
    private String lyrics;

    @Column
    private String participatingArtist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LyricsEntity that = (LyricsEntity) o;
        return lyricsId != null && Objects.equals(lyricsId, that.lyricsId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
