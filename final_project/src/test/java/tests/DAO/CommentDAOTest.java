package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

import com.noahfields.DAO.CommentDAO;
import com.noahfields.DAO.CustomerDAO;
import com.noahfields.DAO.OracleConnection;
import com.noahfields.Models.Comment;

class CommentDAOTest {
	
	static CommentDAO commentDAO;
	static Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	private static final String GETTOPCOMMENTSFORGAME = "SELECT Game_ID, Customer_ID, Comment_Text, Rating, Comment_Date, ROW_NUMBER() OVER (order by Rating DESC) R FROM Game_Comments WHERE Game_ID = ?";
	private static final String GETSTARTCOMMENTS = "SELECT * FROM (" + GETTOPCOMMENTSFORGAME + ") WHERE R BETWEEN ? and ?";
	private static final String HASCOMMENTFORRENTAL = "SELECT * FROM Game_Comments gc WHERE gc.Customer_ID = ? AND gc.Game_ID = ?";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		commentDAO = new CommentDAO();
		commentDAO.setAutoCommit(false);
		conn = new OracleConnection().getConnection();
		conn.setAutoCommit(false);
		commentDAO.connect();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		commentDAO.dispose();
		commentDAO = null;
		conn.rollback();
		conn.close();
		conn = null;
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@AfterEach
	void tearDown() throws Exception{
		ps.close();
		rs.close();
		commentDAO.rollback();
	}

	private static Stream<Arguments> generateGameIdsAndStarts(){
		
		List<Arguments> arguments = new ArrayList<Arguments>();
		for(int i = 1; i<=10; ++i) {
			for(int j = 1; j <= 3; ++j) {
				Arguments arg = Arguments.of(i,j);
				arguments.add(arg);
			}
		}
		return arguments.stream();
	}
	
	@ParameterizedTest
	@MethodSource("generateGameIdsAndStarts")
	void testGetCommentsForGame(int gameId, int start) throws SQLException {
		ps = conn.prepareStatement(GETSTARTCOMMENTS);
		ps.setInt(1, gameId);
		ps.setInt(2, start);
		ps.setInt(3, start + 6);
		rs = ps.executeQuery();
		List<Comment> commentsExpected = new ArrayList<Comment>();
		while(rs.next()) {
			Comment comment = new Comment();
			comment.setGameId(rs.getInt(1));
			comment.setCustomerId(rs.getInt(2));
			comment.setComment_text(rs.getString(3));
			comment.setRating(rs.getDouble(4));
			comment.setComment_date(rs.getDate(5));
			commentsExpected.add(comment);
		}
		
		List<Comment> actual = commentDAO.getCommentsForGame(gameId, start);
		Assertions.assertEquals(commentsExpected, actual);
	}

	private static Stream<Arguments> generateCustomerAndGameIds(){
		
		List<Arguments> arguments = new ArrayList<Arguments>();
		
		for(int i = 1; i <= 47; ++i) {
			for(int j = 1; j <= 10; ++j) {
				Arguments arg = Arguments.of(i,j);
				arguments.add(arg);
			}
		}
		
		return arguments.stream();
	}
	
	@ParameterizedTest
	@MethodSource("generateCustomerAndGameIds")
	void testCustomerHasCommentsForGame(int customerId, int gameId) throws SQLException {
		ps = conn.prepareStatement(HASCOMMENTFORRENTAL);
		ps.setInt(1, customerId);
		ps.setInt(2, gameId);
		
		rs = ps.executeQuery();
		boolean expected = rs.next();
		
		boolean actual = commentDAO.customerHasCommentsForGame(customerId, gameId);
		Assertions.assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("generateCustomerAndGameIds")
	void testAddReviewForCustomerToGame(int customerId, int gameId) throws SQLException {
		commentDAO.setKeepOpen(true);
		Assumptions.assumeFalse(commentDAO.customerHasCommentsForGame(customerId, gameId));
		Assumptions.assumeTrue(customerExists(customerId));
		Comment expectedComment = new Comment();
		expectedComment.setGameId(gameId);
		expectedComment.setCustomerId(customerId);
		Random rand = new Random();
		double ratingVal = rand.nextDouble() * 5;
		expectedComment.setRating(Math.round(ratingVal *100.0)/100.0);
		expectedComment.setComment_text("This is a test comment");
		
		commentDAO.addReviewForCustomerToGame(customerId, gameId, ratingVal, "This is a test comment");
		
		Comment actualComment = commentDAO.getCommentByCustomerAndGame(customerId, gameId);
		expectedComment.setComment_date(actualComment.getComment_date());
		
		Assertions.assertEquals(expectedComment, actualComment);
		commentDAO.setKeepOpen(false);
	}

	private boolean customerExists(int customerId) throws SQLException {
		ps = conn.prepareStatement("Select * FROM Customers WHERE id = ?");
		ps.setInt(1, customerId);
		
		rs = ps.executeQuery();
		
		return rs.next();
	}

}
