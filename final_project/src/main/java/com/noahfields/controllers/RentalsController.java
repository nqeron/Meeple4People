package com.noahfields.controllers;

import java.util.ArrayList;
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
import com.noahfields.Models.Picture;
import com.noahfields.Models.Rental;
import com.noahfields.services.CommentService;
import com.noahfields.services.GameService;
import com.noahfields.services.PictureService;
import com.noahfields.services.RentalsService;

@Controller
public class RentalsController {

	@Autowired
	RentalsService rentalsService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	PictureService pictureService;
	
	@GetMapping("/rentals")
	public String getRentals(Model m, HttpServletRequest request) {
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error", "You must be logged in to see your rentals");
			return "login";
		}
		
		Map<Game, Rental> gamesRented = rentalsService.getGamesRentedForCustomer(cust.getId());
		
		Map<Game, Picture> gamePictures = pictureService.getPicturesForGamesofSize(new ArrayList<Game>(gamesRented.keySet()), 2);
		
		m.addAttribute("gamesRented", gamesRented);
		m.addAttribute("gamePictures", gamePictures);
		
		return "rentals";
	}
	
	@PostMapping("/returnGame")
	public String returnGame(@RequestParam("rentalId") int rentalId, Model m, HttpServletRequest request) {
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error", "You must be logged in to return a rental");
			return "login";
		}
		
		Game game = rentalsService.getGameFromRental(rentalId);
		boolean returned = rentalsService.returnRental(rentalId);
		
		if(!returned) {
			m.addAttribute("error", "could not return game");
			return getRentals(m, request);
		}
		
		boolean hasCommented = commentService.customerHasCommentsForGame(cust.getId(), game.getId());
		if(!hasCommented) {
			Picture picture = pictureService.getPictureForGameofSize(game.getId(), 2);
			m.addAttribute("game", game);
			m.addAttribute("picture", picture);
			return "reviewGame";
		}
		
		return getRentals(m, request);
	}
	
	@PostMapping("/addReviewToGame")
	public String addReviewToGame(@RequestParam("gameId") int gameId, @RequestParam("ratingVal") double ratingVal, @RequestParam("commentText") String commentText, Model m, HttpServletRequest request) {
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error", "You must be logged in to leave a comment");
			return "login";
		}
		
		boolean addedReview = commentService.addReviewForCustomerToGame(cust.getId(),gameId,ratingVal,commentText);
		
		if(!addedReview) {
			m.addAttribute("error", "could not add review for customer");
			Game game = gameService.getGameByID(gameId);
			Picture picture = pictureService.getPictureForGameofSize(gameId, 2);
			m.addAttribute("game", game);
			m.addAttribute("picture", picture);
			return "reviewGame";
		}
		
		
		return getRentals(m, request);
	}
}
