package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.Game;
import com.noahfields.Models.StockItem;

@Repository
public class ShoppingCartDAO extends GeneralDAO{

	private static final String GETITEMSINCARTFORCUSTOMER = "SELECT item.Item_ID, item.Game_ID, item.Status_ID, item.Serial_Number, item.Acquisition_Date, item.Condition_ID, item.Last_Examined"
			+ " FROM Stock item join Shopping_Cart sc on item.Item_ID = sc.Item_ID WHERE sc.Customer_ID = ?";
	private static final String GETGAMEFORITEM = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating"
			+ " FROM Games game join Stock item on game.id = item.Game_ID where item.Item_ID = ?";
	private static final String GETNEXTSHOPPINGCARTID = "SELECT Max(id) + 1 FROM Shopping_Cart";
	private static final String ADDITEMTOSHOPPINGCARTFORCUSTOMER = "INSERT INTO Shopping_Cart VAlUES (?, ?, ?)";
	private static final String GETNEXTAVAILABLESTOCKITEMFORGAME = "SELECT item.Item_ID, item.Game_ID, item.Status_ID, item.Serial_Number," + 
			"  item.Acquisition_Date, item.Condition_ID, item.Last_Examined FROM Stock item" + 
			"  join Games game on item.Game_ID = game.id" + 
			"  left join Shopping_Cart sc on item.Item_ID = sc.Item_ID" + 
			"  WHERE game.id = ? AND item.Status_ID = 1 and sc.Item_ID is null and ROWNUM = 1";
	private static final String REMOVEITEMFROMSHOPPINGCART = "DELETE FROM Shopping_Cart WHERE Customer_ID = ? AND Item_ID = ?";
	private static final String UPDATEITEMSTATUSTORENTED = "UPDATE Stock SET Status_ID = 3 WHERE Item_ID = ?"; //TODO What should the status id be? should it be set to 3 later?
	private static final String UPDATERENTALITEMSTATUSTOINSTOCK = "UPDATE Stock SET Status_ID = 1 WHERE Item_ID = (SELECT Item_ID From Rentals Where id = ?)"; //TODO Maybe should be EnRoute (2) and push off setting to 1 when confirmed in stock, but whatever 
	private static final String GETNUMITEMSINCARTFORCUSTOMER = "SELECT Count(Item_ID) FROM Shopping_Cart WHERE Customer_ID = ?";
	
