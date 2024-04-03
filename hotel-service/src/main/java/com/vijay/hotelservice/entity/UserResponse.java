package com.vijay.hotelservice.entity;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userId;
    private String name;
    private String email;
    private String about;
    private List<RatingResponse> ratings = new ArrayList<>();

}