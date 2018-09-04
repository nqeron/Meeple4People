package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.noahfields.DAO.PictureDAO;
import com.noahfields.Models.Picture;

class PictureDAOTest {
	
	private static PictureDAO pictureDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pictureDAO = new PictureDAO();
		pictureDAO.setKeepOpen(true);
		pictureDAO.connect();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		pictureDAO.dispose();
		pictureDAO = null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	private static Stream<Arguments> generateGameIdsAndPictures() {
		List<Arguments> arguments = new ArrayList<Arguments>();
		
		arguments.add(Arguments.of(5, new Picture(1, 5, 2, "viticulture-medium.jpg")));
		arguments.add(Arguments.of(3, new Picture(2, 3, 2, "fivetribes-medium.jpg")));
		arguments.add(Arguments.of(8, new Picture(3, 8, 2, "themind-medium.jpg")));
		arguments.add(Arguments.of(2, new Picture(4, 2, 2, "kingdomino-medium.jpg")));
		return arguments.stream();
	}
	
	@ParameterizedTest
	@MethodSource("generateGameIdsAndPictures")
	void testGetPictureOfSizeForGame(int gameId, Picture expected) {
		Picture actual = pictureDAO.getPictureOfSizeForGame(2, gameId);
		Assertions.assertEquals(expected, actual);
	}

}
