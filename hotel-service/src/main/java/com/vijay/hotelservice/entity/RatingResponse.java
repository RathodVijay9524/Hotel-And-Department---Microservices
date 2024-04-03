package com.vijay.hotelservice.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponse {
    private String ratingId;
    private String userId;
    private  int rating;
    private  String feedback;


}