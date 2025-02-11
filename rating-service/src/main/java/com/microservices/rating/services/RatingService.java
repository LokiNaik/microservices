package com.microservices.rating.services;

import com.microservices.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    Rating getByRatingId(String ratingId);
    List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
