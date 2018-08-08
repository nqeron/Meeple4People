/**
 * 
 */
package core.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import core.Models.Game;
import core.Models.Mechanic;

/**
 * @author Students
 *
 */
class MechanicDAOTest {

	private static MechanicDAO mechanicDao;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mechanicDao = new MechanicDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		mechanicDao = null;
	}

	/**
	 * Test method for {@link core.DAO.MechanicDAO#getMechanicsByName(java.lang.String[])}.
	 */
	@Test
	void testGetMechanicsByName() {
		List<Mechanic> actual = mechanicDao.getMechanicsByName(new String[] {"Worker Placement", "Set Collection"});
		List<Mechanic> expected = new ArrayList<Mechanic>();
		expected.add(new Mechanic(1, "Worker Placement", "players select individual actions from a set of actions available to all players."));
		expected.add(new Mechanic(8, "Set Collection", "The primary goal of a set collection mechanic is to encourage a player to collect a set of items."));
		
		Assertions.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link core.DAO.MechanicDAO#getMechanicsForGame(core.Models.Game)}.
	 */
	@Test
	void testGetMechanicsForGame() {
		//fail("Not yet implemented");
		Game g = new Game();
		g.setId(5);
		// don't care about any other part of the game
		List<Mechanic> actual = mechanicDao.getMechanicsForGame(g);
		List<Mechanic> expected = new ArrayList<Mechanic>();
		expected.add(new Mechanic(1, "Worker Placement", "players select individual actions from a set of actions available to all players."));
		expected.add(new Mechanic(11, "Hand Management", "Hand management games are games with cards in them that reward players for playing the cards in certain sequences or groups."));
		expected.add(new Mechanic(15, "Variable Phase Order",  "Variable Phase Order implies that turns may not be played the same way as before and / or after"));
		
		Assertions.assertEquals(expected, actual);
	}

}
