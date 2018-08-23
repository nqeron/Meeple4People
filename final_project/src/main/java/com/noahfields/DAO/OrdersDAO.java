package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.StockItem;

@Repository
public class OrdersDAO {
	
	private static final String INSERTORDERFORCUSTOMER = "INSERT INTO Orders (Order_ID, Item_ID, Customer_ID, Date_Shipped) VALUES (?, ?, ?, NULL)";
	private static final String GETNEXTORDERID = "SELECT MAX(Order_ID) + 1 FROM Orders";

	public int addItemsToOrderForCustomer(List<StockItem> rentalsItems, int customerID) {
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
		
		int id = -1;
		
		try {
			PreparedStatement ps = conn.prepareStatement(GETNEXTORDERID);
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
}
