package com.chandu.CustomerCreateins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chandu.CustomerCreateins.model.User;
import com.chandu.CustomerCreateins.service.UserService;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

  
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/api/auth/login"; 
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

  
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User loginRequest, Model model) {
        try {
            User user = userService.loginUser(
                loginRequest.getUsername(), 
                loginRequest.getPassword(), null
            );
            return "redirect:/index"; 
        } catch (Exception e) {
            model.addAttribute("error", "Login failed: " + e.getMessage());
            return "index"; 
        }
    }
}
