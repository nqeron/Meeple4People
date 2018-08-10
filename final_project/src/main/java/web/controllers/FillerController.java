package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FillerController {

	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}
}
