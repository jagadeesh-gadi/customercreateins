package com.chandu.CustomerCreateins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chandu.CustomerCreateins.model.Contact;
import com.chandu.CustomerCreateins.repository.ContactRepository;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContactForm(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("message") String message,
            Model model) {

        // Create a new Contact object
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMessage(message);

        // Save the contact to the database
        contactRepository.save(contact);

        // Add a success message to the model
        model.addAttribute("successMessage", "Thank you for your message. We will get back to you shortly.");
        return "contact";
    }
}
