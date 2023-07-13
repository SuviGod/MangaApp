package ua.sulima.mangaapp.dto.manga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import ua.sulima.mangaapp.domain.Manga;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MangaToCreateDTO {
    private String name;

    private String alternativeNames;

    private Integer authorId;

    private Integer artistId;

    private Long groupId;

    private Short releaseYear;

    private String description;

    public Manga convertToEntity(ModelMapper modelMapper){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TypeMap<MangaToCreateDTO, Manga> propertyMapper =
                modelMapper.createTypeMap(MangaToCreateDTO.class, Manga.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Manga::setId));
        return modelMapper.map(this, Manga.class);
    }

}
