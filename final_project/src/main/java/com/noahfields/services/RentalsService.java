package com.noahfields.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.GeneralDAO;
import com.noahfields.DAO.RentalsDAO;
import com.noahfields.DAO.ShoppingCartDAO;
import com.noahfields.Models.Game;
import com.noahfields.Models.Rental;

@Service
public class RentalsService {

	@Autowired
	RentalsDAO rentalsDAO;
	
	@Autowired
	GeneralDAO generalDAO;
	
	@Autowired
	ShoppingCartDAO shCartDAO;
	
	public Map<Game, Rental> getGamesRentedForCustomer(int customerID){
		return rentalsDAO.getGamesRentedForCustomer(customerID);
	}

	public boolean returnRental(int rentalId) {
		
		//generalDAO.startTransaction();
		
		boolean changed = shCartDAO.updateRentalItemsToInStock(rentalId);
		if(!changed) {
			generalDAO.rollback();
			return false;
		}
		
		boolean remove = rentalsDAO.removeRental(rentalId);
		if(!remove) {
			generalDAO.rollback();
			return false;
		}
		
		return true;
	}

	public Game getGameFromRental(int rentalId) {
		return rentalsDAO.getGameFromRental(rentalId);
	}
	
}
