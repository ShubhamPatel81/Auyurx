package com.auyurx.Service.ServiceImpl;

import com.auyurx.Entity.User;
import com.auyurx.Repository.UserRepository;
import com.auyurx.Service.EmailService;
import com.auyurx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.auyurx.Helpers.EmailVerificationLink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Set User Role
        user.setRole("USER");

        // Generate Token for email verification
        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);
        user.setEmailVerified(false);
        user.setEnabled(false);
        User saveUser = userRepository.save(user);
        String emailLink = EmailVerificationLink.getLinkForEmailVerification(emailToken);

        emailService.sendEmail(saveUser.getEmail(),"Verify using email from email account",emailLink );
        return  saveUser;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUser(User user) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public boolean isUserExistByUserEmail(String emailId) {
        return false;
    }

    @Override
    public boolean isUserExist(User userId) {
        return false;
    }

    @Override
    public boolean isUserExist(String userId) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }


}
