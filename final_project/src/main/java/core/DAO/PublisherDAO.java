package core.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Models.Publisher;

public class PublisherDAO {
	
	private static final String GETPUBLISHERFORGAMEID = "SELECT pub.id, pub.Name, pub.Website FROM Publishers pub join Game_Publishers gp on pub.id = gp.Publisher_ID WHERE gp.Game_ID = ?";
	
	/** 
	 * Method that queries the database for publishers associated with a specific game.
	 * @param id: for game to return publishers for
	 * @return publishers for a given game
	 */
	public List<Publisher> getPublisherForGameID(int id){
		
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
		
		List<Publisher> publishers = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETPUBLISHERFORGAMEID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			publishers = new ArrayList<Publisher>();
			while(rs.next()) {
				Publisher publisher = new Publisher();
				publisher.setId(rs.getInt(1));
				publisher.setName(rs.getString(2));
				publisher.setWebsite(rs.getString(3));
				publishers.add(publisher);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return publishers;
	}

}
