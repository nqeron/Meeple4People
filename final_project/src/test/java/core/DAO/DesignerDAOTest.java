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

import core.Models.Designer;
import core.Models.Game;

/**
 * @author Students
 *
 */
class DesignerDAOTest {

	
	private static DesignerDAO designerDao;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		designerDao = new DesignerDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		designerDao = null;
	}

	/**
	 * Test method for {@link core.DAO.DesignerDAO#getDesignersByNames(java.lang.String[][])}.
	 */
	@Test
	void testGetDesignersByNames() {
		List<Designer> actual = designerDao.getDesignersByNames(new String[][] { {"Uwe"}, {"Jamey", "Stegmaier"} });
		List<Designer> expected = new ArrayList<Designer>();
		expected.add(new Designer(1, "Uwe",	"Rosenberg", null));
		expected.add(new Designer(4, "Jamey", "Stegmaier", "http://jameystegmaier.com/"));
		
		Assertions.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link core.DAO.DesignerDAO#getDesignersForGame(core.Models.Game)}.
	 */
	@Test
	void testGetDesignersForGame() {
		Game g = new Game();
		g.setId(5);
		//id is all we care about
		List<Designer> actual = designerDao.getDesignersForGame(g);
		List<Designer> expected = new ArrayList<Designer>();
		expected.add(new Designer(4, "Jamey", "Stegmaier", "http://jameystegmaier.com/"));
		expected.add(new Designer(5, "Alan", "Stone", null));
		expected.add(new Designer(6, "Morten", "Pedersen", null));
		
		Assertions.assertEquals(expected, actual);
	}

}
