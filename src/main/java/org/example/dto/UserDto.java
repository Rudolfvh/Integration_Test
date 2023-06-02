package org.example.dto;

import java.time.LocalDate;

public record UserDto (
        String name,
        String lastName,
        LocalDate birthday,
        String login) {
}
