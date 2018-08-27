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
	
	@Autowired
	GeneralDAO generalDAO;
	
	@Autowired
	ShoppingCartDAO shCartDAO;
	
	@Autowired
	OrdersDAO ordersDAO;
	
	public int placeOrdersForCustomer(List<StockItem> rentalsItems, int customerID) {
		
		generalDAO.startTransaction();
		
		int id = ordersDAO.addItemsToOrderForCustomer(rentalsItems, customerID);
		if(id < 0) {
			generalDAO.rollback();
			return -1;
		}
		
		boolean added = rentalsDAO.addItemsToRentalsForCustomer(rentalsItems, customerID);
		if(!added) {
			generalDAO.rollback();
			return -1;
		}
		
		boolean changed = shCartDAO.updateItemsInStockToRented(rentalsItems);
		if(!changed) {
			generalDAO.rollback();
			return -1;
		}
		
		boolean removed = shCartDAO.removeItemsFromShoppingCart(rentalsItems, customerID);
		if(!removed) {
			generalDAO.rollback();
			return -1;
		}
		
		
		return id;
		
	}
	
	public List<Game> getGamesForOrder(int orderId){
		return ordersDAO.getGamesForOrder(orderId);
	}
}
