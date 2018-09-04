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
public class OrdersDAO extends GeneralDAO{
	
	private static final String INSERTORDERFORCUSTOMER = "INSERT INTO Orders (Order_ID, Item_ID, Customer_ID, Date_Shipped) VALUES (?, ?, ?, NULL)";
	private static final String GETNEXTORDERID = "SELECT MAX(Order_ID) + 1 FROM Orders";
	private static final String GETGAMESFORORDER = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating FROM Games game"
			+ " JOIN Stock item on game.id = item.Game_ID JOIN Orders ord on item.Item_ID = ord.Item_ID WHERE ord.Order_ID = ?";

	public int addItemsToOrderForCustomer(List<StockItem> rentalsItems, int customerID) {
		
		if(conn == null) {
			return -1;
		}
		
		int id = -1;
		
		try {
			ps = conn.prepareStatement(GETNEXTORDERID);
			ResultSet rs = ps.executeQuery();
			int nextId = 0;
			if(rs.next()) {
				nextId = rs.getInt(1); 
			}
			
			ps = conn.prepareStatement(INSERTORDERFORCUSTOMER); //conn.prepareStatement(INSERTORDERFORCUSTOMER);
			for(StockItem item: rentalsItems) {
				ps.setInt(1, nextId);
				ps.setInt(2, item.getItem_id());
				ps.setInt(3, customerID);
				ps.addBatch();
			}
			int[] execution = ps.executeBatch();
			
			boolean placed = true;
			for(Integer i: execution) {
				if(i < 0 || i == PreparedStatement.EXECUTE_FAILED) {
					placed = false;
				}
			}
			if(placed) {
				id = nextId;
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	public List<Game> getGamesForOrder(int orderID){
		
		if(conn == null) {
			return null;
		}
		
		List<Game> games = null;
		try {
			ps = conn.prepareStatement(GETGAMESFORORDER);
			ps.setInt(1, orderID);
			
			ResultSet rs = ps.executeQuery();
			games = new ArrayList<Game>();
			while(rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setName(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setYear_published(rs.getInt(4));
				game.setCost_of_game(rs.getDouble(5));
				game.setAverage_Rating(rs.getDouble(6));
				games.add(game);
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return games;
	}
}
