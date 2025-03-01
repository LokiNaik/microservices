package com.microservices.user_service.service;

import com.microservices.user_service.entities.Hotel;
import com.microservices.user_service.entities.Rating;
import com.microservices.user_service.entities.User;
import com.microservices.user_service.exception.ResourceNotFoundException;
import com.microservices.user_service.repository.UserRepository;
import com.microservices.user_service.service.external.HotelService;
import com.microservices.user_service.service.external.RatingService;
import com.microservices.user_service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    HotelService hotelService;

    @Mock
    RatingService ratingService;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.openMocks(this);
    }

    public User getUser() {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName("testName");
        user.setPhone("9999999999");
        user.setEmail("test@email.com");
        user.setAbout("testAbout");
        return user;
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName("testName");
        user.setPhone("9999999999");
        user.setEmail("test@email.com");
        user.setAbout("testAbout");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User user1 = userService.saveUser(user);

        assertNotNull(user1);
        assertEquals(user.getPhone(), user1.getPhone());

        verify(userRepository).save(user);
        assertEquals(user.getPhone(), user1.getPhone());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(UUID.randomUUID().toString(), "anyString()", "test@test.com", "testAbout", "999999999", null));
        when(userRepository.findAll()).thenReturn(userList);
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserByID() {
        User user = getUser();
        List<Rating> ratingList = new ArrayList<>();
        Hotel hotel = new Hotel("", "", "", "");
        ratingList.add(new Rating("", "", "", 5, "", hotel));

        when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
        when(ratingService.getRatings(anyString())).thenReturn(ratingList);
        when(hotelService.getHotel(anyString())).thenReturn(hotel);

        User userFetched = userService.getUser(user.getUserId());

        assertEquals(user.getUserId(),userFetched.getUserId());
        assertNotNull(ratingList);
        assertNotNull(hotel);
        verify(userRepository, times(1)).findByUserId(anyString());
    }

    @Test
    public void testUserNotFound() {
        String userId = UUID.randomUUID().toString();
        when(userRepository.findByUserId(userId)).thenReturn(null);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUser(userId);
        });
        verify(userRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void deleteUserByUserId() {

        doNothing().when(userRepository).deleteById(anyString());
        userService.deleteUser(anyString());

        verify(userRepository, times(1)).deleteById(anyString());
    }

}
