package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.noahfields.DAO.OrdersDAO;
import com.noahfields.Models.Game;
import com.noahfields.Models.StockItem;

class OrdersDAOTest {
	
	private static OrdersDAO ordersDAO;
	private static Connection conn;
	private PreparedStatement ps;
	
	private static final String GETGAMESFORORDER = "SELECT game.id, game.Name, game.Description, game.Year_Published, game.Cost_of_Game, game.Average_Rating FROM Games game"
			+ " JOIN Stock item on game.id = item.Game_ID JOIN Orders ord on item.Item_ID = ord.Item_ID WHERE ord.Order_ID = ?";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ordersDAO = new OrdersDAO();
		ordersDAO.setAutoCommit(false);
		ordersDAO.setKeepOpen(true);
		ordersDAO.connect();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		ordersDAO.rollback();
		ordersDAO.dispose();
		ordersDAO = null;
		
		
		conn = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		conn = new OracleConnection().getConnection();
		conn.setAutoCommit(false);
	}

	@AfterEach
	void tearDown() throws Exception {
		conn.rollback();
		conn.close();
		ordersDAO.rollback();
		if(ps != null) ps.close();
	}

	private static Stream<Arguments> generateItemIdsAndCustomerIds() {
		List<Arguments> arguments = new ArrayList<>();
		for(int i = 1; i <= 20; ++i) {
			for(int j = 1; j <= 47; ++j) {
				Arguments arg = Arguments.of(i,j);
				arguments.add(arg);
			}
		}
		return arguments.stream();
	}
	
	@ParameterizedTest
	@MethodSource("generateItemIdsAndCustomerIds")
	void testAddItemsToOrderForCustomer(int itemID,int customerID) throws TestAbortedException, SQLException {
		Assumptions.assumeTrue(customerExists(customerID));
		Assumptions.assumeTrue(itemExists(itemID));
		Assumptions.assumeFalse(itemInOrders(itemID));
		List<StockItem> rentalsItems = new ArrayList<StockItem>();
		StockItem item = null;
		ps = conn.prepareStatement("SELECT * FROM Stock WHERE Item_ID = ?");
		ps.setInt(1, itemID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			item = new StockItem();
			item.setItem_id(rs.getInt(1));
			item.setGame_id(rs.getInt(2));
			item.setStatus_id(rs.getInt(3));
			item.setSerial_number(rs.getString(4));
			item.setAcquisition_date(rs.getDate(5));
			item.setCondition_id(rs.getInt(6));
			item.setLast_examined(rs.getDate(7));
		}
		rentalsItems.add(item);
		int id = ordersDAO.addItemsToOrderForCustomer(rentalsItems, customerID);
		
		Assertions.assertTrue(id > 0);
	}
	
	private boolean itemInOrders(int itemID) throws SQLException {
		ps = conn.prepareStatement("Select * FROM Orders WHERE Item_ID = ?");
		ps.setInt(1, itemID);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	private boolean itemExists(int itemID) throws SQLException{
		ps = conn.prepareStatement("Select * FROM Stock WHERE Item_ID = ?");
		ps.setInt(1, itemID);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	private boolean customerExists(int customerId) throws SQLException {
		ps = conn.prepareStatement("Select * FROM Customers WHERE id = ?");
		ps.setInt(1, customerId);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	@ParameterizedTest
	@ValueSource( ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void testGetGamesForOrder(int orderID) throws SQLException {
		Assumptions.assumeTrue(existsOrder(orderID));
		List<Game> gamesActual = ordersDAO.getGamesForOrder(orderID);
		
		ps = conn.prepareStatement(GETGAMESFORORDER);
		ps.setInt(1, orderID);
		
		ResultSet rs = ps.executeQuery();
		
		List<Game> expected = new ArrayList<Game>();
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
		
		Assertions.assertEquals(expected, gamesActual);
	}

	private boolean existsOrder(int orderID) throws SQLException {
		ps = conn.prepareStatement("Select * FROM Orders WHERE Order_ID = ?");
		ps.setInt(1, orderID);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

}
