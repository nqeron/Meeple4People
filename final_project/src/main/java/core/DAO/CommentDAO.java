package core.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Models.Comment;

public class CommentDAO {
	
	private static final String GETTOPCOMMENTSFORGAME = "SELECT Game_ID, Customer_ID, Comment_Text, Rating, Comment_Date, ROW_NUMBER() OVER (order by Rating DESC) R FROM Game_Comments WHERE Game_ID = ?";
	private static final String GETSTARTCOMMENTS = "SELECT * FROM (" + GETTOPCOMMENTSFORGAME + ") WHERE R BETWEEN ? and ?";
	
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

}
