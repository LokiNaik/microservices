package com.microservices.user_service.service.impl;

import com.microservices.user_service.entities.Hotel;
import com.microservices.user_service.entities.Rating;
import com.microservices.user_service.entities.User;
import com.microservices.user_service.entities.constants.AppConstants;
import com.microservices.user_service.exception.ResourceNotFoundException;
import com.microservices.user_service.repository.UserRepository;
import com.microservices.user_service.service.UserServices;
import com.microservices.user_service.service.external.HotelService;
import com.microservices.user_service.service.external.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserServices {
    private final UserRepository repository;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;
    private final RatingService ratingService;

    @Autowired
    public UserServiceImpl(UserRepository repository, RestTemplate restTemplate,
                           HotelService hotelService, RatingService ratingService) {
        this.repository = repository;
        this.restTemplate = restTemplate;
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
    public User getUser(String userId) {
        // fetching user from userDb
        User user = repository.findByUserId(userId);
        if (user == null) {
            throw new ResourceNotFoundException("No user found with the user id : " + userId);
        }
        //fetch userRatings from Rating Service.
        List<Rating> ratingList = ratingService.getRatings(user.getUserId());
        log.info("Getting ratings for the user : {}", ratingList);
        user.setRatings(ratingList);

        ratingList.stream().map(rating -> {
            //Fetch the hotel details.
            Hotel hotel = hotelService.getHotel(rating.getHotelId());   // restTemplate.getForObject(AppConstants.HOTEL_SERVICE.GET_HOTEL_SERVICE_URL + rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        return user;
    }

    @Override
    public void deleteUser(String user) {

    }
}
