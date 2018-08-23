package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.Game;
import com.noahfields.Models.StockItem;

@Repository
public class RentalsDAO {

	private static final String ADDITEMSTORENTAL = "INSERT INTO Rentals (Customer_ID, Item_ID, Date_Rented, Due_Date) Values (?, ?, ?, ?)";
	private static final String GETNEXTRENTALSID = "SELECT MAX(id)+1 From Rentals";
	private static final String GETGAMESRENTEDFORCUSTOMER = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating FROM Games game"
			+ " JOIN Stock item on game.id = item.Game_ID JOIN Rentals rental on item.Item_ID = rental.Item_ID WHERE rental.Customer_ID = ?";

	public boolean addItemsToRentalsForCustomer(List<StockItem> rentalsItems, int customerID) {
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
			String[] rental_id = {"id"};
			PreparedStatement ps = conn.prepareStatement(ADDITEMSTORENTAL, rental_id);
			for(StockItem items: rentalsItems) {
				ps.setInt(1, customerID);
				ps.setInt(2, items.getItem_id());
				Date today = new java.sql.Date( Calendar.getInstance().getTime().getTime() );
				LocalDate fiveWeeks = today.toLocalDate().plusWeeks(5);
				Date dueDate = Date.valueOf(fiveWeeks);
				ps.setDate(3, today);
				ps.setDate(4, dueDate);
				ps.addBatch();
			}
			
			int[] executed = ps.executeBatch();
			added = true;
			for(Integer i: executed) {
				if(i < 0 || i == PreparedStatement.EXECUTE_FAILED) {
					added = false;
					break;
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return added;
	}

	public List<Game> getGamesRentedForCustomer(int customerID){
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
		
		List<Game> games = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETGAMESRENTEDFORCUSTOMER);
			ps.setInt(1, customerID);
			
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return games;
	}
}
