package com.auyurx.Service;

import com.auyurx.Entity.User;
import com.auyurx.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;


public interface UserService {

    User saveUser(User user);
    User getUserByEmail(String email);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);

    void deleteUser(String id);
    boolean isUserExistByUserEmail(String emailId);
    boolean isUserExist(User userId);

    boolean isUserExist(String userId);
    List<User> getAllUsers(); // Corrected return type

//    User getUserByEmail(String email);
}
