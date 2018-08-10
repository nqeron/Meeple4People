package core.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import core.Models.Designer;
import core.Models.Game;
import core.Models.Mechanic;

public class GameDAO {

	private static final String GETTOPRATEDGAMES = "Select * From topRated Where R BETWEEN ? AND ?";
	private static final String GETGAMEBYID = "SELECT * FROM Games Where id = ?";
	
	public List<Game> searchForGames(String name, int[] years, double lowCost, double highCost, double lowRating, double highRating, List<Designer> designers, List<Mechanic> mechanics ){
		
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
		
		List<Game> games = null;
		try {
			PreparedStatement ps = searchForGamesSQL(conn, name, years, lowCost, highCost, lowRating, highRating, designers, mechanics);
			ResultSet rs = ps.executeQuery();
			games = new ArrayList<Game>();
			while(rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setName(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setYear_published(rs.getInt(4));
				game.setCost_of_game(rs.getDouble(5));
				game.setAverage_Rating(rs.getDouble(6));
				games.add(game);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return games;
	}
	
	

	private PreparedStatement searchForGamesSQL(Connection conn, String name, int[] years, double lowCost, double highCost,  double lowRating, double highRating, List<Designer> designers, List<Mechanic> mechanics ) throws SQLException{
		String SQLBase = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating FROM Games game ";
		
		String Join = "";
		String Where = " WHERE ";
		int whereCount = 0;
		ArrayList<Object> wherePart = new ArrayList<Object>();
		if(designers != null && !designers.isEmpty() && designers.size() > 0) {
			Join += " JOIN Game_Desginers gd on game.id = gd.Game_ID ";
			Join += " JOIN Designers des on gd.Designer_ID = des.id ";
			
			Where += " des.id in ";
			
			StringJoiner des = new StringJoiner(", ", "(", ")");
			for(Designer d: designers) {
				des.add("?");
				wherePart.add(d.getId());
			}
			Where += des.toString();
			//wherePart[whereCount] = designers.toArray(null);
			whereCount++;
		}
		
		if(mechanics != null && !mechanics.isEmpty() && mechanics.size() > 0) {
			Join += " JOIN Game_Mechanics gm on game.id = gm.Game_ID ";
			Join += " JOIN Mechanics mech on gm.Mechanic_ID = mech.id ";
			
			if(whereCount > 0 ) {
				Where += " AND ";
			}
			Where += " mech.id in ";
			
			StringJoiner mechs = new StringJoiner(", ", "(", ")");
			for(Mechanic m: mechanics) {
				mechs.add("?");
				wherePart.add( m.getId());
			}
			Where += mechs.toString();
			whereCount++;
		}
		
		if(name != null && !name.equals("")) {
			if(whereCount > 0) {
				Where += " AND ";
			}
			Where += " game.Name LIKE ?% ";
			//wherePart[whereCount] = name;
			wherePart.add(name);
			whereCount++;
			
		}
		
		if(years != null && years.length > 0) {
			if(whereCount > 0) {
				Where += " AND ";
			}
			Where += " game.Year_Published in ";
			
			StringJoiner yrs = new StringJoiner(", ", "(", ")");
			for(int y: years) {
				yrs.add("?");
				wherePart.add(y);
			}
			Where += yrs.toString();
			whereCount++;
		}
		
		if(whereCount >0) {
			Where += " AND ";
		}
		Where += " game.Cost_of_Game > ?";
		//wherePart[whereCount++] = lowCost;
		wherePart.add(lowCost);
		if(highCost > 0) {
			Where += " AND ";
			Where += " game.Cost_of_Game < ?"; //+ highCost;
			//wherePart[whereCount++] = highCost;
			wherePart.add(highCost);
		}
		
		Where += " AND game.Average_Rating > ?"; //+ lowRating;
		wherePart.add(lowRating);
		if(highRating > 0) {
			Where += " AND game.Average_Rating < ?" + highRating;
			wherePart.add(highRating);
		}
		
		String SQL = SQLBase + Join + Where;
		PreparedStatement ps = conn.prepareStatement(SQL);

		for(int i = 1; i <= wherePart.size(); ++i) {
			Object o = wherePart.get(i-1);
			ps.setObject(i, o);
		}
		
		//for(int i = 1; i <= wherePart.size(); ++i) {
//			Object o = wherePart.get(i-1);
//			if(o instanceof Double) {
//				ps.setDouble(i, (Double) o);
//			}else if(o instanceof String) {
//				ps.setString(i, (String) o ); 
//			} else if (o instanceof Integer) {
//				ps.setInt(i, (Integer) o);
//			} else {
//				ps.setObject(i, o);
//			}
//		}
		return ps;
	}

	public List<Game> getRecommendedGames(int start){
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
		
		List<Game> games = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETTOPRATEDGAMES);
			ps.setInt(1, start);
			ps.setInt(2, start+5);
			ResultSet rs = ps.executeQuery();
			
			games = new ArrayList<Game>();
			while(rs.next()){
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setName(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setYear_published(rs.getInt(4));
				game.setCost_of_game(rs.getDouble(5));
				game.setAverage_Rating(rs.getDouble(6));
				//ignore the row number in column 7
				games.add(game);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return games;
	}



	public Game getGameByID(int id) {
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
			PreparedStatement ps = conn.prepareStatement(GETGAMEBYID);
			ps.setInt(1, id);
			
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
}
