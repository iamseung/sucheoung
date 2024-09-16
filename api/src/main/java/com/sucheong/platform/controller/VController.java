package com.sucheong.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VController {
    @GetMapping("/home")
    public String hello(Model model) {
        model.addAttribute("data", "TEST");
        return "home";
    }
}
