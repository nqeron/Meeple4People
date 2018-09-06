package com.noahfields.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FillerController {

	//about sends the user to the about page
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	//faq sends the user to the faq page
	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}
}
