package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.Comment;

@Repository
public class CommentDAO {
	
	private static final String GETTOPCOMMENTSFORGAME = "SELECT Game_ID, Customer_ID, Comment_Text, Rating, Comment_Date, ROW_NUMBER() OVER (order by Rating DESC) R FROM Game_Comments WHERE Game_ID = ?";
	private static final String GETSTARTCOMMENTS = "SELECT * FROM (" + GETTOPCOMMENTSFORGAME + ") WHERE R BETWEEN ? and ?";
	private static final String HASCOMMENTFORRENTAL = "SELECT * FROM Game_Comments gc WHERE gc.Customer_ID = ? AND gc.Game_ID = ?";
	private static final String INSERTREVIEW = "INSERT INTO Game_Comments VALUES (?, ?, ?, ?, ?)";
	
	public List<Comment> getCommentsForGame(int id, int start){
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
		
		List<Comment> comments = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETSTARTCOMMENTS);
			ps.setInt(1, id);
			ps.setInt(2, start);
			ps.setInt(3, start+6);
			
			ResultSet rs = ps.executeQuery();
			
			comments = new ArrayList<Comment>();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setGameId(rs.getInt(1));
				comment.setCustomerId(rs.getInt(2));
				comment.setComment_text(rs.getString(3));
				comment.setRating(rs.getDouble(4));
				comment.setComment_date(rs.getDate(5));
				comments.add(comment);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}

	public boolean customerHasCommentsForGame(int customerId, int gameId) {
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
		
		boolean hasComment = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(HASCOMMENTFORRENTAL);
			ps.setInt(1, customerId);
			ps.setInt(2, gameId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				hasComment = true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hasComment;
	}

	public boolean addReviewForCustomerToGame(int customerId, int gameId, double ratingVal, String commentText) {
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
			PreparedStatement ps = conn.prepareStatement(INSERTREVIEW);
			ps.setInt(1, gameId);
			ps.setInt(2, customerId);
			ps.setString(3, commentText);
			ps.setDouble(4, ratingVal);
			ps.setDate(5, new Date(Calendar.getInstance().getTime().getTime()));
			
			int updated = ps.executeUpdate();
			if(updated > 0 && updated != PreparedStatement.EXECUTE_FAILED) {
				added = true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return added;
	}

}
