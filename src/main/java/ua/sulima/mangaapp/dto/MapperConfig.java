package ua.sulima.mangaapp.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.sulima.mangaapp.domain.Manga;
import ua.sulima.mangaapp.dto.manga.MangaToCreateDTO;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        TypeMap<MangaToCreateDTO, Manga> propertyMapper =
                modelMapper.createTypeMap(MangaToCreateDTO.class, Manga.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Manga::setId));
        return modelMapper;
    }
}
