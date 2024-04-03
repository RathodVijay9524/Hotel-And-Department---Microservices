package com.vijay.hotelservice.client;

import com.vijay.hotelservice.entity.RatingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingFaginClientService {


    @GetMapping("/api/ratings/user/{userId}")
    public List<RatingResponse> getRatingsByUserId(@PathVariable String userId);
}
