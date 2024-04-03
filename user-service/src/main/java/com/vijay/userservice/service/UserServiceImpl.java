package com.vijay.userservice.service;

import com.vijay.userservice.entity.Hotel;
import com.vijay.userservice.entity.Rating;
import com.vijay.userservice.entity.User;
import com.vijay.userservice.exceptions.ResourceNotFoundException;
import com.vijay.userservice.external.client.HotelService;
import com.vijay.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;



    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        return user;
    }

    //get single user
    @Override
    public User getUser(String userId) {
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch rating of the above  user from RATING SERVICE


        Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8082/api/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8081/api/hotels/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel= forEntity.getBody();
            logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}



/*
// using faginClient

@FeignClient(name = "rating-service", url = "http://localhost:8082")
public interface RatingClient {

    @GetMapping("/api/ratings/users/{userId}")
    ResponseEntity<Rating[]> getRatingsForUser(@PathVariable("userId") String userId);
}

@FeignClient(name = "hotel-service", url = "http://localhost:8081")
public interface HotelClient {

    @GetMapping("/api/hotels/{hotelId}")
    ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") String hotelId);
}

 private final UserRepository userRepository;
    private final RatingClient ratingClient;
    private final HotelClient hotelClient;

    @Autowired
    public UserService(UserRepository userRepository, RatingClient ratingClient, HotelClient hotelClient) {
        this.userRepository = userRepository;
        this.ratingClient = ratingClient;
        this.hotelClient = hotelClient;
    }

    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        ResponseEntity<Rating[]> ratingsResponse = ratingClient.getRatingsForUser(userId);
        Rating[] ratingsOfUser = ratingsResponse.getBody();
        List<Rating> ratings = Arrays.asList(ratingsOfUser);

        List<Rating> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> hotelEntity = hotelClient.getHotelById(rating.getHotelId());
            Hotel hotel = hotelEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

 */