package com.micorservices.hotel.repository;

import com.micorservices.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
    Hotel findByHotelId(String hotelId);
}
