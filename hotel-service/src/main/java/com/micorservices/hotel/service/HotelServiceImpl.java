package com.micorservices.hotel.service;

import com.micorservices.hotel.entities.Hotel;
import com.micorservices.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setHotelId(randomId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findByHotelId(hotelId);
    }

    @Override
    public void deleteHotel(String hotelId) {

    }
}
