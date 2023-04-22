package com.docseeker.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UserTest {
    @Test
    void create_new_user_patient() {
        User user = new User(
                UUID.randomUUID().toString(),
                "patient",
                "patient_test@gmail.com",
                "testpassword",
                "75730173",
                23
        );

        Assertions.assertNotNull(user);
        Assertions.assertEquals("patient", user.getUserType());
    }
}
