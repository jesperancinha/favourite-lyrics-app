package org.jesperancinha.lyrics.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class LyricsDto {

    private String lyrics;

    private String author;

}
