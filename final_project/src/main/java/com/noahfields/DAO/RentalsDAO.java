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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.Game;
import com.noahfields.Models.Rental;
import com.noahfields.Models.StockItem;

@Repository
public class RentalsDAO {

	private static final String ADDITEMSTORENTAL = "INSERT INTO Rentals (Customer_ID, Item_ID, Date_Rented, Due_Date) Values (?, ?, ?, ?)";
	private static final String GETNEXTRENTALSID = "SELECT MAX(id)+1 From Rentals";
	private static final String GETGAMESRENTEDFORCUSTOMER = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating, rental.id, rental.Date_Rented, rental.Due_Date FROM Games game"
			+ " JOIN Stock item on game.id = item.Game_ID JOIN Rentals rental on item.Item_ID = rental.Item_ID WHERE rental.Customer_ID = ?";
	private static final String REMOVERENTALBYID = "DELETE FROM Rentals WHERE id = ?";
	private static final String GETGAMEFROMRENTAL = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating FROM Games game JOIN Stock item on game.id = item.Game_ID "
			+ " JOIN Rentals rental on item.Item_ID = rental.Item_ID WHERE rental.id = ?";
	private static final String GETNUMITEMSRENTEDFORCUSTOMER = "SELECT Count(Item_ID) FROM Rentals WHERE Customer_ID = ?";

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

	public Map<Game, Rental> getGamesRentedForCustomer(int customerID){
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
		
		Map<Game, Rental> gamesRented = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETGAMESRENTEDFORCUSTOMER);
			ps.setInt(1, customerID);
			
			ResultSet rs = ps.executeQuery();
			gamesRented = new HashMap<Game, Rental>();
			while(rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setName(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setYear_published(rs.getInt(4));
				game.setCost_of_game(rs.getDouble(5));
				game.setAverage_Rating(rs.getDouble(6));
				
				Rental rental = new Rental();
				rental.setId(rs.getInt(7));
				rental.setDate_rented(rs.getDate(8));
				rental.setDue_date(rs.getDate(9));
				
				gamesRented.put(game, rental);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gamesRented;
	}

	public boolean removeRental(int rentalId) {
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
			PreparedStatement ps = conn.prepareStatement(REMOVERENTALBYID);
			ps.setInt(1, rentalId);
			
			int deleted = ps.executeUpdate();
			removed = true;
			if(deleted < 0 || deleted == PreparedStatement.EXECUTE_FAILED) {
				removed = false;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return removed;
	}

	public Game getGameFromRental(int rentalId) {
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
			PreparedStatement ps = conn.prepareStatement(GETGAMEFROMRENTAL);
			ps.setInt(1, rentalId);
			
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

	public int getNumItemsRentedForCustomer(int id) {
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
			return -1;
		}
		
		int numItems = -1;
		
		try {
			PreparedStatement ps = conn.prepareStatement(GETNUMITEMSRENTEDFORCUSTOMER);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			numItems = 0;
			if(rs.next()) {
				numItems = rs.getInt(1);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numItems;
	}
}
