package com.example.patientManagementSystem.authentication.service;

import com.example.patientManagementSystem.authentication.exception.UserAlreadyExistsException;
import com.example.patientManagementSystem.authentication.exception.UserNotFoundException;
import com.example.patientManagementSystem.authentication.model.User;

import java.util.List;

public interface UserService {
    User register(User user) throws UserAlreadyExistsException;
    User login(User user) throws UserNotFoundException;

    List<User> getAllUser();

}