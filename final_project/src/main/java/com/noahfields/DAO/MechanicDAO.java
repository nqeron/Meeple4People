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

import com.noahfields.Models.Game;
import com.noahfields.Models.Mechanic;

@Repository
public class MechanicDAO {

	private static final String GETMECHANICSBYNAMEBASE = "SELECT * FROM Mechanics WHERE UPPER(Name) in ";
	private static final String GETMECHANICSBYGAMEID = "SELECT mech.id, mech.Name, mech.Description FROM Mechanics mech join Game_Mechanics gm on mech.id = gm.Mechanic_ID WHERE gm.Game_ID = ?";
	private static final String GETMECHANICBYID = "SELECT * FROM Mechanics WHERE id = ?";
	
	
	/**
	 * Queries the database for mechanics for the associated names given
	 * @param mechanicNames: names of mechanics to search for
	 * @return mechanics for given names
	 */
	public List<Mechanic> getMechanicsByName(String[] mechanicNames) {
		if(mechanicNames == null || mechanicNames.length <=0 || Arrays.equals(mechanicNames,new String[] {""})) {
			return null;
		}
		
		String SQL = GETMECHANICSBYNAMEBASE;
		
		StringJoiner mechs = new StringJoiner(", ","(", ")");
		List<String> names = new ArrayList<String>();
		for(String m: mechanicNames) {
			mechs.add("UPPER(?)");
			names.add(m.trim());
		}
		SQL += mechs.toString();
		
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
		
		
		List<Mechanic> mechanics = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			for (int i = 1; i<= names.size(); ++i) {
				String mechName = names.get(i-1);
				ps.setString(i, mechName);
			}
			ResultSet rs = ps.executeQuery();
			
			mechanics = new ArrayList<Mechanic>();
			while(rs.next()) {
				Mechanic mechanic = new Mechanic();
				mechanic.setId(rs.getInt(1));
				mechanic.setName(rs.getString(2));
				mechanic.setDescription(rs.getString(3));
				mechanics.add(mechanic);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mechanics;
	}

	/**
	 * Queries the database for mechanics for a particular game
	 * @param game: game to get the mechanics for
	 * @return list of mechanics for the given game
	 */
	public List<Mechanic> getMechanicsForGame(Game game){
		//gets the mechanics for a specified game
		
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
		
		
		List<Mechanic> mechanics = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETMECHANICSBYGAMEID);
			ps.setInt(1, game.getId());
			ResultSet rs = ps.executeQuery();
			
			mechanics = new ArrayList<Mechanic>();
			while(rs.next()) {
				Mechanic mechanic = new Mechanic();
				mechanic.setId(rs.getInt(1));
				mechanic.setName(rs.getString(2));
				mechanic.setDescription(rs.getString(3));
				mechanics.add(mechanic);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mechanics;
	}

	/**
	 * Queries the database for a particular mechanic
	 * @param id: id of mechanic to get
	 * @return mechanic for given id
	 */
	public Mechanic getMechanicByID(int id) {
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
		
		Mechanic mechanic = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(GETMECHANICBYID);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				mechanic = new Mechanic();
				mechanic.setId(rs.getInt(1));
				mechanic.setName(rs.getString(2));
				mechanic.setDescription(rs.getString(3));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mechanic;
	}
}
