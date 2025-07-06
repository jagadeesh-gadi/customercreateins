package com.chandu.CustomerCreateins.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/"})
    public String home() {
        return "index2"; // Returns index2.html located in the templates folder
    }

    @GetMapping({"/home"})
    public String homePage() {
        return "index"; // Returns index.html located in the templates folder
    }
}
