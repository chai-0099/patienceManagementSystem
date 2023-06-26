package com.example.patientManagementSystem.authentication.jwtConfig;

import com.example.patientManagementSystem.authentication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Override
    public Map<String, String> generateToken(User user) {
        Map<String, String> map = new HashMap<>();
        long currentTimeInMilli = System.currentTimeMillis();
        String jwtToken = Jwts.builder()
                .setSubject(user.getEmailId())
                .setIssuedAt(new Date(currentTimeInMilli))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        map.put("token", jwtToken);
        map.put("message", "User successfully logged in");
        map.put("emailId", user.getEmailId());
        return map;
    }


}