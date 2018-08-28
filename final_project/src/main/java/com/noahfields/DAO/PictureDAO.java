package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.Game;
import com.noahfields.Models.Picture;

@Repository
public class PictureDAO {

	
	private static final String SELECTPICTUREBYSIZEANDGAME = "SELECT * FROM Game_Pictures WHERE Picture_Size = ? AND Game_ID=?";

	public Picture getPictureOfSizeForGame(int size, int gameId) {
		
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
		
		Picture picture = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(SELECTPICTUREBYSIZEANDGAME);
			ps.setInt(1, size);
			ps.setInt(2, gameId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				picture = new Picture();
				picture.setId(rs.getInt(1));
				picture.setGame_id(rs.getInt(2));
				picture.setSize(rs.getInt(3));
				picture.setUri(rs.getString(4));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return picture;
	}
}
