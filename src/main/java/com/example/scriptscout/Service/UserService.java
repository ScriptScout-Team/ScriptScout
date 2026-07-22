package com.example.scriptscout.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scriptscout.models.User;
import com.example.scriptscout.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public String signup(User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            return "Email already exists";
        }

        userRepository.save(user);
        return "Signup Successful";
    }
    public User login(User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null &&
                existingUser.getPassword().equals(user.getPassword())) {

            return existingUser;
        }

        return null;
    }
    public String forgotPassword(String email, String newPassword) {

        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null) {

            existingUser.setPassword(newPassword);
            userRepository.save(existingUser);

            return "Password Updated Successfully";
        }

        return "User Not Found";
    }
    public String resetPassword(String email, String newPassword, String confirmPassword) {

        User existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {
            return "User Not Found";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "Passwords do not match";
        }

        existingUser.setPassword(newPassword);
        userRepository.save(existingUser);

        return "Password Reset Successfully";
    }

}