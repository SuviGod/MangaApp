package ua.sulima.mangaapp.dto.manga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MangaToCreateWithMultipartFileDTO {
    private String name;

    private String alternativeNames;

    private Integer authorId;

    private Integer artistId;

    private Long groupId;

    private Short releaseYear;

    private String description;

    private MultipartFile previewImage;

    public MangaToCreateDTO convertToMangaToCreateDTO(ModelMapper modelMapper){
        return new MangaToCreateDTO();
    }
}
