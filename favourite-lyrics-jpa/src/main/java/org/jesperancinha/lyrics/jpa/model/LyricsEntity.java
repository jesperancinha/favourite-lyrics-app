package org.jesperancinha.lyrics.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @Column
    private String lyrics;

    @Column
    private String participatingArtist;

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if(!(o instanceof LyricsEntity that)){
            return false;
        }
        if (this == o) return true;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
