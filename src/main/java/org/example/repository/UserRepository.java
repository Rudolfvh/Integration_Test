package org.example.repository;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private User user;

    public Optional<User> save(User user) {
        return Optional.of(this.user);
    }

}
