package com.microservices.user_service.service.external;

import com.microservices.user_service.entities.Rating;
import com.microservices.user_service.entities.constants.AppConstants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "RATING-SERVICE",fallback = RatingServiceFallback.class) // we can also provide the url here.
public interface RatingService {

    Logger logger = LoggerFactory.getLogger(HotelService.class);

    @GetMapping("/ratings/user/{userId}")
    @CircuitBreaker(name = AppConstants.RATING_SERVICE.RATING_SERVICE, fallbackMethod = AppConstants.RATING_SERVICE.FALLBACK_RATING_SERVICE)
    List<Rating> getRatings(@PathVariable String userId);

    @PostMapping("/ratings")
    Rating createRating(Rating rating);

    /**
     * Used for fallback when service is down.
     * @param userId
     * @param ex
     * @return
     */
    default List<Rating> fallBackForRatingService(String userId, Throwable ex) {
        logger.error("Circuit Breaker: Rating Service Down! Returning empty ratings");
        return Collections.emptyList();
    }
}
