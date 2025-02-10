package com.micorservices.hotel.service;

import com.micorservices.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String hotelId);

    void deleteHotel(String hotelId);

}
