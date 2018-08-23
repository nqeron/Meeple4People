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
public class ShoppingCartDAO {

	private static final String GETITEMSINCARTFORCUSTOMER = "SELECT item.Item_ID, item.Game_ID, item.Status_ID, item.Serial_Number, item.Acquisition_Date, item.Condition_ID, item.Last_Examined"
			+ " FROM Stock item join Shopping_Cart sc on item.Item_ID = sc.Item_ID WHERE Customer_ID = ?";
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
	private static final String UPDATEITEMSTATUS = "UPDATE Stock SET Status_ID = 3 WHERE Item_ID = ?"; //TODO What should the status id be? should it be set to 3 later?
	
	public List<StockItem> getItemsInCartForCustomer(int customer_id){
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return null;
		}
		
		List<StockItem> stockItems = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETITEMSINCARTFORCUSTOMER);
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stockItems;
	}

	public Game getGameForItem(int item_id) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return null;
		}
		
		Game game = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(GETGAMEFORITEM);
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return game;
	}

	public boolean addItemToShoppingCartForCustomer(int item_id, int customer_id) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return false;
		}
		
		boolean added = false;
		try {
			PreparedStatement ps = conn.prepareStatement(GETNEXTSHOPPINGCARTID);
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return added;
	}

	public StockItem getNextAvailableStockItemForGame(int game_id) {
		
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return null;
		}
		
		StockItem stockItem = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(GETNEXTAVAILABLESTOCKITEMFORGAME);
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stockItem;
	}

	public boolean removeItemFromShoppingCart(int itemId, int customerId) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return false;
		}
		
		boolean removed = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(REMOVEITEMFROMSHOPPINGCART);
			ps.setInt(1, customerId);
			ps.setInt(2, itemId);

			int updated = ps.executeUpdate();
			
			if(updated > 0) {
				removed = true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return removed;
	}

	

	public boolean updateItemsInStockToRented(List<StockItem> rentalsItems) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return false;
		}
		
		boolean updated = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATEITEMSTATUS);
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updated;
	}

	public boolean removeItemsFromShoppingCart(List<StockItem> rentalsItems, int customerID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return false;
		}
		
		boolean removed = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(REMOVEITEMFROMSHOPPINGCART);
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return removed;
	}
}
