package com.example.patientManagementSystem.authentication.jwtConfig;


import com.example.patientManagementSystem.authentication.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateToken(User user);
}