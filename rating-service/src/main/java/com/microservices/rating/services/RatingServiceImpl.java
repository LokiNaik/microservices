package com.microservices.rating.services;

import com.microservices.rating.entities.Rating;
import com.microservices.rating.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{

    private final RatingRepo repo;

    @Autowired
    public RatingServiceImpl(RatingRepo repo){
        this.repo = repo;
    }
    @Override
    public Rating create(Rating rating) {
        return repo.save(rating);
    }

    @Override
    public Rating getByRatingId(String ratingId) {
        Optional<Rating> byId = repo.findById(ratingId);
        return byId.get();
    }

    @Override
    public List<Rating> getRatings() {
        return repo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repo.findByHotelId(hotelId);
    }
}
