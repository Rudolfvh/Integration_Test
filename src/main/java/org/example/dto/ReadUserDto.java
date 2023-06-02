package org.example.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserDto {

    @NotNull(message = "name can't be null")
    @Size(min = 4, message = "name at least 4 letters")
    private String name;

    @NotNull()
    @Size(min = 4, message = "lastName at least 4 letters")
    private String lastName;

    @NotNull(message = "birthday can't be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "birthday can't be before current date")
    private LocalDate birthday;

    @NotBlank(message = "login can't be empty")
    @Email(message = "login is not valid.. example@gamil.com")
    private String login;

    @NotNull(message = "password can't be null")
    @Size(min = 16)
    private String password;

}
