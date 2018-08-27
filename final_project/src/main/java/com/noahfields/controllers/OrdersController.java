package com.noahfields.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.noahfields.Models.Game;
import com.noahfields.services.OrdersService;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@GetMapping("/orders/{id}")
	public String getOrder(@PathVariable int id, Model m) {
		
		List<Game> games = ordersService.getGamesForOrder(id);
		
		m.addAttribute("games", games);
		m.addAttribute("orderId", id);
		return "orderDetail";
	}
}
