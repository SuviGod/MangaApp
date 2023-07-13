package ua.sulima.mangaapp.dto.group;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.sulima.mangaapp.domain.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(GroupToCreateDTO dto, @MappingTarget Group entity);
}
