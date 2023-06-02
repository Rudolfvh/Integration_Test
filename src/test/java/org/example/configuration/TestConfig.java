package org.example.configuration;

import org.example.dto.ReadUserDto;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@TestConfiguration
public class TestConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ReadUserDto readUserDto(UserMapper userMapper, User user) {
        return userMapper.toReadUserDto(user);
    }

}