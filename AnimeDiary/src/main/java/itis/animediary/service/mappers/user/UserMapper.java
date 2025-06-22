package itis.animediary.service.mappers.user;

import itis.animediary.data.entity.UserEntity;
import itis.animediary.service.dto.UserDto;
import itis.animediary.service.mappers.AuthorityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AuthorityMapper.class)
public interface UserMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "photoURL", target = "photoURL")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "isNonLocked", target = "isNonLocked")
    @Mapping(source = "authorities", target = "authorities")
    UserDto toDTO(UserEntity entity);
}
