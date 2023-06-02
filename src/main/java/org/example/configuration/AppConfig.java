package org.example.configuration;

import org.example.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@ComponentScan(basePackages = "org.example.*")
public class AppConfig {

    @Bean
    public User userBean() {
        return User.builder()
                .id(1L)
                .name("Matvey")
                .lastName("Klop")
                .birthday(LocalDate.of(2005, 6, 16))
                .login("matyane@gmail.com")
                .password("1234567891456987")
                .build();
    }

}