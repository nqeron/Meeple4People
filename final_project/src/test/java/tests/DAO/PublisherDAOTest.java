package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.noahfields.DAO.OracleConnection;
import com.noahfields.DAO.PublisherDAO;
import com.noahfields.Models.Publisher;

class PublisherDAOTest {

	private static PublisherDAO publisherDAO;
	private static Connection conn;
	
	private PreparedStatement ps;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		publisherDAO = new PublisherDAO();
		publisherDAO.setKeepOpen(true);
		publisherDAO.connect();
		conn = new OracleConnection().getConnection();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		publisherDAO.dispose();
		publisherDAO = null;
		conn.close();
		conn = null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		if(ps != null) ps.close();
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void testGetPublisherForGameID(int gameId) throws SQLException {
		Assumptions.assumeTrue(existsGame(gameId));
		List<Publisher> actual = publisherDAO.getPublisherForGameID(gameId);
		
		ps = conn.prepareStatement("SELECT pub.id, pub.Name, pub.Website FROM Publishers pub join Game_Publishers gp on pub.id = gp.Publisher_ID WHERE gp.Game_ID = ?");
		ps.setInt(1, gameId);
		
		ResultSet rs = ps.executeQuery();
		List<Publisher> publishers = null;
		
		publishers = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setId(rs.getInt(1));
			publisher.setName(rs.getString(2));
			publisher.setWebsite(rs.getString(3));
			publishers.add(publisher);
		}
		
		Assertions.assertEquals(publishers, actual);
	}

	private boolean existsGame(int gameId) throws SQLException {
		ps = conn.prepareStatement("Select * FROM Games WHERE id = ?");
		ps.setInt(1, gameId);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
	}

	@Test
	void testGetPublishersByName() {
		List<Publisher> actual =publisherDAO.getPublishersByName(new String[] {"orange", "stone"});
		List<Publisher> expected = new ArrayList<Publisher>();
		expected.add(new Publisher(2, "Blue Orange Games", "http://www.blueorangegames.com/"));
		expected.add(new Publisher(8,"Stonemaier Games", "http://www.stonemaiergames.com/"));
		
		Assertions.assertEquals(expected, actual);
	}

}
