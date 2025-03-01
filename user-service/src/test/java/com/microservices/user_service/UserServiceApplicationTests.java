package com.microservices.user_service;

import com.microservices.user_service.entities.Rating;
import com.microservices.user_service.service.external.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	private final RatingService ratingService;

	@Autowired
    UserServiceApplicationTests(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Test
	void contextLoads() {
	}
}
