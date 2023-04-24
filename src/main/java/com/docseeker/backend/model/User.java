package com.docseeker.backend.model;

public record User(
        String id,
        UserType userType,
        String email,
        String password,
        String dni,
        int age
) {
}
