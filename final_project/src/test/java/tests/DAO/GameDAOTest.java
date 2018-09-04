package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.noahfields.DAO.GameDAO;
import com.noahfields.DAO.OracleConnection;
import com.noahfields.Models.Game;

class GameDAOTest {

	private static GameDAO gameDao;
	private static Connection conn;
	
	PreparedStatement ps;
	
	private static final String GETTOPRATEDGAMES = "Select * From topRated Where R BETWEEN ? AND ?";
	private static final String GETGAMEBYID = "SELECT * FROM Games Where id = ?";
	private static final String GETNUMGAMES = "SELECT Count(id) FROM Games";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gameDao = new GameDAO();
		gameDao.setKeepOpen(true);
		gameDao.connect();
		conn = new OracleConnection().getConnection();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		gameDao.dispose();
		gameDao = null;
		conn.close();
		conn = null;
	}
	
	@BeforeEach
	void setUp() {
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		if(ps != null) ps.close();
	}
	
	@Test
	void testGetRecommendedFromBeginning() throws Exception {
		List<Game> actual = GameDAOTest.gameDao.getRecommendedGames(1);
		List<Game> expected = new ArrayList<Game>();
		
		ps = conn.prepareStatement(GETTOPRATEDGAMES);
		ps.setInt(1, 1);
		ps.setInt(2, 6);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Game game = new Game();
			game.setId(rs.getInt(1));
			game.setName(rs.getString(2));
			game.setDescription(rs.getString(3));
			game.setYear_published(rs.getInt(4));
			game.setCost_of_game(rs.getDouble(5));
			game.setAverage_Rating(rs.getDouble(6));
			expected.add(game);
		}
		
		//System.out.println(actual.toString());
		//fail("Not yet implemented " + actual.toString());
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void testGetRecommendedFromMiddle() throws SQLException{
		List<Game> actual = GameDAOTest.gameDao.getRecommendedGames(3);
		List<Game> expected = new ArrayList<Game>();
		
		ps = conn.prepareStatement(GETTOPRATEDGAMES);
		ps.setInt(1, 3);
		ps.setInt(2, 8);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Game game = new Game();
			game.setId(rs.getInt(1));
			game.setName(rs.getString(2));
			game.setDescription(rs.getString(3));
			game.setYear_published(rs.getInt(4));
			game.setCost_of_game(rs.getDouble(5));
			game.setAverage_Rating(rs.getDouble(6));
			expected.add(game);
		}
	
		
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void testGetRecommendedFromEnd() throws SQLException {
		List<Game> actual = GameDAOTest.gameDao.getRecommendedGames(7);
		List<Game> expected = new ArrayList<Game>();
		
		ps = conn.prepareStatement(GETTOPRATEDGAMES);
		ps.setInt(1, 7);
		ps.setInt(2, 12);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Game game = new Game();
			game.setId(rs.getInt(1));
			game.setName(rs.getString(2));
			game.setDescription(rs.getString(3));
			game.setYear_published(rs.getInt(4));
			game.setCost_of_game(rs.getDouble(5));
			game.setAverage_Rating(rs.getDouble(6));
			expected.add(game);
		}
		
		Assertions.assertEquals(expected, actual);
	}
	
	
	@ParameterizedTest
	@ValueSource( ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void testGetGameByID(int gameId) throws SQLException {
		Game actual = gameDao.getGameByID(gameId);
		
		ps = conn.prepareStatement(GETGAMEBYID);
		ps.setInt(1, gameId);
		
		ResultSet rs = ps.executeQuery();
		Game game = null;
		if(rs.next()) {
			game = new Game();
			game.setId(rs.getInt(1));
			game.setName(rs.getString(2));
			game.setDescription(rs.getString(3));
			game.setYear_published(rs.getInt(4));
			game.setCost_of_game(rs.getDouble(5));
			game.setAverage_Rating(rs.getDouble(6));
		}
		
		Assertions.assertEquals(game, actual);
		
	}
	
	@Test
	void testGetNumGames() throws SQLException {
		
		int actual = gameDao.getNumGames();
		
		ps = conn.prepareStatement(GETNUMGAMES);
		ResultSet rs = ps.executeQuery();
		int expected = 0;
		if(rs.next()) {
			expected = rs.getInt(1);
		}
		
		Assertions.assertEquals(expected, actual);
	}

}
