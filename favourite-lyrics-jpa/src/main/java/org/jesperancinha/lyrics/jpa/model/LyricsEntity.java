package org.jesperancinha.lyrics.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "LYRICS")
@Data
public class LyricsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long lyricsId;

    @Column
    private String lyrics;

    @Column
    private String participatingArtist;

}
