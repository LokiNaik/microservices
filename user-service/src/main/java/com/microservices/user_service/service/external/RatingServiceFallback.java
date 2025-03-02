package com.microservices.user_service.service.external;

import com.microservices.user_service.entities.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class RatingServiceFallback implements RatingService{
    @Override
    public List<Rating> getRatings(String userId) {
        log.error("Fallback triggered: RATING-SERVICE is down! Returning empty ratings.");
        return Collections.emptyList();
    }

    @Override
    public Rating createRating(Rating rating) {
        return null;
    }
}
