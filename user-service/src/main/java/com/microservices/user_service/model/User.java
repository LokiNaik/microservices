package com.microservices.user_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;


@Entity
public class User {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public User(UUID id, String username, String email, String firstName, String lastName, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password, PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
}
