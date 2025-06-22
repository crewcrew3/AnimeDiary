package itis.animediary.service.mappers.user;

import itis.animediary.service.dto.UserDto;
import itis.animediary.data.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserListMapper {
    List<UserDto> toDtoList(List<UserEntity> entities);
}
