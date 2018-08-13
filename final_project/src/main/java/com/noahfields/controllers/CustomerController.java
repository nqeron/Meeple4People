package com.noahfields.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.noahfields.DAO.CustomerDAO;
import com.noahfields.DAO.ZipcodeDAO;
import com.noahfields.Models.Customer;
import com.noahfields.Models.Zipcode;
import com.noahfields.services.CustomerService;
import com.noahfields.services.ZipcodeService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ZipcodeService zipcodeService;
	
	@GetMapping("/register")
	public String goToRegister() {
		return "signUp";
	}
	
	@PostMapping("/registerAction")
	public String register(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("inputPassword") String password, Model m, HttpServletRequest request) {
		Customer customer = customerService.getCustomerByUsername(username);
		
		if( (customer != null) && !customer.equals(null) ) {
			m.addAttribute("error","That username is already taken!");
			return "signUp";
		}
		
		customer = customerService.getCustomerByEmail(email);
		if( (customer != null) && !customer.equals(null) ) {
			m.addAttribute("error","That email is already taken!");
			return "signUp";
		}
		
		customer = new Customer();
		customer.setPassword(password);
		customer.setE_mail(email);
		customer.setUsername(username);
		customer.setMember_status("Active");
		Date date = new java.sql.Date( Calendar.getInstance().getTime().getTime() );
		customer.setJoin_date(date);
		customer.setBalance(0);
		
		int id = customerService.registerUser(customer);
		if(id <= 0) {
			m.addAttribute("error", "Unable to create user for some reason!");
			return "signUp";
		}
		
		customer.setId(id);
		
		//m.addAttribute("customer", customer);
		request.getSession().setAttribute("customer", customer);
		return userProfileMain(m, request);
	}

	
	@GetMapping("/userProfile")
	public String userProfileMain(Model m, HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		
		if(customer == null || customer.equals(null)) {
			m.addAttribute("error", "You must login to see this page!");
			return "login";
		}
		
		Zipcode zip = zipcodeService.getZipcode(customer.getZipcode());
		
		List<Zipcode> allZips = zipcodeService.getAllZips();
		
		m.addAttribute("zipcode", zip);
		m.addAttribute("allZips", allZips);
		
		return "userProfile";
	}
	
//	private String userProfileMain(Model m, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return "userProfile";
//	}
	
	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}
	
	@GetMapping("/logout")
	public RedirectView logout(ModelAndView m, HttpServletRequest request) {
		//ModelAndView m = new ModelAndView();
		
		request.getSession().setAttribute("customer", null);
		String referer = request.getHeader("Referer");
		if(referer.endsWith("loginAction") || referer.endsWith("updateUser") || referer.endsWith("registerAction")) {
			referer = "/login";
		}
		return new RedirectView(referer);
	}
	
	@PostMapping("/loginAction")
	public String login(@RequestParam("username") String username, @RequestParam("inputPassword") String password, Model m, HttpServletRequest request) {
		
		if(!customerService.verifyUser(username, password)) {
			m.addAttribute("error", "incorrect credentials");
			return "login";
		}
		
		Customer customer = customerService.getCustomerByUsername(username);
		
		if(customer == null || customer.equals(null)) {
			m.addAttribute("error","Could not retrieve user");
			return "login";
		}
		
		request.getSession().setAttribute("customer", customer);
		
		return userProfileMain(m, request);
	}

	@PostMapping("/updateUser")
	public String updateUser(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("state") String state,
							 @RequestParam("city") String city, @RequestParam("address1") String address1, @RequestParam("address2") String address2,
							 @RequestParam("country") String country, @RequestParam("zipcode") String zipcode, @RequestParam("phone") String phone, @RequestParam("email") String email,
							 Model m, HttpServletRequest request) {
		
		int zip = 0;
		try {
			zip = Integer.parseInt(zipcode);
		} catch(NumberFormatException e) {
			m.addAttribute("error", "could not parse the Zipcode");
			return userProfileMain(m, request);
		}
		
		long phoneNum = 0;
		try {
			phoneNum = Long.parseLong(phone);
		} catch(NumberFormatException e) {
			m.addAttribute("error", "could not parse the phone number");
			return userProfileMain(m, request);
		}
		
		if("".equals(firstname)) {
			firstname = null;
		}
		if("".equals(lastname)) {
			lastname = null;
		}
		if("".equals(address1)) {
			address1 = null;
		}
		if("".equals(address2)) {
			address2 = null;
		}
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","Could not get user from session, please log back in.");
			return "login";
		}
		
		Customer custEmail = customerService.getCustomerByEmail(email);
		if(!cust.equals(custEmail)) {
			m.addAttribute("error", "That email is already taken! cust: " + cust + " custEmai: "+ custEmail);
			return userProfileMain(m, request);
		}
		
		Zipcode zipcodeObj =  zipcodeService.getZipcode(zip);
		if(zipcodeObj == null || zipcodeObj.equals(null)) {
			m.addAttribute("error","That zipcode is not in the database, please contact the db admin");
			return userProfileMain(m, request);
		}
		
		cust.setFirst_name(firstname);
		cust.setLast_name(lastname);
		cust.setAddress_line_1(address1);
		cust.setAddress_line_2(address2);
		cust.setZipcode(zip);
		cust.setPhone(phoneNum);
		cust.setE_mail(email);
		
		int reason = 0;
		if((reason = customerService.updateCustomer(cust)) < 0) {
			m.addAttribute("error", "Could not update user for reason " + reason + " customer id: " + cust.getId());
		}
		
		request.getSession().setAttribute("customer", cust);
		return userProfileMain(m, request);
	}
	
}
