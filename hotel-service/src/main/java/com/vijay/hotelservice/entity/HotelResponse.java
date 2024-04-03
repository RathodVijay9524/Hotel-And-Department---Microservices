package com.vijay.hotelservice.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {

    private  String id;
    private String userId;
    private  String name;
    private  String location;
    private  String about;
    private UserResponse userResponse;

}
