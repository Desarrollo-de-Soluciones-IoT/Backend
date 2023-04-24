package com.docseeker.backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record User(
        String id,
        @NotEmpty
        UserType userType,
        @Email
        String email,
        String password,
        String dni,
        int age
) {
}
