package com.chandu.CustomerCreateins.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    
    @GetMapping("/news-detail")
    public String showNewsDetail() {
        return "news-detail"; // Assuming it's in /src/main/resources/templates
    }
}
