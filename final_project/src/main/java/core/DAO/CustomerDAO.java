package core.DAO;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Encryption.Encryption;
import core.Models.Customer;
import core.Models.Designer;

public class CustomerDAO {

	private static final String ADDCUSTOMER = "INSERT INTO Customers (Last_Name, First_name, Username, E_mail, Password, Salt, Address_Line_1, Address_Line_2, Zipcode, Phone, Member_Status, Join_Date, Balance) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String REGISTERCUSTOMER = "INSERT INTO Customers (Username, E_mail, Password, Salt, Member_Status, Join_Date, Balance) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATECUSTOMER = "UPDATE Customers SET Last_Name = ?, First_name = ?, E_mail = ?, Address_Line_1 = ?, Address_Line_2 = ?, Zipcode = ?, Phone = ?, Balance = ? WHERE id = ?"; //Cannot change username , password, salt, or member status here
	private static final String GETCUSTOMERBYUSERNAME = "SELECT Last_Name, First_name, Username, E_mail, Address_Line_1, Address_Line_2, Zipcode, Phone, Member_Status, Join_Date, Balance, id FROM Customers WHERE Username = ?";
	private static final String VERIFYUSER = "SELECT Password, Salt FROM Customers WHERE Username = ?";
	private static final String CHANGEUSERPASSWORD = "UPDATE Customers Set Password = ?, Salt = ? WHERE Username = ?";
	private static final String GETCUSTOMERBYEMAIL = "SELECT Last_Name, First_name, Username, E_mail, Address_Line_1, Address_Line_2, Zipcode, Phone, Member_Status, Join_Date, Balance, id FROM Customers WHERE E_mail = ?";
	
	/**
	 * @param customer: customer data to add to database (id is auto generated)
	 * @return whether or not customer was added to the database
	 */
	public boolean addCustomer(Customer customer) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return false;
		}
	
		boolean created = false;
		try {
			PreparedStatement ps = conn.prepareStatement(ADDCUSTOMER);
			ps.setString(1, customer.getFirst_name());
			ps.setString(2, customer.getLast_name());
			ps.setString(3, customer.getUsername());
			ps.setString(4, customer.getE_mail());
			
			//encrypt password and record salt
			byte[] salt = Encryption.getSalt();
			String saltStr = Encryption.getHexBinaryString(salt);
			String pass = Encryption.getPassword(customer.getPassword(),salt);
			
			ps.setString(5, pass);
			ps.setString(6, saltStr);
			ps.setString(7, customer.getAddress_line_1());
			ps.setString(8, customer.getAddress_line_2());
			ps.setInt(9, customer.getZipcode());
			ps.setLong(10, customer.getPhone());
			ps.setString(11, customer.getMember_status());
			ps.setDate(12, customer.getJoin_date());
			ps.setDouble(13, customer.getBalance());
			
			int updated = ps.executeUpdate();
			
			if(updated > 0) {
				created = true;
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
	}

	
	/**
	 * @param customer: customer details to update for the customer.id
	 * @return whether or not the customer details were updated in the database
	 */
	public int updateCustomer(Customer customer) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return -1;
		}
	
		int created = -2;
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATECUSTOMER);
			ps.setString(1, customer.getFirst_name());
			ps.setString(2, customer.getLast_name());
			ps.setString(3, customer.getE_mail());
			ps.setString(4, customer.getAddress_line_1());
			ps.setString(5, customer.getAddress_line_2());
			ps.setInt(6, customer.getZipcode());
			ps.setLong(7, customer.getPhone());
			ps.setDouble(8, customer.getBalance());
			ps.setInt(9, customer.getId());
			
			int updated = ps.executeUpdate();
			
			if(updated > 0) {
				created = updated;
			} else {
				created = -3;
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			created = e.getErrorCode();
			if (created > 0 ) {
				created = 0 - created;
			}
			e.printStackTrace();
		}
		return created;	
	}


	/**
	 * @param username: username for customer
	 * @return customer info for given username (w/o password or salt)
	 */
	public Customer getCustomerByUsername(String username) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return null;
		}
		
		Customer customer = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETCUSTOMERBYUSERNAME);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer();
				customer.setFirst_name(rs.getString(1));
				customer.setLast_name(rs.getString(2));
				customer.setUsername(rs.getString(3));
				customer.setE_mail(rs.getString(4));
				customer.setAddress_line_1(rs.getString(5));
				customer.setAddress_line_2(rs.getString(6));
				customer.setZipcode(rs.getInt(7));
				customer.setPhone(rs.getLong(8));
				customer.setMember_status(rs.getString(9));
				customer.setJoin_date(rs.getDate(10));
				customer.setBalance(rs.getDouble(11));
				customer.setId(rs.getInt(12));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}

	
	/**
	 * @param username: username to verify for
	 * @param password: password to verify
	 * @return whether or not the password is correct for the given user
	 */
	public boolean verifyUser(String username, String password) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return false;
		}
		
		boolean verified = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(VERIFYUSER);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String pass = rs.getString(1);
				String salt = rs.getString(2);
				String hashedPass = Encryption.getPassword(password, Encryption.getBytesFromString(salt));
				verified = hashedPass.equals(pass);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return verified;
	}

	/**
	 * Changes the password for a given user
	 * @param username: username for which to change the password for
	 * @param password: password to change to
	 * @return whether or not password was changed for the user
	 */
	public boolean changePassword(String username, String password) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return false;
		}
		
		boolean changed = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(CHANGEUSERPASSWORD);
			byte[] salt = Encryption.getSalt();
			ps.setString(1, Encryption.getPassword(password, salt));
			ps.setString(2, Encryption.getHexBinaryString(salt));
			ps.setString(3, username);
			
			int update = ps.executeUpdate();
			if (update > 0) {
				changed = true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return changed;
	}


	public Customer getCustomerByEmail(String email) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return null;
		}
		
		Customer customer = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GETCUSTOMERBYEMAIL);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer();
				customer.setFirst_name(rs.getString(1));
				customer.setLast_name(rs.getString(2));
				customer.setUsername(rs.getString(3));
				customer.setE_mail(rs.getString(4));
				customer.setAddress_line_1(rs.getString(5));
				customer.setAddress_line_2(rs.getString(6));
				customer.setZipcode(rs.getInt(7));
				customer.setPhone(rs.getLong(8));
				customer.setMember_status(rs.getString(9));
				customer.setJoin_date(rs.getDate(10));
				customer.setBalance(rs.getDouble(11));
				customer.setId(rs.getInt(12));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}


	public int registerUser(Customer customer) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return -1;
		}
	
		int created = -1;
		try {
			String[] customer_id = {"id"};
			PreparedStatement ps = conn.prepareStatement(REGISTERCUSTOMER, customer_id);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getE_mail());
			
			//encrypt password and record salt
			byte[] salt = Encryption.getSalt();
			String saltStr = Encryption.getHexBinaryString(salt);
			String pass = Encryption.getPassword(customer.getPassword(),salt);
			
			ps.setString(3, pass);
			ps.setString(4, saltStr);
			ps.setString(5, customer.getMember_status());
			ps.setDate(6, customer.getJoin_date());
			ps.setDouble(7, customer.getBalance());
			
			//ps.e
			int updated = ps.executeUpdate();
			
			if(updated > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					created = rs.getInt(1);
				}
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
		
	}
}
