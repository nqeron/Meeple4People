package core.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.noahfields.DAO.GameDAO;
import com.noahfields.Models.Game;

class GameDAOTest {

	private static GameDAO gameDao;
	
	@BeforeAll
	static void setUp() {
		gameDao = new GameDAO();
	}
	
	@Test
	void testGetRecommendedFromBeginning() {
		List<Game> actual = GameDAOTest.gameDao.getRecommendedGames(1);
		List<Game> expected = new ArrayList<Game>();
		expected.add(new Game(1, "Le Havre", "The gameplay takes place in the harbour of Le Havre, where players take goods such as fish and wood from the wharves. These goods are used either to feed the players' community, to construct buildings and ships, or are processed into finished goods.", 2008, 70, 1.48));
		expected.add(new Game(10, "Scythe", "In Scythe, each player represents a character from one of five factions of Eastern Europe who are attempting to earn their fortune and claim their faction's stake in the land around the mysterious Factory. Players conquer territory, enlist new recruits, reap resources, gain villagers, build structures, and activate monstrous mechs.", 2016, 80, 2.15));
		expected.add(new Game(6, "Tzolk'in: The Mayan Calendar", "Tzolkin: The Mayan Calendar presents a new game mechanism: dynamic worker placement. Players representing different Mayan tribes place their workers on giant connected gears, and as the gears rotate they take the workers to different action spots.", 2012, 34, 2.24));
		expected.add(new Game(7, "Azul", "In the game Azul, players take turns drafting colored tiles from suppliers to their player board. Later in the round, players score points based on how they've placed their tiles to decorate the palace. Extra points are scored for specific patterns and completing sets; wasted supplies harm the player's score. The player with the most points at the end of the game wins.", 2017, 30, 2.43));
		expected.add(new Game(4, "Pandemic", "In Pandemic, several virulent diseases have broken out simultaneously all over the world! The players are disease-fighting specialists whose mission is to treat disease hotspots while researching cures for each of four plagues before they get out of hand.", 2008, 36, 2.69));
		expected.add(new Game(9, "Caverna: The Cave Farmers", "In the game, you are the bearded leader of a small dwarf family that lives in a little cave in the mountains. You begin the game with a farmer and his spouse, and each member of the farming family represents an action that the player can take each turn. Together, you cultivate the forest in front of your cave and dig deeper into the mountain. You furnish the caves as dwellings for your offspring as well as working spaces for small enterprises.",	2013, 97, 2.76));
	
		
		//System.out.println(actual.toString());
		//fail("Not yet implemented " + actual.toString());
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void testGetRecommendedFromMiddle() {
		List<Game> actual = GameDAOTest.gameDao.getRecommendedGames(3);
		List<Game> expected = new ArrayList<Game>();
		expected.add(new Game(6, "Tzolk'in: The Mayan Calendar", "Tzolkin: The Mayan Calendar presents a new game mechanism: dynamic worker placement. Players representing different Mayan tribes place their workers on giant connected gears, and as the gears rotate they take the workers to different action spots.", 2012, 34, 2.24));
		expected.add(new Game(7, "Azul", "In the game Azul, players take turns drafting colored tiles from suppliers to their player board. Later in the round, players score points based on how they've placed their tiles to decorate the palace. Extra points are scored for specific patterns and completing sets; wasted supplies harm the player's score. The player with the most points at the end of the game wins.", 2017, 30, 2.43));
		expected.add(new Game(4, "Pandemic", "In Pandemic, several virulent diseases have broken out simultaneously all over the world! The players are disease-fighting specialists whose mission is to treat disease hotspots while researching cures for each of four plagues before they get out of hand.", 2008, 36, 2.69));
		expected.add(new Game(9, "Caverna: The Cave Farmers", "In the game, you are the bearded leader of a small dwarf family that lives in a little cave in the mountains. You begin the game with a farmer and his spouse, and each member of the farming family represents an action that the player can take each turn. Together, you cultivate the forest in front of your cave and dig deeper into the mountain. You furnish the caves as dwellings for your offspring as well as working spaces for small enterprises.",	2013, 97, 2.76));
		expected.add(new Game(2, "Kingdomino", "In Kingdomino, you are a Lord seeking new lands in which to expand your kingdom. You must explore all the lands, wheat fields, lakes, and mountains in order to spot the best plots. But be careful as some other Lords also covet these lands...", 2016, 20, 3));
		expected.add(new Game(8, "The Mind", "The Mind is more than just a game. It's an experiment, a journey, a team experience in which you can't exchange information, yet will become one to defeat all the levels of the game.", 2018, 25, 3.38));
	
		
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void testGetRecommendedFromEnd() {
		List<Game> actual = GameDAOTest.gameDao.getRecommendedGames(7);
		List<Game> expected = new ArrayList<Game>();
		expected.add(new Game(2, "Kingdomino", "In Kingdomino, you are a Lord seeking new lands in which to expand your kingdom. You must explore all the lands, wheat fields, lakes, and mountains in order to spot the best plots. But be careful as some other Lords also covet these lands...", 2016, 20, 3));
		expected.add(new Game(8, "The Mind", "The Mind is more than just a game. It's an experiment, a journey, a team experience in which you can't exchange information, yet will become one to defeat all the levels of the game.", 2018, 25, 3.38));
		expected.add(new Game(3, "Five Tribes", "Crossing into the Land of 1001 Nights, your caravan arrives at the fabled Sultanate of Naqala. The old sultan just died and control of Naqala is up for grabs! The oracles foretold of strangers who would maneuver the Five Tribes to gain influence over the legendary city-state. Will you fulfill the prophecy? Invoke the old Djinns and move the Tribes into position at the right time, and the Sultanate may become yours!", 2014, 41, 3.44));
		expected.add(new Game(5, "Viticulture Essentials Edition", "In Viticulture, the players find themselves in the roles of people in rustic, pre-modern Tuscany who have inherited meager vineyards. They have a few plots of land, an old crushpad, a tiny cellar, and three workers. They each have a dream of being the first to call their winery a true success.", 2015, 60, 4.5));
		
		Assertions.assertEquals(expected, actual);
	}
	
	@AfterAll
	static void tearDown() {
		gameDao = null;
	}

}
