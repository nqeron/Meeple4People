package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.TestAbortedException;

import com.noahfields.DAO.OracleConnection;
import com.noahfields.DAO.ShoppingCartDAO;
import com.noahfields.Models.Game;
import com.noahfields.Models.StockItem;

class ShoppingCartDAOTest {

	private static ShoppingCartDAO shCartDAO;
	private static Connection conn;
	
	private static final String GETGAMEFORITEM = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating"
			+ " FROM Games game join Stock item on game.id = item.Game_ID where item.Item_ID = ?";
	private static final String GETNEXTAVAILABLESTOCKITEMFORGAME = "SELECT item.Item_ID, item.Game_ID, item.Status_ID, item.Serial_Number," + 
			"  item.Acquisition_Date, item.Condition_ID, item.Last_Examined FROM Stock item" + 
			"  join Games game on item.Game_ID = game.id" + 
			"  left join Shopping_Cart sc on item.Item_ID = sc.Item_ID" + 
			"  WHERE game.id = ? AND item.Status_ID = 1 and sc.Item_ID is null and ROWNUM = 1";
	private static final String ADDITEMSTORENTAL = "INSERT INTO Rentals (Customer_ID, Item_ID, Date_Rented, Due_Date) Values (?, ?, ?, ?)";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		shCartDAO = new ShoppingCartDAO();
		shCartDAO.setAutoCommit(false);
		shCartDAO.setKeepOpen(true);
		shCartDAO.connect();
		
		conn = new OracleConnection().getConnection();
		conn.setAutoCommit(false);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		shCartDAO.rollback();
		shCartDAO.dispose();
		shCartDAO = null;
		
		conn.close();
		conn = null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		conn.rollback();
		shCartDAO.rollback();
	}
	
	private static Stream<Arguments> generateItemIds(){
		List<Arguments> arguments = new ArrayList<Arguments>();
		for(int i = 1; i <=20; ++i) {
			arguments.add(Arguments.of(i));
		}
		return arguments.stream();
	}

	@ParameterizedTest
	@MethodSource("generateItemIds")
	void testGetGameForItem(int item_id) throws SQLException {
		Assumptions.assumeTrue(existsItem(item_id));
		Game expected = getGamesFromItem(item_id);
		Game actual = shCartDAO.getGameForItem(item_id);
		
		Assertions.assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@MethodSource("generateItemIds")
	void testAddItemToShoppingCartForCustomer(int item_id) throws TestAbortedException, SQLException {
		Assumptions.assumeTrue(existsItem(item_id));
		Assumptions.assumeFalse(itemInCart(item_id));
		
		int customer_id = 23;
		boolean added = shCartDAO.addItemToShoppingCartForCustomer(item_id, customer_id);
		
		Assertions.assertTrue(added);
		
		List<StockItem> actualItems = shCartDAO.getItemsInCartForCustomer(customer_id);
		Assertions.assertEquals(item_id, actualItems.get(0).getItem_id() );
		
		boolean removed = shCartDAO.removeItemFromShoppingCart(item_id, customer_id);
		Assertions.assertTrue(removed);
		
	}

	private Game getGamesFromItem(int item_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(GETGAMEFORITEM);
		ps.setInt(1, item_id);
		
		Game game = null;
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
		
		return game;
	}

	private boolean itemInCart(int item_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Select * FROM Shopping_Cart WHERE Item_ID = ?");
		ps.setInt(1, item_id);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	private boolean existsItem(int item_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Select * FROM Stock WHERE Item_ID = ?");
		ps.setInt(1, item_id);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void testGetNextAvailableStockItemForGame(int game_id) throws SQLException {
		Assumptions.assumeTrue(existsGame(game_id));
		StockItem itemActual = shCartDAO.getNextAvailableStockItemForGame(game_id);
		
		PreparedStatement ps = conn.prepareStatement(GETNEXTAVAILABLESTOCKITEMFORGAME);
		ps.setInt(1, game_id);
		
		ResultSet rs = ps.executeQuery();
		StockItem expected = null;
		if(rs.next()) {
			expected = new StockItem();
			expected.setItem_id(rs.getInt(1));
			expected.setGame_id(rs.getInt(2));
			expected.setStatus_id(rs.getInt(3));
			expected.setSerial_number(rs.getString(4));
			expected.setAcquisition_date(rs.getDate(5));
			expected.setCondition_id(rs.getInt(6));
			expected.setLast_examined(rs.getDate(7));
		}
		
		Assertions.assertEquals(expected, itemActual);
	}

	private boolean existsGame(int game_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Select * FROM Games WHERE id = ?");
		ps.setInt(1, game_id);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	@ParameterizedTest
	@MethodSource("generateItemIds")
	void testUpdateItemsInStockToRented(int item_id) throws TestAbortedException, SQLException {
		Assumptions.assumeTrue(existsItemInStock(item_id));
		List<StockItem> items = new ArrayList<StockItem>();
		StockItem item = new StockItem();
		item.setItem_id(item_id);
		items.add(item);
		boolean updated = shCartDAO.updateItemsInStockToRented(items);
		Assertions.assertTrue(updated);
	}

	private boolean existsItemInStock(int item_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Select * FROM Stock WHERE item_id = ? AND Status_ID = 1");
		ps.setInt(1, item_id);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	@ParameterizedTest
	@MethodSource("generateItemIds")
	void testRemoveItemsFromShoppingCart(int item_id) throws TestAbortedException, SQLException {
		Assumptions.assumeTrue(existsItem(item_id));
		Assumptions.assumeFalse(itemInCart(item_id));
		
		shCartDAO.addItemToShoppingCartForCustomer(item_id, 23);
		
		int num = shCartDAO.getNumItemsInCartForCustomer(23);
		Assertions.assertTrue(num >= 1);
		
		List<StockItem> items = new ArrayList<StockItem>();
		StockItem item = new StockItem();
		item.setItem_id(item_id);
		items.add(item);
		boolean removed = shCartDAO.removeItemsFromShoppingCart(items, 23);
		Assertions.assertTrue(removed);
	}

	@ParameterizedTest
	@MethodSource("generateItemIds")
	void testUpdateRentalItemsToInStock(int item_id) throws SQLException {
		//First set items to rented
		Assumptions.assumeTrue(existsItemInStock(item_id));
		List<StockItem> items = new ArrayList<StockItem>();
		StockItem item = new StockItem();
		item.setItem_id(item_id);
		items.add(item);
		boolean updated = shCartDAO.updateItemsInStockToRented(items);
		
		
		String[] rentalid = new String[] {"id"};
		//Then add to the Rentals table
		PreparedStatement ps = conn.prepareStatement(ADDITEMSTORENTAL, rentalid);
		ps.setInt(1, 23); //customer_id - test
		ps.setInt(2, item_id);
		Date today = new java.sql.Date( Calendar.getInstance().getTime().getTime() );
		LocalDate fiveWeeks = today.toLocalDate().plusWeeks(5);
		Date dueDate = Date.valueOf(fiveWeeks);
		ps.setDate(3, today);
		ps.setDate(4, dueDate);
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int rentalId = 0;
		if(rs.next()) {
			rentalId = rs.getInt(1);
		}
		
		updated = shCartDAO.updateRentalItemsToInStock(rentalId);
		Assertions.assertTrue(updated);
	}

	@Test
	void testGetNumItemsInCartForCustomer() {
		//fail("Not yet implemented");
	}

}
