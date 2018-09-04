package tests.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.noahfields.DAO.CustomerDAO;
import com.noahfields.DAO.OracleConnection;
import com.noahfields.Models.Customer;

class CustomerDAOTest {
	
	static CustomerDAO customerDAO;
	static Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
		customerDAO.setAutoCommit(false);
		conn = new OracleConnection().getConnection();
		conn.setAutoCommit(false);
		customerDAO.connect();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		customerDAO.dispose();
		customerDAO = null;
		conn.rollback();
		conn.close();
		conn = null;
	}

	@AfterEach
	void tearDown() throws SQLException {
		customerDAO.rollback();
		if(ps != null) ps.close();
		if (rs != null) rs.close();
	}
	
	@Test
	void testAddAndGetCustomer() {
		customerDAO.setKeepOpen(true);
		Customer customer = new Customer();
		customer.setAddress_line_1("test address");
		customer.setAddress_line_2("addr line 2");
		customer.setBalance(5.0);
		customer.setE_mail("loofy@gmail.com");
		customer.setFirst_name("Lorenzo");
		customer.setLast_name("Firefly");
		customer.setJoin_date(new Date(Calendar.getInstance().getTime().getTime()));
		customer.setMember_status("Active");
		customer.setPhone(8634456822l);
		customer.setUsername("loofy");
		customer.setPassword("password");
		customer.setZipcode(7006);
		customerDAO.addCustomer(customer);
		customer.setPassword(null);
		
		Customer actual = customerDAO.getCustomerByUsername(customer.getUsername());
		customer.setId(actual.getId());
		customer.setJoin_date(actual.getJoin_date());
		
		Assertions.assertEquals(customer, actual);
		
		actual = customerDAO.getCustomerByEmail(customer.getE_mail());
		customer.setId(actual.getId());
		customer.setJoin_date(actual.getJoin_date());
		Assertions.assertEquals(customer, actual);
		customerDAO.setKeepOpen(false);
	}

	@Test
	void testUpdateCustomerAndVerify() {
		customerDAO.setKeepOpen(true);
		Customer customer = new Customer();
		customer.setAddress_line_1("test address");
		customer.setAddress_line_2("addr line 2");
		customer.setBalance(5.0);
		customer.setE_mail("loofy@gmail.com");
		customer.setFirst_name("Lorenzo");
		customer.setLast_name("Firefly");
		customer.setJoin_date(new Date(Calendar.getInstance().getTime().getTime()));
		customer.setMember_status("Active");
		customer.setPhone(8634456822l);
		customer.setUsername("loofy");
		customer.setPassword("password");
		customer.setZipcode(7006);
		customerDAO.addCustomer(customer);
		customer.setPassword(null);
		
		customer.setAddress_line_1("new address");
		customer.setE_mail("lofirefly@gmail.com");
		customer.setFirst_name("Lor");
		
		Customer temp = customerDAO.getCustomerByUsername("loofy");
		customer.setId(temp.getId());
		customerDAO.updateCustomer(customer);
		
		Customer actual = customerDAO.getCustomerByUsername("loofy");
		customer.setJoin_date(actual.getJoin_date());
		
		Assertions.assertEquals(customer, actual);
		
		Assertions.assertTrue(customerDAO.verifyUser("loofy", "password"));
		customerDAO.setKeepOpen(false);
		
		
		
	}

	@Test
	void testChangePassword() {
		customerDAO.setKeepOpen(true);
		Customer customer = new Customer();
		customer.setAddress_line_1("test address");
		customer.setAddress_line_2("addr line 2");
		customer.setBalance(5.0);
		customer.setE_mail("loofy@gmail.com");
		customer.setFirst_name("Lorenzo");
		customer.setLast_name("Firefly");
		customer.setJoin_date(new Date(Calendar.getInstance().getTime().getTime()));
		customer.setMember_status("Active");
		customer.setPhone(8634456822l);
		customer.setUsername("loofy");
		customer.setPassword("password");
		customer.setZipcode(7006);
		customerDAO.addCustomer(customer);
		
		customerDAO.changePassword("loofy", "newPass");
		
		Assertions.assertTrue(customerDAO.verifyUser("loofy", "newPass"));
		
		customerDAO.setKeepOpen(false);
	}

	@Test
	void testRegisterUser() {
		customerDAO.setKeepOpen(true);
		Customer customer = new Customer();
		customer.setBalance(5.0);
		customer.setE_mail("loofy@gmail.com");
		customer.setJoin_date(new Date(Calendar.getInstance().getTime().getTime()));
		customer.setMember_status("Active");
		customer.setUsername("loofy");
		customer.setPassword("password");
		int id = customerDAO.registerUser(customer);
		customer.setId(id);
		
		Customer actual = customerDAO.getCustomerByUsername("loofy");
		customer.setJoin_date(actual.getJoin_date());
		
		Assertions.assertEquals(customer, actual);
	}

}
