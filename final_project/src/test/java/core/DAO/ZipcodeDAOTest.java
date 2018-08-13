package core.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.noahfields.DAO.ZipcodeDAO;
import com.noahfields.Models.Zipcode;

import oracle.jdbc.proxy.annotation.Methods;

class ZipcodeDAOTest {

	private static ZipcodeDAO zipcodeDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		zipcodeDao = new ZipcodeDAO();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		zipcodeDao = null;
	}


	@ParameterizedTest
	@MethodSource("getZipcodes")
	void testGetZipcode(int zip, Zipcode expected) {
		//fail("Not yet implemented");
		Zipcode actual  = zipcodeDao.getZipcode(zip);
		Assertions.assertEquals(expected, actual);
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

}
