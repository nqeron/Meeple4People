package com.noahfields.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.PictureDAO;
import com.noahfields.Models.Game;
import com.noahfields.Models.Picture;

@Service
public class PictureService {

	@Autowired
	PictureDAO pictureDAO;
	
	public Map<Game, Picture> getPicturesForGamesofSize(List<Game> games, int size) {

		Map<Game, Picture> gamePictures = new HashMap<Game, Picture>();
		
		for(Game game: games) {
			Picture picture = pictureDAO.getPictureOfSizeForGame(size, game.getId());
			gamePictures.put(game, picture);
		}
		
		return gamePictures;
		
		//return pictureDAO.getPictureForGamesofSize(games, size);
	}

	public Picture getPictureForGameofSize(int gameId, int size) {
		return pictureDAO.getPictureOfSizeForGame(size, gameId);
	}

}
