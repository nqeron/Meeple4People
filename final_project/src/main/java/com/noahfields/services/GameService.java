package com.noahfields.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.GameDAO;
import com.noahfields.Models.Game;

@Service
public class GameService {

	@Autowired
	GameDAO gameDao;
	
	public List<Game> getRecommendedGames(int start){
		return gameDao.getRecommendedGames(start);
	}

	public Game getGameByID(int id) {
		return gameDao.getGameByID(id);
	}
}
