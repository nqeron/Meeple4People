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
	
//	@Autowired
//	GeneralDAO generalDAO;
	
	@Autowired
	ShoppingCartDAO shCartDAO;
	
	public Map<Game, Rental> getGamesRentedForCustomer(int customerID){
		rentalsDAO.connect();
		return rentalsDAO.getGamesRentedForCustomer(customerID);
	}

	public boolean returnRental(int rentalId) {
		
		//rentalsDAO
		rentalsDAO.setAutoCommit(false);
		rentalsDAO.setKeepOpen(true);
		rentalsDAO.connect();
		
		//shCartDAO
		shCartDAO.setAutoCommit(false);
		shCartDAO.setKeepOpen(true);
		shCartDAO.connect();
		
		boolean changed = shCartDAO.updateRentalItemsToInStock(rentalId);
		if(!changed) {
			shCartDAO.rollback();
			shCartDAO.dispose();
			return false;
		}
		
		boolean remove = rentalsDAO.removeRental(rentalId);
		if(!remove) {
			shCartDAO.rollback();
			shCartDAO.dispose();
			
			rentalsDAO.rollback();
			rentalsDAO.dispose();
			return false;
		}
		
		rentalsDAO.commit();
		rentalsDAO.dispose();
		
		shCartDAO.commit();
		shCartDAO.dispose();
		return true;
	}

	public Game getGameFromRental(int rentalId) {
		rentalsDAO.connect();
		return rentalsDAO.getGameFromRental(rentalId);
	}
	
}
