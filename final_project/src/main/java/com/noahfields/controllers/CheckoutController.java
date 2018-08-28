package com.noahfields.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.noahfields.Models.Customer;
import com.noahfields.Models.Game;
import com.noahfields.Models.Picture;
import com.noahfields.Models.StockItem;
import com.noahfields.Models.Zipcode;
import com.noahfields.services.OrdersService;
import com.noahfields.services.PictureService;
import com.noahfields.services.ShoppingCartService;
import com.noahfields.services.ZipcodeService;

@Controller
@SessionAttributes("shipCust")
public class CheckoutController {

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	ZipcodeService zipcodeService;
	
	@Autowired
	PictureService pictureService;
	
	@GetMapping("/checkout/shipping")
	public String checkoutShipping(Model m, HttpServletRequest request) {
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","You must be logged in to checkout");
			return "login";
		}
		
		if(shoppingCartService.getItemsInCartForCustomer(cust.getId()).isEmpty()) {
			m.addAttribute("error", "no items in your shopping cart to check out!");
			return "shoppingCart";
		};
		
		Zipcode custZip = zipcodeService.getZipcode(cust.getZipcode());
		
		List<Zipcode> allZips = zipcodeService.getAllZips();
		m.addAttribute("zipcode", custZip);
		m.addAttribute("allZips",allZips);
		
		
		return "checkoutShipping";
	}
	
	@PostMapping("/checkout/billing")
	public String checkoutBilling(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("state") String state,
			 @RequestParam("city") String city, @RequestParam("address1") String address1, @RequestParam("address2") String address2,
			 @RequestParam("country") String country, @RequestParam("zipcode") String zipcode, @RequestParam("phone") String phone, Model m, HttpServletRequest request) {
		
		int zip = 0;
		try {
			zip = Integer.parseInt(zipcode);
		} catch(NumberFormatException e) {
			m.addAttribute("error", "could not parse the Zipcode");
			return "checkoutBilling";
		}
		
		long phoneNum = 0;
		try {
			phoneNum = Long.parseLong(phone);
		} catch(NumberFormatException e) {
			m.addAttribute("error", "could not parse the phone number");
			return "checkoutBilling";
		}
		
		if("".equals(address2)) {
			address2 = null;
		}
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","You can not checkout until you are logged in.");
			return "login";
		}
		
		Customer shippingTo = new Customer();
		shippingTo.setFirst_name(firstname);
		shippingTo.setLast_name(lastname);
		shippingTo.setAddress_line_1(address1);
		shippingTo.setAddress_line_2(address2);
		shippingTo.setZipcode(zip);
		shippingTo.setPhone(phoneNum);
		
		m.addAttribute("shipCust", shippingTo);
		request.getSession().setAttribute("shipCust", shippingTo);
		
		Zipcode custZip = zipcodeService.getZipcode(cust.getZipcode());
		
		List<Zipcode> allZips = zipcodeService.getAllZips();
		m.addAttribute("zipcode", custZip);
		m.addAttribute("allZips",allZips);
		return "checkoutBilling";
	}
	
	@PostMapping("/checkout/confirmation")
	public String checkoutConfirmation(@RequestParam("fullname") String fullname, @RequestParam("state") String state,
			 @RequestParam("city") String city, @RequestParam("address1") String address1, @RequestParam("address2") String address2,
			 @RequestParam("country") String country, @RequestParam("zipcode") String zipcode,@RequestParam("creditCardNum") String creditCardNum, Model m, HttpServletRequest request) {

		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error", "You must be logged in to checkout");
			return "login";
		}
		
		int billZipNum = 0;
		try {
			billZipNum = Integer.parseInt(zipcode);
		} catch(NumberFormatException e) {
			m.addAttribute("error", "could not parse the Zipcode");
			return "checkoutBilling";
		}
		
		if("".equals(address2)) {
			address2 = null;
		}
		
		String[] names = fullname.split(" ");
		String firstname = names[0];
		String lastname = names[1];
		
		Customer billingTo = new Customer();
		billingTo.setFirst_name(firstname);
		billingTo.setLast_name(lastname);
		billingTo.setAddress_line_1(address1);
		billingTo.setAddress_line_2(address2);
		billingTo.setZipcode(billZipNum);

		Zipcode billZip = zipcodeService.getZipcode(billZipNum);
		
		List<StockItem> rentalsItems = shoppingCartService.getItemsInCartForCustomer(cust.getId());
		Map<StockItem, Game> rentals = shoppingCartService.getGamesInCartForCustomer(rentalsItems);
		
		Map<Game, Picture> gamePictures = pictureService.getPicturesForGamesofSize(new ArrayList<Game>(rentals.values()), 2);
		
		request.getSession().setAttribute("billCust", billingTo);
		Customer shippingTo = (Customer) request.getSession().getAttribute("shipCust");
		
		if(shippingTo == null || shippingTo.equals(null)) {
			m.addAttribute("error","could not find shipping customer");
			return "checkoutShipping";
		}
		
		Zipcode shipZip = zipcodeService.getZipcode(shippingTo.getZipcode());
		//request.getSession().setAttribute("shipCust", null);
		double rentalAmount = 0;
		if(!rentals.isEmpty()) {
			rentalAmount = ShoppingCartController.PRICEPERGAME * rentals.size();
		}
		
		String rentalAmountString = String.format("$%.2f", rentalAmount);
		double shippingCost = 5.00;
		
		String shippingAmountString = String.format("$%.2f", shippingCost);
		double totalSum = rentalAmount + shippingCost;
		
		String totalSumString = String.format("$%.2f", totalSum);
		
		creditCardNum = creditCardNum.replaceAll("\\D","");
		String creditCardString = "************"+creditCardNum.substring(12, 16);
		
		request.getSession().setAttribute("creditCardString", creditCardString);
		
		m.addAttribute("gamePictures", gamePictures);
		m.addAttribute("shipCust", shippingTo);
		m.addAttribute("shipCustZip", shipZip);
		m.addAttribute("billCust", billingTo);
		m.addAttribute("billCustZip", billZip);
		m.addAttribute("itemGames", rentals);
		m.addAttribute("rentalSum", rentalAmountString);
		m.addAttribute("shippingCost", shippingAmountString);
		m.addAttribute("totalSum", totalSumString);
		m.addAttribute("creditCard", creditCardString);
		
		return "checkoutReview"; //TODO create a confirmation page!
	}
	
	@GetMapping("/checkout/createOrder")
	public String createOrder(Model m, HttpServletRequest request){
		Customer cust = (Customer) request.getSession().getAttribute("customer");
				
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","You must be logged in to checkout");
			return "login";
		}
		
		Customer shippingTo = (Customer) request.getSession().getAttribute("shipCust");
		
		if(shippingTo == null || shippingTo.equals(null)) {
			m.addAttribute("error", "can not load shipping info");
			return "checkoutShipping";
		}
		
		Zipcode shipZip = zipcodeService.getZipcode(shippingTo.getZipcode());
		
		Customer billingTo = (Customer) request.getSession().getAttribute("billCust");
		if(billingTo == null || billingTo.equals(null)) {
			m.addAttribute("error", "could not load billing info");
			return "checkoutBilling";
		}
		
		Zipcode billZip = zipcodeService.getZipcode(billingTo.getZipcode());
		
		List<StockItem> rentalsItems = shoppingCartService.getItemsInCartForCustomer(cust.getId());
		Map<StockItem, Game> rentals = shoppingCartService.getGamesInCartForCustomer(rentalsItems);
		
		Map<Game, Picture> gamePictures = pictureService.getPicturesForGamesofSize(new ArrayList<Game>(rentals.values()), 2);
		
		double rentalAmount = 0;
		if(!rentals.isEmpty()) {
			rentalAmount = ShoppingCartController.PRICEPERGAME * rentals.size();
		}
		
		String rentalAmountString = String.format("$%.2f", rentalAmount);
		double shippingCost = 5.00;
		
		String shippingAmountString = String.format("$%.2f", shippingCost);
		double totalSum = rentalAmount + shippingCost;
		
		String totalSumString = String.format("$%.2f", totalSum);
		
		m.addAttribute("gamePictures", gamePictures);
		m.addAttribute("shipCust", shippingTo);
		m.addAttribute("shipCustZip", shipZip);
		m.addAttribute("billCust", billingTo);
		m.addAttribute("billCustZip", billZip);
		m.addAttribute("itemGames", rentals);
		m.addAttribute("rentalSum", rentalAmountString);
		m.addAttribute("shippingCost", shippingAmountString);
		m.addAttribute("totalSum", totalSumString);
		m.addAttribute("creditCard", request.getSession().getAttribute("creditCardString"));
		
		request.getSession().removeAttribute("creditCardString");
		
		int orderId = ordersService.placeOrdersForCustomer(rentalsItems,cust.getId());
		if(orderId < 0) {
			m.addAttribute("error", "error in placing order!");
			return "checkoutReview";
		}
		
		m.addAttribute("orderId",orderId);
		
		return "checkoutOrderPage";
	}
	
}
