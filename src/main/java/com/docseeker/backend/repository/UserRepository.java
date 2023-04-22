package com.docseeker.backend.repository;

import com.docseeker.backend.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

    List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(
                UUID.randomUUID().toString(),
                "patient",
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
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User create(User user) {
        users.add(user);
        return user;
    }

    public void update(User user, String id) {
        User userToUpdate = findById(id);
        int i = users.indexOf(userToUpdate);
        users.set(i, user);
    }

    public void delete(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
