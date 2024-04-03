package com.vijay.hotelservice.service;


import com.vijay.hotelservice.client.RatingFaginClientService;
import com.vijay.hotelservice.client.UserFaginClientService;
import com.vijay.hotelservice.entity.Hotel;
import com.vijay.hotelservice.entity.HotelResponse;
import com.vijay.hotelservice.entity.RatingResponse;
import com.vijay.hotelservice.entity.UserResponse;
import com.vijay.hotelservice.repository.HotelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RatingFaginClientService ratingFaginClientService;
    @Autowired
    private UserFaginClientService userFaginClientService;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel savedhotel = hotelRepository.save(hotel);
        return savedhotel;
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> all = hotelRepository.findAll();
        return all;
    }
    @Override
    public Hotel getHotel(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("hotel with given id not found"));
        return hotel;
    }
        @Override
        public HotelResponse get(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("hotel with given id not found"));

        UserResponse singleUser= userFaginClientService.getSingleUserById(hotel.getUserId());
        List<RatingResponse> ratingsByUserId= ratingFaginClientService.getRatingsByUserId(singleUser.getUserId());
        singleUser.setRatings(ratingsByUserId);
        HotelResponse hotelResponse=new HotelResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setLocation(hotel.getLocation());
        hotelResponse.setName(hotel.getName());
        hotelResponse.setAbout(hotel.getAbout());
        BeanUtils.copyProperties(singleUser,hotelResponse);
        hotelResponse.setUserResponse(singleUser);
        return hotelResponse;
    }



}

