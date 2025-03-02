package com.microservices.user_service.service.impl;

import com.microservices.user_service.entities.Hotel;
import com.microservices.user_service.entities.Rating;
import com.microservices.user_service.entities.User;
import com.microservices.user_service.exception.ResourceNotFoundException;
import com.microservices.user_service.repository.UserRepository;
import com.microservices.user_service.service.UserServices;
import com.microservices.user_service.service.external.HotelService;
import com.microservices.user_service.service.external.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserServices {
    private final UserRepository repository;
    private final HotelService hotelService;
    private final RatingService ratingService;

    @Autowired
    public UserServiceImpl(UserRepository repository, RestTemplate restTemplate,
                           HotelService hotelService, RatingService ratingService) {
        this.repository = repository;
        this.hotelService = hotelService;
        this.ratingService = ratingService;
    }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // FETCH RATINGS ALONG WITH USER.
        return repository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        repository.deleteById(userId);
    }

    @Override
    public User getUser(String userId) {
        // fetching user from userDb
        User user = repository.findByUserId(userId);
        if (user == null) {
            throw new ResourceNotFoundException("No user found with the user id : " + userId);
        }
        //fetch userRatings from Rating Service.
        List<Rating> ratingList = getRatingList(userId);
        user.setRatings(ratingList);
        return user;
    }

    private List<Rating> getRatingList(String userId) {
        List<Rating> ratings = ratingService.getRatings(userId);
        log.info("Getting ratings for the user : {}", ratings);
        if (!ratings.isEmpty()) {
            ratings.forEach(rating -> {
                if (!rating.getHotelId().isEmpty()) {
                    Hotel hotel = getHotel(rating.getHotelId());
                    rating.setHotel(hotel);
                }
            });
        }
        return ratings;
    }

    private Hotel getHotel(String hotelId) {
        Hotel hotel = hotelService.getHotel(hotelId);
        log.info("Fetched Hotel: {}", hotel);
        return hotel;
    }
}
