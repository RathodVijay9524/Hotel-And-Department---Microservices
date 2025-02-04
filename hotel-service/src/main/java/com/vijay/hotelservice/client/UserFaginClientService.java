package com.vijay.hotelservice.client;

import com.vijay.hotelservice.entity.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserFaginClientService {

    @GetMapping("/api/users/single/{userId}")
    public UserResponse getSingleUserById(@PathVariable String userId);
}