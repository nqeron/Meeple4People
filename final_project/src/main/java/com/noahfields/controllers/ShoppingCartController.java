package com.noahfields.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.noahfields.Models.Customer;
import com.noahfields.Models.Game;
import com.noahfields.Models.Picture;
import com.noahfields.Models.StockItem;
import com.noahfields.services.PictureService;
import com.noahfields.services.ShoppingCartService;

@Controller
public class ShoppingCartController {

	public static final double PRICEPERGAME = 5.00;
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	PictureService pictureService;
	
	@PostMapping("addGameToShoppingCart")
	public RedirectView addGameToShoppingCart(@RequestParam("gameId") int gameId, Model m, HttpServletRequest request, RedirectAttributes redir) {
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","You must login to add an item to your cart!");
			return new RedirectView("/login");
		}
		
		StockItem item = shoppingCartService.getNextAvailableStockItemForGame(gameId);
		
		if(item == null || item.equals(null)) {
			redir.addFlashAttribute("error","Item is out of stock");
			return new RedirectView(request.getHeader("Referer"));
		}
		
		int added = shoppingCartService.addItemToShoppingCartForCustomer(item, cust);
		if(added < 0) {
			if(added == shoppingCartService.COUlDNOTBEADDED) {
				m.addAttribute("error", "could not add item to shopping cart!");
				redir.addFlashAttribute("error", "could not add item to shopping cart!");
			} else if(added == shoppingCartService.EXCEEDMAXNUMGAMES) {
				m.addAttribute("error", "You've exceeded the maximum limit of games allowed");
				redir.addFlashAttribute("error", "You've exceeded the maximum limit of games allowed");
			} else if(added == shoppingCartService.CUSTOMERISSUSPENDED) {
				redir.addFlashAttribute("error", "You can not add a game while suspended. To get your status revoked please contact a customer representative.");
			}
			RedirectView review = new RedirectView(request.getHeader("Referer"));
			return review;
		}
		
		return new RedirectView("/shoppingCart");
	}
	
	@GetMapping("/shoppingCart")
	public String getShoppingCart(Model m, HttpServletRequest request) {
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","You must login to see your cart!");
			return "login";
		}
		
		List<StockItem> customerItems = shoppingCartService.getItemsInCartForCustomer(cust.getId());
		Map<StockItem, Game> itemGames = shoppingCartService.getGamesInCartForCustomer(customerItems);
		
		double rentalAmount = 0;
		if(!customerItems.isEmpty()) {
			rentalAmount = PRICEPERGAME * customerItems.size();
		}
		
		String rentalAmountString = String.format("$%.2f", rentalAmount);
		
		Map<Game, Picture> gamePictures = pictureService.getPicturesForGamesofSize(new ArrayList<Game>(itemGames.values()), 2);
		
		m.addAttribute("itemGames", itemGames);
		m.addAttribute("rentalSum", rentalAmountString);
		m.addAttribute("gamePictures",gamePictures);
		//itemGames.keySet()getClass();
		return "shoppingCart";
	}
	
	@PostMapping("/removeItemFromShoppingCart")
	public String removeItemFromShoppingCart(@RequestParam("itemId") int itemId, Model m, HttpServletRequest request) {
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","You must login to see your cart!");
			return "login";
		}
		
		boolean removed = shoppingCartService.removeItemFromShoppingCart(itemId, cust.getId());
		
		return getShoppingCart(m, request);
	}
	
}
