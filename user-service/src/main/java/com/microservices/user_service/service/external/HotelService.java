package com.microservices.user_service.service.external;

import com.microservices.user_service.entities.Hotel;
import com.microservices.user_service.entities.constants.AppConstants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "HOTEL-SERVICE" , fallback = HotelServiceFallBack.class)
public interface HotelService {

    Logger logger = LoggerFactory.getLogger(HotelService.class);

    @GetMapping("/hotel/{hotelId}")
    @CircuitBreaker(name = AppConstants.HOTEL_SERVICE.HOTEL_SERVICE,
            fallbackMethod = AppConstants.HOTEL_SERVICE.HOTEL_SERVICE_FALLBACK)
    Hotel getHotel(@PathVariable String hotelId);

    default Hotel fallBackForHotelService(String hotelId, Throwable ex) {
        logger.error("Circuit Breaker: HotelService is down");
        return new Hotel();
    }
}
