package com.noahfields.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.stereotype.Repository;

import com.noahfields.Models.Publisher;

@Repository
public class PublisherDAO extends GeneralDAO{
	
	private static final String GETPUBLISHERFORGAMEID = "SELECT pub.id, pub.Name, pub.Website FROM Publishers pub join Game_Publishers gp on pub.id = gp.Publisher_ID WHERE gp.Game_ID = ?";
	private static final String GETPUBLISHERBYNAMEBASE = "SELECT * FROM Publishers";
	
	/** 
	 * Method that queries the database for publishers associated with a specific game.
	 * @param id: for game to return publishers for
	 * @return publishers for a given game
	 */
	public List<Publisher> getPublisherForGameID(int id){
		
		if(conn == null) {
			return null;
		}
		
		List<Publisher> publishers = null;
		try {
			ps = conn.prepareStatement(GETPUBLISHERFORGAMEID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			publishers = new ArrayList<Publisher>();
			while(rs.next()) {
				Publisher publisher = new Publisher();
				publisher.setId(rs.getInt(1));
				publisher.setName(rs.getString(2));
				publisher.setWebsite(rs.getString(3));
				publishers.add(publisher);
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return publishers;
	}

	/**
	 * getPublishersByName: gets Publishers by name -- looks for partial matches
	 * @param publishers: array of strings with names for publisher
	 * @return list of publishers with any of the provided names
	 */
	public List<Publisher> getPublishersByName(String[] publishers) {
		if(publishers == null || publishers.length <=0 || Arrays.equals(publishers,new String[] {""})) {
			return null;
		}
		
		if(conn == null) {
			return null;
		}
		
		List<Publisher> pubs = null;
		
		try {
			String SQL_QUERY = GETPUBLISHERBYNAMEBASE;
			String WHERE = " WHERE ";
			StringJoiner LikeClause = new StringJoiner(" OR ");
			for(String p: publishers) {
				LikeClause.add("UPPER(Name) LIKE UPPER(?)");
			}
			//WHERE UPPER(Name) LIKE UPPER(?)
			ps = conn.prepareStatement(GETPUBLISHERBYNAMEBASE + WHERE + LikeClause.toString());
			for(int i = 1; i <= publishers.length; ++i) {
				String pubName = publishers[i-1];
				ps.setString(i, "%"+pubName.trim()+"%");
			}
			
			ResultSet rs = ps.executeQuery();
			pubs = new ArrayList<Publisher>();
			
			while(rs.next()) {
				Publisher publisher = new Publisher();
				publisher.setId(rs.getInt(1));
				publisher.setName(rs.getString(2));
				publisher.setWebsite(rs.getString(3));
				pubs.add(publisher);
			}
			ps.close();
			if(!keepOpen) {
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pubs;
	}

}
