package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.Designer;
import com.noahfields.Models.Game;

@Repository
public class DesignerDAO {

	private static final String GETDESIGNERSFORGAME = "SELECT des.id, des.First_name, des.Last_name, des.Website FROM Designers des join Game_Designers gd on des.id = gd.Designer_ID WHERE gd.Game_ID = ?";
	
	/**
	 * @param designerNames: names of designers to search for - "First Last" 
	 * @return list of designers with given names
	 */
	public List<Designer> getDesignersByNames(String[] designerNames) {
		
		if(designerNames == null || designerNames.length <=0 || Arrays.equals(designerNames,new String[] {""})) {
			return null;
		}
		// TODO Auto-generated method stub
		String SQLBase = "SELECT * FROM Designers WHERE ";
		
		//StringBuilder whereClause = new StringBuilder(designerNames.length * designerNames[0].length);
		StringJoiner whereClause = new StringJoiner(" OR ");
		List<String> names = new ArrayList<String>();
		for(String des: designerNames) {
			String[] designer = des.trim().split(" ");
			if(designer == null || designer.length <= 0 || des.isEmpty()) {
				continue;
			}
			
			if(designer.length >= 2) {
				whereClause.add("( First_Name = ? AND Last_Name = ?)");
				names.add(designer[0]);
				names.add(designer[1]);
			} else {
				whereClause.add("UPPER(First_Name) LIKE UPPER(?) OR UPPER(LAST_Name) LIKE UPPER(?)");
				names.add("%"+designer[0]+"%");
				names.add("%"+designer[0]+"%");
			}
		}
		
		String SQL = SQLBase + whereClause.toString();
		
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
	
		List<Designer> designers = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			for (int i = 1; i <= names.size(); ++i) {
				String name = names.get(i-1);
				ps.setString(i, name);
			}
			ResultSet rs = ps.executeQuery();
			designers = new ArrayList<Designer>();
			while(rs.next()) {
				Designer designer = new Designer();
				designer.setId(rs.getInt(1));
				designer.setFirst_name(rs.getString(2));
				designer.setLast_name(rs.getString(3));
				designer.setWebsite(rs.getString(4));
				designers.add(designer);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return designers;
	}

	/**
	 * @param game: game to search for designers for
	 * @return list of designers for a given game
	 */
	public List<Designer> getDesignersForGame(Game game){
		
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
	
		List<Designer> designers = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETDESIGNERSFORGAME);
			ps.setInt(1, game.getId());
			ResultSet rs = ps.executeQuery();
			designers = new ArrayList<Designer>();
			while(rs.next()) {
				Designer designer = new Designer();
				designer.setId(rs.getInt(1));
				designer.setFirst_name(rs.getString(2));
				designer.setLast_name(rs.getString(3));
				designer.setWebsite(rs.getString(4));
				designers.add(designer);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return designers;
	}

}
