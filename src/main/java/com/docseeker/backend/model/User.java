package com.docseeker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        @Column(nullable = true)
        private UserType userType;
        @Column(unique = true)
        private String email;
        private String password;
        @Column(unique = true)
        private String dni;
        @Column(nullable = true)
        private int age;
}
