package com.noahfields.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	// welcome: easter egg - a test page
	@GetMapping("/welcome")
	public String welcome(Model m) {
		
		m.addAttribute("error", "testing");
		return "welcome";
	}
}
