package org.example.service;



import org.example.dto.ReadUserDto;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserDto> save(@Valid ReadUserDto readUserDto) {
        return userRepository.save(userMapper.toUser(readUserDto))
                .map(userMapper::toUserDto);
    }


}
