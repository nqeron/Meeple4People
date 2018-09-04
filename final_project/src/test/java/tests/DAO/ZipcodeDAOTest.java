package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.noahfields.DAO.OracleConnection;
import com.noahfields.DAO.ZipcodeDAO;
import com.noahfields.Models.Zipcode;

import oracle.jdbc.proxy.annotation.Methods;

class ZipcodeDAOTest {

	private static ZipcodeDAO zipcodeDao;
	private static Connection conn;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		zipcodeDao = new ZipcodeDAO();
		zipcodeDao.setKeepOpen(true);
		zipcodeDao.connect();
		
		conn = new OracleConnection().getConnection();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		zipcodeDao.dispose();
		zipcodeDao = null;
		
		conn.close();
		conn = null;
	}


	private static Stream<Arguments> getZipcodes() {
		return Stream.of(
			Arguments.of(Integer.parseInt("07006"), new Zipcode(7006, "Caldwell", "NJ", "USA")),
			Arguments.of(7006, new Zipcode(7006, "Caldwell", "NJ", "USA")),
			Arguments.of(7952, new Zipcode(7952, "Bernardsville", "NJ", "USA")),
			Arguments.of(12517, new Zipcode(12517, "Copake Falls", "NY", "USA")),
			Arguments.of(9001, new Zipcode(9001, "Nowhere", "KY", "USA")),
			Arguments.of(7005, new Zipcode(7005, "Boonton", "NJ", "USA"))
			);
	}
	
	@ParameterizedTest
	@MethodSource("getZipcodes")
	void testGetZipcode(int zip, Zipcode expected) {
		//fail("Not yet implemented");
		Zipcode actual  = zipcodeDao.getZipcode(zip);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void testGetAllZipcodes() throws SQLException {
		List<Zipcode> actual = zipcodeDao.getAllZips();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * From Zipcodes");
		ResultSet rs = ps.executeQuery();
		List<Zipcode> expected = new ArrayList<Zipcode>();
		while(rs.next()) {
			Zipcode zip = new Zipcode();
			zip.setZipcode(rs.getInt(1));
			zip.setCity(rs.getString(2));
			zip.setState(rs.getString(3));
			zip.setCountry(rs.getString(4));
			expected.add(zip);
		}
		
		Assertions.assertEquals(expected, actual);
	}
	
	

}
