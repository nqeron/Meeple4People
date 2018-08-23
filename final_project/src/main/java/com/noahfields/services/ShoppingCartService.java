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

	@Autowired
	ShoppingCartDAO shCartDAO;
	
	
	public List<StockItem> getItemsInCartForCustomer(int customer_id){
		return shCartDAO.getItemsInCartForCustomer(customer_id);
	}
	
	public StockItem getNextAvailableStockItemForGame(int gameId) {
		return shCartDAO.getNextAvailableStockItemForGame(gameId);
	}
	
	public boolean addItemToShoppingCartForCustomer(StockItem item, Customer customer) {
		return shCartDAO.addItemToShoppingCartForCustomer(item.getItem_id(), customer.getId());
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
		// TODO Auto-generated method stub
		return shCartDAO.removeItemFromShoppingCart(itemId,customerId);
	}

	
}
