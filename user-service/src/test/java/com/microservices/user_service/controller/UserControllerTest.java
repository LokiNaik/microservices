package com.microservices.user_service.controller;


import com.microservices.user_service.entities.User;
import com.microservices.user_service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    UserController userController;

    @Test
    public void testSaveUserSuccessful() {
        // init
        User user = new User();
        user.setName("testName");
        user.setEmail("testEmail");
        user.setUserId(UUID.randomUUID().toString());
        user.setPhone("999999999");
        user.setAbout("testAbout");
        user.setRatings(new ArrayList<>());

        // mocking service call
        when(userService.saveUser(user)).thenReturn(user);

        // call controller method.
        ResponseEntity<User> userResponseEntity = userController.saveUser(user);

        // Assertions
        assertNotNull(userResponseEntity.getBody());
        assertEquals(userResponseEntity.getBody().getUserId(), user.getUserId());
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // mock the service call
        when(userService.getUser(anyString())).thenReturn(user);

        ResponseEntity<User> userResponseEntity = userController.getUser(userId);

        assertNotNull(userResponseEntity.getBody());
        assertEquals(userResponseEntity.getBody().getUserId(), userId);

        verify(userService, times(1)).getUser(anyString());
    }

    @Test
    void getAllUsers() {
        List<User> usersList = new ArrayList<>();

        // mock service call
        when(userService.getAllUsers()).thenReturn(usersList);

        // call controller method
        ResponseEntity<List<User>> allUsers = userController.getAllUsers();
        assertNotNull(allUsers.getBody());
        verify(userService, times(1)).getAllUsers();
    }
}
