package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.TestAbortedException;

import com.noahfields.DAO.OracleConnection;
import com.noahfields.DAO.RentalsDAO;
import com.noahfields.Models.Game;
import com.noahfields.Models.Rental;
import com.noahfields.Models.StockItem;

class RentalsDAOTest {

	private static RentalsDAO rentalsDAO;
	private static Connection conn;
	
	private static final String GETGAMEFORITEM = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating"
			+ " FROM Games game join Stock item on game.id = item.Game_ID where item.Item_ID = ?";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		rentalsDAO = new RentalsDAO();
		rentalsDAO.setAutoCommit(false);
		rentalsDAO.setKeepOpen(true);
		rentalsDAO.connect();
		
		conn = new OracleConnection().getConnection();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		rentalsDAO.rollback();
		rentalsDAO.dispose();
		rentalsDAO = null;
		
		conn.close();
		conn = null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		rentalsDAO.rollback();
	}

	private static Stream<Arguments> generateItemIds(){
		List<Arguments> arguments = new ArrayList<Arguments>();
		
		for(int i=1; i <= 20; ++i) {
			arguments.add(Arguments.of(i));
		}
		
		return arguments.stream();
	}
	
	@ParameterizedTest
	@MethodSource("generateItemIds")
	void testAddItemsToRentalsForCustomer(int itemID) throws TestAbortedException, SQLException {
		Assumptions.assumeTrue(existsItem(itemID));
		Assumptions.assumeFalse(itemInRentals(itemID));
		int customerID = 23;
		
		List<StockItem> rentalsItems = new ArrayList<StockItem>();
		StockItem item = new StockItem();
		item.setItem_id(itemID);
		rentalsItems.add(item);
		
		List<Game> expectedGames = new ArrayList<Game>();
		populateGamesFromItem(expectedGames, itemID);
		
		boolean added = rentalsDAO.addItemsToRentalsForCustomer(rentalsItems, customerID);
		
		Map<Game, Rental> gamesRented = rentalsDAO.getGamesRentedForCustomer(customerID);
		List<Game> actualGames = new ArrayList<Game>(gamesRented.keySet());
		
		for(Game expectedG: actualGames) {
			Game actG = rentalsDAO.getGameFromRental(gamesRented.get(expectedG).getId());
			Assertions.assertEquals(expectedG, actG);
		}
		
		int num = rentalsDAO.getNumItemsRentedForCustomer(customerID);
		
		Assertions.assertTrue(added);
		Assertions.assertEquals(expectedGames, actualGames);
		Assertions.assertEquals(num, 1);
		
		for(Game expectedG: actualGames) {
			boolean removed = rentalsDAO.removeRental(gamesRented.get(expectedG).getId());
			Assertions.assertTrue(removed);
		}
		
	}

	private void populateGamesFromItem(List<Game> expectedGames, int itemID) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(GETGAMEFORITEM);
		ps.setInt(1, itemID);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Game game = new Game();
			game.setId(rs.getInt(1));
			game.setName(rs.getString(2));
			game.setDescription(rs.getString(3));
			game.setYear_published(rs.getInt(4));
			game.setCost_of_game(rs.getDouble(5));
			game.setAverage_Rating(rs.getDouble(6));
			expectedGames.add(game);
		}
	}

	private boolean itemInRentals(int itemID) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Select * FROM Rentals WHERE Item_ID = ?");
		ps.setInt(1, itemID);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	private boolean existsItem(int itemID) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Select * FROM Stock WHERE Item_ID = ?");
		ps.setInt(1, itemID);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

}
