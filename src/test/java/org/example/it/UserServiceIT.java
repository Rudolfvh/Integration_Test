package org.example.it;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.example.App;
import org.example.dto.ReadUserDto;
import org.example.service.UserService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = App.class)
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserServiceIT {

    private final UserService userService;
    private ReadUserDto readUserDto;

    @BeforeEach
    void setUp(@Qualifier("readUserDto") ReadUserDto readUserDto) {
        this.readUserDto = readUserDto;
    }

    @Test
    void save() {
        var optionalUserDto = userService.save(readUserDto);
        assertThat(optionalUserDto).isPresent();
        optionalUserDto.ifPresent(userDto -> {
            assertAll(() -> {
                assertThat(userDto.name()).isEqualTo(readUserDto.getName());
                assertThat(userDto.lastName()).isEqualTo(readUserDto.getLastName());
                assertThat(userDto.birthday()).isEqualTo(readUserDto.getBirthday());
                assertThat(userDto.login()).isEqualTo(readUserDto.getLogin());
            });
        });
    }

    @Test
    void nameValidationTest() {
        readUserDto.setName("");
        ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        assertThat(exception.getMessage()).contains("name at least 4 letters");
        readUserDto.setName("Matvey");
        assertThat(userService.save(readUserDto)).isPresent();
    }

    @Test
    void lastNameValidationTest() {
        readUserDto.setLastName("");
        ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        assertThat(exception.getMessage()).contains("lastName at least 4 letters");
        readUserDto.setLastName("Klop");
        assertThat(userService.save(readUserDto)).isPresent();
    }

    @Test
    void birthdayValidationTest() {
        readUserDto.setBirthday(null);
        ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        assertThat(exception.getMessage()).contains("birthday can't be null");
        readUserDto.setBirthday(LocalDate.now());
        exception = assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        assertThat(exception.getMessage()).contains("birthday can't be before current date");
        readUserDto.setBirthday(LocalDate.of(2005, 6, 16));
        assertThat(userService.save(readUserDto)).isPresent();
    }

    @Test
    void loginValidationTest() {
        readUserDto.setLogin("");
        ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        assertThat(exception.getMessage()).contains("login can't be empty");
        readUserDto.setLogin("Maks");
        exception = assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        assertThat(exception.getMessage()).contains("login is not valid");
        readUserDto.setLogin("example@gmail.com");
        assertThat(userService.save(readUserDto)).isPresent();
    }

    @Test
    void passwordValidationTest() {
        readUserDto.setPassword(null);
        ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        assertThat(exception.getMessage()).contains("password can't be null");
        readUserDto.setPassword("21345d");
        assertThrows(ConstraintViolationException.class, () -> userService.save(readUserDto));
        readUserDto.setPassword("1234545aqrqcvfyr");
        assertThat(userService.save(readUserDto)).isPresent();
    }

}