	/**
	 * getItemsInCartForCustomer: gets all the items that a customer has in their shopping cart
	 * @param customer_id: id of the customer
	 * @return list of items in a customer's shopping cart
	 */
	public List<StockItem> getItemsInCartForCustomer(int customer_id){
		
		if(conn == null) {
			return null;
		}
		
		List<StockItem> stockItems = null;
		try {
			ps = conn.prepareStatement(GETITEMSINCARTFORCUSTOMER);
			ps.setInt(1, customer_id);
			
			ResultSet rs = ps.executeQuery();
			
			stockItems = new ArrayList<StockItem>();
			while(rs.next()) {
				StockItem stockItem = new StockItem();
				stockItem.setItem_id(rs.getInt(1));
				stockItem.setGame_id(rs.getInt(2));
				stockItem.setStatus_id(rs.getInt(3));
				stockItem.setSerial_number(rs.getString(4));
				stockItem.setAcquisition_date(rs.getDate(5));
				stockItem.setCondition_id(rs.getInt(6));
				stockItem.setLast_examined(rs.getDate(7));
				stockItems.add(stockItem);
				
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stockItems;
	}

	/**
	 * getGameForItem: gets the game for a particular item
	 * @param item_id: id of the item
	 * @return Game details for the given item
	 */
	public Game getGameForItem(int item_id) {
		
		if(conn == null) {
			return null;
		}
		
		Game game = null;
		
		try {
			ps = conn.prepareStatement(GETGAMEFORITEM);
			ps.setInt(1, item_id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				game = new Game();
				game.setId(rs.getInt(1));
				game.setName(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setYear_published(rs.getInt(4));
				game.setCost_of_game(rs.getDouble(5));
				game.setAverage_Rating(rs.getDouble(6));
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return game;
	}

	/**
	 * addItemToShoppingCartForCustomer: adds an item into the shopping cart for a customer
	 * @param item_id: id of the item to add
	 * @param customer_id: id of the customer to add for
	 * @return whether or not the item was added
	 */
	public boolean addItemToShoppingCartForCustomer(int item_id, int customer_id) {
		
		if(conn == null) {
			return false;
		}
		
		boolean added = false;
		try {
			ps = conn.prepareStatement(GETNEXTSHOPPINGCARTID);
			ResultSet rs = ps.executeQuery();
			int nextid = 0;
			if(rs.next()) {
			 nextid = rs.getInt(1);
			}
			ps = conn.prepareStatement(ADDITEMTOSHOPPINGCARTFORCUSTOMER);
			ps.setInt(1, nextid);
			ps.setInt(2, customer_id);
			ps.setInt(3, item_id);
			
			int executed = ps.executeUpdate();
			
			if(executed > 0) {
				added = true;
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return added;
	}

	/**
	 * getNextAvailableStockItemForGame: finds the next available item in stock for a particular game (not in a shopping cart and In Stock)
	 * @param game_id: id of game to find item for
	 * @return: StockItem found
	 */
	public StockItem getNextAvailableStockItemForGame(int game_id) {
		
		if(conn == null) {
			return null;
		}
		
		StockItem stockItem = null;
		
		try {
			ps = conn.prepareStatement(GETNEXTAVAILABLESTOCKITEMFORGAME);
			ps.setInt(1, game_id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				stockItem = new StockItem();
				stockItem.setItem_id(rs.getInt(1));
				stockItem.setGame_id(rs.getInt(2));
				stockItem.setStatus_id(rs.getInt(3));
				stockItem.setSerial_number(rs.getString(4));
				stockItem.setAcquisition_date(rs.getDate(5));
				stockItem.setCondition_id(rs.getInt(6));
				stockItem.setLast_examined(rs.getDate(7));
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stockItem;
	}

	/**
	 * removeItemFromShoppingCart: removes an item from a customer's shopping cart
	 * @param itemId: item to remove from the shopping cart
	 * @param customerId: customer to remove item for
	 * @return whether or not the item was removed
	 */
	public boolean removeItemFromShoppingCart(int itemId, int customerId) {
		
		if(conn == null) {
			return false;
		}
		
		boolean removed = false;
		
		try {
			ps = conn.prepareStatement(REMOVEITEMFROMSHOPPINGCART);
			ps.setInt(1, customerId);
			ps.setInt(2, itemId);

			int updated = ps.executeUpdate();
			
			if(updated > 0) {
				removed = true;
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return removed;
	}

	

	/**
	 * updateItemsInStockToRented: sets the given items to have the Rented (3) status
	 * @param rentalsItems: items to change state
	 * @return whether or not the items were updated
	 */
	public boolean updateItemsInStockToRented(List<StockItem> rentalsItems) {
		
		if(conn == null) {
			return false;
		}
		
		boolean updated = false;
		
		try {
			ps = conn.prepareStatement(UPDATEITEMSTATUSTORENTED);
			for(StockItem item: rentalsItems) {
				ps.setInt(1, item.getItem_id());
				ps.addBatch();
			}
			int[] executions = ps.executeBatch();
			updated = true;
			for(Integer i: executions) {
				if(i < 0 || i == PreparedStatement.EXECUTE_FAILED) {
					updated = false;
					break;
				}
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updated;
	}

	/**
	 * removeItemsFromShoppingCart: removes multiple items from the shopping cart for a given user (used when transferring to Rentals table)
	 * @param rentalsItems: items to remove
	 * @param customerID: customer to remove for
	 * @return whether or not the items were removed
	 */
	public boolean removeItemsFromShoppingCart(List<StockItem> rentalsItems, int customerID) {
		
		if(conn == null) {
			return false;
		}
		
		boolean removed = false;
		
		try {
			ps = conn.prepareStatement(REMOVEITEMFROMSHOPPINGCART);
			for(StockItem item: rentalsItems) {
				ps.setInt(1, customerID);
				ps.setInt(2, item.getItem_id());
				ps.addBatch();
			}
			int[] executed = ps.executeBatch();
			removed = true;
			for(Integer i: executed) {
				if(i == PreparedStatement.EXECUTE_FAILED) {
					removed = false;
					break;
				}
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return removed;
	}

	/**
	 * updateRentalItemsToInStock: updates items from a particular rental to be In stock (status 1)
	 * @param rentalId: rental id of item
	 * @return whether or not the status was changed
	 */
	public boolean updateRentalItemsToInStock(int rentalId) {
		
		if(conn == null) {
			return false;
		}
		
		boolean updated = false;
		
		try {
			ps = conn.prepareStatement(UPDATERENTALITEMSTATUSTOINSTOCK);
			ps.setInt(1, rentalId);
			
			int changed = ps.executeUpdate();
			updated = true;
			if(changed < 0 || changed == PreparedStatement.EXECUTE_FAILED) {
				updated = false;
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		return updated;
	}

	/**
	 * getNumItemsInCartForCustomer: gets the number of items the customer has in their cart
	 * @param id: customer id
	 * @return number of items in customer's cart
	 */
	public int getNumItemsInCartForCustomer(int id) {
		
		if(conn == null) {
			return -1;
		}
		
		int numItems = -1;
		
		try {
			ps = conn.prepareStatement(GETNUMITEMSINCARTFORCUSTOMER);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			numItems = 0;
			if(rs.next()) {
				numItems = rs.getInt(1);
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numItems;
	}
}
