package com.vijay.hotelservice.service;

import com.vijay.hotelservice.entity.Hotel;
import com.vijay.hotelservice.entity.HotelResponse;

import java.util.List;

public interface HotelService {

    //create

    Hotel create(Hotel hotel);
    Hotel getHotel(String id);

    //get all
    List<Hotel> getAll();

    //get single
    HotelResponse get(String id);

}
