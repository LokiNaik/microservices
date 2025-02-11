package com.microservices.rating.controllers;

import com.microservices.rating.entities.Rating;
import com.microservices.rating.services.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingServiceImpl ratingService;

    @Autowired
    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        if (rating != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Rating());
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getById(@PathVariable String ratingId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getByRatingId(ratingId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
