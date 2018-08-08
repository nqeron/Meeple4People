package core.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import core.Models.Game;
import core.Models.Mechanic;

public class MechanicDAO {

	private static final String GETMECHANICSBYNAMEBASE = "SELECT * FROM Mechanics WHERE name in ";
	private static final String GETMECHANICSBYGAMEID = "SELECT mech.id, mech.Name, mech.Description FROM Mechanics mech join Game_Mechanics gm on mech.id = gm.Mechanic_ID WHERE gm.Game_ID = ?";
	
	public List<Mechanic> getMechanicsByName(String[] mechanicNames) {
		String SQL = GETMECHANICSBYNAMEBASE;
		
		StringJoiner mechs = new StringJoiner(", ","(", ")");
		List<String> names = new ArrayList<String>();
		for(String m: mechanicNames) {
			mechs.add("?");
			names.add(m);
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
}
