package com.example.scriptscout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scriptscout.Service.UserService;
import com.example.scriptscout.models.User;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return userService.signup(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }
    @PutMapping("/forgotpassword")
    public String forgotPassword(@RequestBody User user) {

        return userService.forgotPassword(
                user.getEmail(),
                user.getPassword()
        );
    }
    @PutMapping("/resetpassword")
    public String resetPassword(@RequestBody User user) {

        return userService.resetPassword(
                user.getEmail(),
                user.getPassword(),
                user.getConfirmPassword()
        );
    }
        
    }
    

