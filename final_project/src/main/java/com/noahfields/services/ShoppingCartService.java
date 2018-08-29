package com.noahfields.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.GeneralDAO;
import com.noahfields.DAO.RentalsDAO;
import com.noahfields.DAO.ShoppingCartDAO;
import com.noahfields.Models.Customer;
import com.noahfields.Models.Game;
import com.noahfields.Models.StockItem;

@Service
public class ShoppingCartService {

	public static final int COUlDNOTBEADDED = -2;
	public static final int EXCEEDMAXNUMGAMES = -1;
	private static final int MAXNUMGAMES = 5;
	public static final int CUSTOMERISSUSPENDED = -3;
	
	@Autowired
	ShoppingCartDAO shCartDAO;
	
	@Autowired
	RentalsDAO rentalsDAO;
	
	
	public List<StockItem> getItemsInCartForCustomer(int customer_id){
		return shCartDAO.getItemsInCartForCustomer(customer_id);
	}
	
	public StockItem getNextAvailableStockItemForGame(int gameId) {
		return shCartDAO.getNextAvailableStockItemForGame(gameId);
	}
	
	public int addItemToShoppingCartForCustomer(StockItem item, Customer customer) {
		if(customer.getMember_status() == "Suspended") {
			return CUSTOMERISSUSPENDED;
		}
		
		int numItemsInCart = shCartDAO.getNumItemsInCartForCustomer(customer.getId());
		int numItemsRented = rentalsDAO.getNumItemsRentedForCustomer(customer.getId());
		
		if(numItemsInCart + numItemsRented >= MAXNUMGAMES) {
			return EXCEEDMAXNUMGAMES;
		}
		boolean added = shCartDAO.addItemToShoppingCartForCustomer(item.getItem_id(), customer.getId());
		return added? 1 : COUlDNOTBEADDED;
	}

	public Map<StockItem, Game> getGamesInCartForCustomer(List<StockItem> customerItems) {
		Map<StockItem, Game> gameItems = new HashMap<StockItem, Game>();
		for(StockItem si: customerItems) {
			Game game = shCartDAO.getGameForItem(si.getItem_id());
			gameItems.put(si, game);
		}
		return gameItems;
	}

	public boolean removeItemFromShoppingCart(int itemId, int customerId) {
		return shCartDAO.removeItemFromShoppingCart(itemId,customerId);
	}

	
}
