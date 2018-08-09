package core.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.Models.Zipcode;

public class ZipcodeDAO {

	private static final String GETZIPCODE = "SELECT * FROM Zipcodes WHERE Zipcode = ?";
	
	public Zipcode getZipcode(int zip) {
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
		
		Zipcode zipcode = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(GETZIPCODE);
			ps.setInt(1, zip);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				zipcode = new Zipcode();
				zipcode.setZipcode(rs.getInt(1));
				zipcode.setCity(rs.getString(2));
				zipcode.setState(rs.getString(3));
				zipcode.setCountry(rs.getString(4));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zipcode;
	}
}
