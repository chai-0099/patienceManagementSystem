package com.example.patienceManagementSystem.authentication.service;

import com.example.patienceManagementSystem.authentication.exception.UserAlreadyExistsException;
import com.example.patienceManagementSystem.authentication.exception.UserNotFoundException;
import com.example.patienceManagementSystem.authentication.model.User;

import java.util.List;

public interface UserService {
    User register(User user) throws UserAlreadyExistsException;
    User login(User user) throws UserNotFoundException;

    List<User> getAllUser();

}