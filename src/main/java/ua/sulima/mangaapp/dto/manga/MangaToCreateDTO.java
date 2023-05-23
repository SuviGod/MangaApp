package ua.sulima.mangaapp.dto.manga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MangaToCreateDTO {
    private Integer id;

    private String name;

    private String alternativeNames;

    private Integer authorId;

    private Integer artistId;

    private Short releaseYear;

    private Long translatorId;

    private String description;

}
