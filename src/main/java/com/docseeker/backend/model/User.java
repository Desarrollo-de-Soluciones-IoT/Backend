package com.docseeker.backend.model;

public class User {
    private String id;
    private String userType;
    private String email;
    private String password;
    private String dni;
    private int age;

    public User(String id, String userType, String email, String password, String dni, int age) {
        this.id = id;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
