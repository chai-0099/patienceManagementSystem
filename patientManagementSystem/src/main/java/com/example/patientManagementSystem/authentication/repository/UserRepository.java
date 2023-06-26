package com.example.patientManagementSystem.authentication.repository;

import com.example.patientManagementSystem.authentication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailId(String emailId);

    User findByEmailIdAndPassword(String emailId, String password);
}