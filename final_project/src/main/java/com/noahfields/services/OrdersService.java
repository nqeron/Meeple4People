package com.noahfields.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.GeneralDAO;
import com.noahfields.DAO.OrdersDAO;
import com.noahfields.DAO.RentalsDAO;
import com.noahfields.DAO.ShoppingCartDAO;
import com.noahfields.Models.Game;
import com.noahfields.Models.StockItem;

@Service
public class OrdersService {

	@Autowired
	RentalsDAO rentalsDAO;
	
//	@Autowired
//	GeneralDAO generalDAO;
	
	@Autowired
	ShoppingCartDAO shCartDAO;
	
	@Autowired
	OrdersDAO ordersDAO;
	
	public int placeOrdersForCustomer(List<StockItem> rentalsItems, int customerID) {
		
		//Prevent ordersDAO, rentalsDAO, and shCartDAO from committing their changes, allowing a rollback if necessary
		//keep their connections open so that the changes can be committed once everything goes through.
		ordersDAO.setAutoCommit(false);
		ordersDAO.setKeepOpen(true);
		ordersDAO.connect();
		
		//rentalsDAO
		rentalsDAO.setAutoCommit(false);
		rentalsDAO.setKeepOpen(true);
		rentalsDAO.connect();
		
		//shCartDAO
		shCartDAO.setAutoCommit(false);
		shCartDAO.setKeepOpen(true);
		shCartDAO.connect();
		
		int id = ordersDAO.addItemsToOrderForCustomer(rentalsItems, customerID);
		if(id < 0) {
			ordersDAO.rollback();
			ordersDAO.dispose();
			return -1;
		}
		
		boolean added = rentalsDAO.addItemsToRentalsForCustomer(rentalsItems, customerID);
		if(!added) {
			//rollback both transactions so far
			rentalsDAO.rollback();
			rentalsDAO.dispose();
			
			ordersDAO.rollback();
			ordersDAO.dispose();
			return -1;
		}
		
		boolean changed = shCartDAO.updateItemsInStockToRented(rentalsItems);
		if(!changed) {
			//rollback all transactions so far
			rentalsDAO.rollback();
			rentalsDAO.dispose();
			
			ordersDAO.rollback();
			ordersDAO.dispose();
			
			shCartDAO.rollback();
			shCartDAO.dispose();
			return -1;
		}
		
		boolean removed = shCartDAO.removeItemsFromShoppingCart(rentalsItems, customerID);
		if(!removed) {
			//rollback all transactions so far
			rentalsDAO.rollback();
			rentalsDAO.dispose();
			
			ordersDAO.rollback();
			ordersDAO.dispose();
			
			shCartDAO.rollback();
			shCartDAO.dispose();
			return -1;
		}
		
		//if all of this has been successful, commit the changes and close the connections
		
		ordersDAO.commit();
		ordersDAO.dispose();
		
		rentalsDAO.commit();
		rentalsDAO.dispose();
		
		shCartDAO.commit();
		shCartDAO.dispose();
		return id;
		
	}
	
	public List<Game> getGamesForOrder(int orderId){
		ordersDAO.connect();
		return ordersDAO.getGamesForOrder(orderId);
	}
}
