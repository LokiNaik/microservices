package com.microservices.rating.repository;

import com.microservices.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepo extends MongoRepository<Rating, String> {

    // custom finder methods.
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
