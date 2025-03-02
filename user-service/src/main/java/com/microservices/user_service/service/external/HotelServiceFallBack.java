package com.microservices.user_service.service.external;

import com.microservices.user_service.entities.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HotelServiceFallBack implements HotelService{
    @Override
    public Hotel getHotel(String hotelId) {
        log.error("Feign client: Hotel Service is down!");
        return new Hotel();
    }
}
