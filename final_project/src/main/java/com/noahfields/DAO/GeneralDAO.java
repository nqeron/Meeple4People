package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

//Provides general functionality for DAOs to roll them back, if needed
@Repository
abstract public class GeneralDAO {
	protected Connection conn;
	protected boolean autocommit;
	protected boolean keepOpen;
	protected PreparedStatement ps;
	
	public GeneralDAO() {
		this.autocommit = true;
		this.keepOpen = false;
	}
	
	public void setAutoCommit(boolean autoCommit) {
		this.autocommit = autoCommit;
	}
	
	public void setKeepOpen(boolean keepOpen) {
		this.keepOpen = keepOpen;
	}
	
	/**
	 * connect: connects the DAO to the Database
	 */
	public void connect() {
		try {
			conn = new OracleConnection().getConnection();
			conn.setAutoCommit(autocommit);
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
	}

	/**
	 * rollback: rolls the current state back
	 */
	public void rollback() {
		if (!this.autocommit) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * commit: commits the current state
	 */
	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * dispose: closes the connection
	 */
	public void dispose() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
