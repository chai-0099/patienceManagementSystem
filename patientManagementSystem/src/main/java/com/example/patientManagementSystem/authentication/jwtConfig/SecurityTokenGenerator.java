package com.example.patienceManagementSystem.authentication.jwtConfig;


import com.example.patienceManagementSystem.authentication.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateToken(User user);
}