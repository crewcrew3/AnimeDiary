package itis.animediary.service.mappers;

import itis.animediary.data.entity.AuthorityEntity;
import itis.animediary.service.dto.AuthorityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    AuthorityDto toDTO(AuthorityEntity entity);
}
