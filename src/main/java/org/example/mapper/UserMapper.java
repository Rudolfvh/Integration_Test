package org.example.mapper;

import org.example.dto.ReadUserDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    User toUser(ReadUserDto readUserDto);
    UserDto toUserDto(User user);
    ReadUserDto toReadUserDto(User user);

}
