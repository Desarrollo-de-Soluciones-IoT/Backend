package com.docseeker.backend.repository;

import com.docseeker.backend.model.User;
import com.docseeker.backend.model.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(
                UUID.randomUUID().toString(),
                UserType.PATIENT,
                "jaemin@gmail.com",
                "jaeminpatient",
                "73147180",
                23
        ));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(String id) {
        return users.stream().filter(user -> user.id().equals(id)).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
    }

    public void save(User user) {
        users.removeIf(u -> u.id().equals(user.id()));
        users.add(user);
    }

    public void update(User user, String id) {
        User userToUpdate = findById(id);
        int i = users.indexOf(userToUpdate);
        users.set(i, user);
    }

    public void delete(String id) {
        users.removeIf(user -> user.id().equals(id));
    }

    public boolean existsById(String id) {
        return users.stream().filter(user -> user.id().equals(id)).count() == 1;
    }
}
