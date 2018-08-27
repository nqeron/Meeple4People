package com.noahfields.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noahfields.Models.Customer;
import com.noahfields.Models.Game;
import com.noahfields.Models.Rental;
import com.noahfields.services.RentalsService;

@Controller
public class RentalsController {

	@Autowired
	RentalsService rentalsService;
	
	@GetMapping("/rentals")
	public String getRentals(Model m, HttpServletRequest request) {
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error", "You must be logged in to see your rentals");
			return "login";
		}
		
		Map<Game, Rental> gamesRented = rentalsService.getGamesRentedForCustomer(cust.getId());
		
		m.addAttribute("gamesRented", gamesRented);
		
		return "rentals";
	}
	
	@PostMapping("/returnGame")
	public String returnGame(@RequestParam("rentalId") int rentalId, Model m, HttpServletRequest request) {
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error", "You must be logged in to return a rental");
			return "login";
		}
		
		boolean returned = rentalsService.returnRental(rentalId);
		
		if(!returned) {
			m.addAttribute("error", "could not return game");
			return getRentals(m, request);
		}
		
		return getRentals(m, request);
	}
}
