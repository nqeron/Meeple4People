package tests.DAO;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectClasses({CommentDAOTest.class, CustomerDAOTest.class, DesignerDAOTest.class, GameDAOTest.class, MechanicDAOTest.class, OrdersDAOTest.class, 
				PictureDAOTest.class, PublisherDAOTest.class, RentalsDAOTest.class, ShoppingCartDAOTest.class, ZipcodeDAOTest.class})
class DAOTestSuite {

}
