package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AllController {

   @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Ivan Karpenko"); // Example name, you can modify it dynamically
        model.addAttribute("currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "index"; // This corresponds to "index.html"
    }
}
