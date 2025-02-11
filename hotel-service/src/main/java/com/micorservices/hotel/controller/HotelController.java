package com.micorservices.hotel.controller;

import com.micorservices.hotel.entities.Hotel;
import com.micorservices.hotel.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelServiceImpl hotelService;

    @Autowired
    public HotelController(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/")
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id){
        Hotel hotel = hotelService.getHotel(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> hotelList = hotelService.getAllHotels();
        return ResponseEntity.ok(hotelList);
    }



}
