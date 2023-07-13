package ua.sulima.mangaapp.dto.manga;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MangaToShowDTO {

    private Integer id;

    private String name;

    private String alternativeNames;

    private Short releaseYear;

    private String description;

    private LocalDateTime addDatetime;

    private LocalDateTime updateDatetime;

    private LocalDateTime approvalDatetime;

    private String previewImagePath;

    private Boolean isApproved;
}
