package com.workflow.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
    public String test(Model model) {
        model.addAttribute("msg", "Work-flow demo");
        return "test";
    }
}
