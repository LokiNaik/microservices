package com.microservices.user_service.service;

import com.microservices.user_service.entities.User;

import java.util.List;

public interface UserServices {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
    void deleteUser(String user);
}
