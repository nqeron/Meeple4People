package web.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import core.DAO.CustomerDAO;
import core.DAO.ZipcodeDAO;
import core.Models.Customer;
import core.Models.Zipcode;

@Controller
public class CustomerController {

	
	@GetMapping("/register")
	public String goToRegister() {
		return "signUp";
	}
	
	@PostMapping("/registerAction")
	public String register(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("inputPassword") String password, Model m, HttpServletRequest request) {
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.getCustomerByUsername(username);
		
		if( (customer != null) && !customer.equals(null) ) {
			m.addAttribute("error","That username is already taken!");
			return "signUp";
		}
		
		customer = customerDAO.getCustomerByEmail(email);
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
		
		int id = customerDAO.registerUser(customer);
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
		
		Zipcode zip = new ZipcodeDAO().getZipcode(customer.getZipcode());
		
		m.addAttribute("zipcode", zip);
		
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
		return new RedirectView(request.getHeader("Referer"));
	}
	
	@PostMapping("/loginAction")
	public String login(@RequestParam("username") String username, @RequestParam("inputPassword") String password, Model m, HttpServletRequest request) {
		
		CustomerDAO customerDao = new CustomerDAO();
		
		if(!customerDao.verifyUser(username, password)) {
			m.addAttribute("error", "incorrect credentials");
			return "login";
		}
		
		Customer customer = customerDao.getCustomerByUsername(username);
		
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
		
		Customer cust = (Customer) request.getSession().getAttribute("customer");
		
		if(cust == null || cust.equals(null)) {
			m.addAttribute("error","Could not get user from session, please log back in.");
			return "login";
		}
		
		CustomerDAO customerDAO = new CustomerDAO();
		Customer custEmail = customerDAO.getCustomerByEmail(email);
		if(!cust.equals(custEmail)) {
			m.addAttribute("error", "That email is already taken!");
			return userProfileMain(m, request);
		}
		
		Zipcode zipcodeObj =  new ZipcodeDAO().getZipcode(zip);
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
		if((reason = customerDAO.updateCustomer(cust)) < 0) {
			m.addAttribute("error", "Could not update user for reason " + reason + " customer id: " + cust.getId());
		}
		
		request.getSession().setAttribute("customer", cust);
		return userProfileMain(m, request);
	}
	
}
