package com.noahfields.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.noahfields.Models.Game;
import com.noahfields.Models.Picture;
import com.noahfields.services.OrdersService;
import com.noahfields.services.PictureService;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@Autowired
	PictureService pictureService;
	
	// getOrder generates a page for the given order, displaying the relevant games
	@GetMapping("/orders/{id}")
	public String getOrder(@PathVariable int id, Model m) {
		
		List<Game> games = ordersService.getGamesForOrder(id);
		
		Map<Game, Picture> gamePictures = pictureService.getPicturesForGamesofSize(games, 2);
		
		m.addAttribute("games", games);
		m.addAttribute("gamePictures", gamePictures);
		m.addAttribute("orderId", id);
		return "orderDetail";
	}
}
