package com.microservices.user_service.service.external;

import com.microservices.user_service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "RATING-SERVICE") // we can also provide the url here.
public interface RatingService {

    @GetMapping("/ratings/user/{userId}")
    List<Rating> getRatings(@PathVariable String userId);

    @PostMapping("/ratings")
    Rating createRating(Rating rating);
}
