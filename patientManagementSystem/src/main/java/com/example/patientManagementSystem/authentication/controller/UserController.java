package com.example.patienceManagementSystem.authentication.controller;

import com.example.patienceManagementSystem.authentication.exception.UserAlreadyExistsException;
import com.example.patienceManagementSystem.authentication.exception.UserNotFoundException;
import com.example.patienceManagementSystem.authentication.jwtConfig.SecurityTokenGenerator;
import com.example.patienceManagementSystem.authentication.model.User;
import com.example.patienceManagementSystem.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService , SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserNotFoundException, UserAlreadyExistsException {
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody User user) throws UserNotFoundException {

        User user1 = userService.login(user);

        if (user1 == null) {
            return new ResponseEntity<>("login faild", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(securityTokenGenerator.generateToken(user1), HttpStatus.OK);

    }

    //write a controller method to get all users get the get all user metheod from service
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() throws UserNotFoundException {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
}
