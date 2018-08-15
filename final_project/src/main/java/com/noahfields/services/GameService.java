package com.noahfields.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.DesignerDAO;
import com.noahfields.DAO.GameDAO;
import com.noahfields.DAO.MechanicDAO;
import com.noahfields.DAO.PublisherDAO;
import com.noahfields.Models.Designer;
import com.noahfields.Models.Game;
import com.noahfields.Models.Mechanic;
import com.noahfields.Models.Publisher;

@Service
public class GameService {

	@Autowired
	GameDAO gameDao;
	
	@Autowired
	DesignerDAO designerDAO;
	
	@Autowired
	MechanicDAO mechanicDAO;
	
	@Autowired
	PublisherDAO publisherDAO;
	
	public List<Game> getRecommendedGames(int start){
		return gameDao.getRecommendedGames(start);
	}

	public Game getGameByID(int id) {
		return gameDao.getGameByID(id);
	}

	public List<Game> searchForGamesByName(String name) {
		// TODO Auto-generated method stub
//		return gameDao.searchForGamesByName(name); 
		return gameDao.searchForGames(name, null, -1, -1, -1, -1, null, null, null);
	}

	public List<Game> searchForGames(String name, String[] yearsPublished, String[] ratings, String[] designers,
			String[] publishers, String[] mechanics, String[] cost) {
		// TODO Auto-generated method stub
		List<Publisher> publisherVals = publisherDAO.getPublishersByName(publishers);
		List<Mechanic> mechanicVals = mechanicDAO.getMechanicsByName(mechanics);
		List<Designer> designerVals = designerDAO.getDesignersByNames(designers);
		
		double lowRating = -1;
		if(ratings.length >= 1) {
			try {
				lowRating = Double.parseDouble(ratings[0]);
			} catch(NumberFormatException e) {
				
			}
		}
		
		double highRating = -1;
		if(ratings.length >= 2) {
			try {
				highRating = Double.parseDouble(ratings[1]);
			} catch(NumberFormatException e) {
				
			}
		}
		
		
		int[] years = null;
		
		if(!Arrays.equals(yearsPublished, new String[] {""})) {
			years = new int[yearsPublished.length];
			for(int i =0; i < yearsPublished.length; ++i) {
				String yr = yearsPublished[i];
				try {
					years[i] = Integer.parseInt(yr.trim());
				} catch(NumberFormatException e) {
					
				}
			}
		}
		
		double lowCost = -1;
		if(cost.length >= 1) {
			try {
				lowCost = Double.parseDouble(cost[0]);
			} catch(NumberFormatException e) {
				
			}
		}
		
		double highCost = -1;
		if(cost.length >= 2) {
			try {
				highCost = Double.parseDouble(cost[1]);
			} catch(NumberFormatException e) {
				
			}
		}
		
		return gameDao.searchForGames(name, years, lowCost, highCost, lowRating, highRating, designerVals, mechanicVals, publisherVals);
	}
}
