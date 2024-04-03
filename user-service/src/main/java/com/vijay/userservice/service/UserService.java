package com.vijay.userservice.service;


import com.vijay.userservice.entity.User;

import java.util.List;

public interface UserService {

    //user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    public User getUserById(String userId);

    //get single user of given userId

    User getUser(String userId);

    //TODO: delete
    //TODO: update


}