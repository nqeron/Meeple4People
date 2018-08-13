package com.noahfields.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.CustomerDAO;
import com.noahfields.Models.Customer;

@Service
public class CustomerService {

	@Autowired
	CustomerDAO customerDao;
	
	public Customer getCustomerByUsername(String username) {
		return customerDao.getCustomerByUsername(username);
	}

	public Customer getCustomerByEmail(String email) {
		return customerDao.getCustomerByEmail(email);
	}

	public int registerUser(Customer customer) {
		return customerDao.registerUser(customer);
	}

	public boolean verifyUser(String username, String password) {
		return customerDao.verifyUser(username, password);
	}

	public int updateCustomer(Customer cust) {
		return customerDao.updateCustomer(cust);
	}

}
