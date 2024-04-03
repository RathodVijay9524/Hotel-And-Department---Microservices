package com.vijay.hotelservice.controller;


import com.vijay.hotelservice.entity.Hotel;
import com.vijay.hotelservice.entity.HotelResponse;
import com.vijay.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }


    //get single
   // @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> ByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<Hotel> ByHotelIddd(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
    }


    //get all
   // @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelService.getAll());
    }


}
