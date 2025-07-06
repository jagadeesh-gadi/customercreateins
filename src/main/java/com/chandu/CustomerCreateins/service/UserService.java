package com.chandu.CustomerCreateins.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.chandu.CustomerCreateins.model.User;
import com.chandu.CustomerCreateins.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private JavaMailSender mailSender;

	    public User registerUser(User user) throws MessagingException {
	       
	        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
	        if (existingUser.isPresent()) {
	            throw new RuntimeException("Email is already registered.");
	        }

	        
	        User savedUser = userRepository.save(user);

	       
	        sendRegistrationEmail(user.getEmail());

	        return savedUser;
	    }

	    public User loginUser(String email, String password, String role) {
	        return userRepository.findByEmail(email)
	                .filter(user -> user.getPassword().equals(password) && user.getRole().equalsIgnoreCase(role))
	                .orElseThrow(() -> new RuntimeException("Invalid credentials or role"));
	    }

	    private void sendRegistrationEmail(String email) throws MessagingException {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setTo(email);
	        helper.setSubject("Registration Successful");
	        helper.setText("Welcome to the hospital management system! Your registration was successful.");
	        mailSender.send(message);
	    }
}