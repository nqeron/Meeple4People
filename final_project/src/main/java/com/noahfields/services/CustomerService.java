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
		customerDao.connect();
		return customerDao.getCustomerByUsername(username);
	}

	public Customer getCustomerByEmail(String email) {
		customerDao.connect();
		return customerDao.getCustomerByEmail(email);
	}

	public int registerUser(Customer customer) {
		customerDao.connect();
		return customerDao.registerUser(customer);
	}

	public boolean verifyUser(String username, String password) {
		customerDao.connect();
		return customerDao.verifyUser(username, password);
	}

	public int updateCustomer(Customer cust) {
		customerDao.connect();
		return customerDao.updateCustomer(cust);
	}

}
