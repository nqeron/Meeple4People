package com.noahfields.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noahfields.DAO.CommentDAO;
import com.noahfields.DAO.DesignerDAO;
import com.noahfields.DAO.GameDAO;
import com.noahfields.DAO.MechanicDAO;
import com.noahfields.DAO.PublisherDAO;
import com.noahfields.Models.Comment;
import com.noahfields.Models.Designer;
import com.noahfields.Models.Game;
import com.noahfields.Models.Mechanic;
import com.noahfields.Models.Picture;
import com.noahfields.Models.Publisher;
import com.noahfields.services.CommentService;
import com.noahfields.services.DesignerService;
import com.noahfields.services.GameService;
import com.noahfields.services.MechanicService;
import com.noahfields.services.PictureService;
import com.noahfields.services.PublisherService;

@Controller
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@Autowired
	DesignerService designerService;
	
	@Autowired
	PublisherService publisherService;
	
	@Autowired
	MechanicService mechanicService;
	
	@Autowired
	CommentService commentService;

	@Autowired
	PictureService pictureService;

	//private final static org.slj
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model m) {
		m.addAttribute("test", "A test");
		List<Game> games = gameService.getRecommendedGames(1);
		Map<Game,Picture> gamePictures = pictureService.getPicturesForGamesofSize(games,2);
		m.addAttribute("gameList", games);
		m.addAttribute("gamePictures",gamePictures);
		m.addAttribute("recommended",7);
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postIndex(@RequestParam("nextRecommended") String nextRecommended, Model m) {
		m.addAttribute("test", "A test");
		int next = Integer.parseInt(nextRecommended);
		List<Game> games = gameService.getRecommendedGames(next);
		m.addAttribute("size", games.size());
		if (games.size() < 6) {
			List<Game> initialGames = gameService.getRecommendedGames(1);
			int sz = games.size();
			for(int i = 0; i < 6 - sz; ++i) {
				games.add(initialGames.get(i));
			}
			next = sz + 1;
		}
		
		Map<Game,Picture> gamePictures = pictureService.getPicturesForGamesofSize(games,2);
		m.addAttribute("gameList", games);
		m.addAttribute("gamePictures",gamePictures);
		m.addAttribute("recommended",next+6);
		return "index";
	}
	
	@RequestMapping(value = "/welcome")
	public ModelAndView welcome(Model m) {
		
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("test", "This is a Test");
		m.addAttribute("test", "Some test");
		
		return model;
	}
	
	@GetMapping("/games/{id}")
	public String getGameDetail(@PathVariable int id, Model m) {
		Game game = gameService.getGameByID(id);
		
		if(game == null || game.equals(null)) {
			return null; //TODO return an error page
		}
		
		List<Designer> designers = designerService.getDesignersForGame(game);
		List<Publisher> publishers = publisherService.getPublisherForGameID(id);
		List<Mechanic> mechanics = mechanicService.getMechanicsForGame(game);
		List<Comment> comments = commentService.getCommentsForGame(id, 1);
		
		Picture picture = pictureService.getPictureForGameofSize(game.getId(), 2);
		
		m.addAttribute("game", game);
		m.addAttribute("designers", designers);
		m.addAttribute("publishers", publishers);
		m.addAttribute("mechanics", mechanics);
		m.addAttribute("ratings", comments);
		m.addAttribute("picture", picture);
		
		return "gameDetail";
	}
	//public ModelAndView getRecommendedGames() {
//		GameDAO gameDao = new GameDAO();
//		
//		List<String> games = new ArrayList<String>();
//		games.add("viticulture");
//		games.add("le havre");
//		///List<Game> games = gameDao.getRecommendedGames(1);
//		System.out.println("44444444444 ");
//		System.out.println(games.toString());
//		ModelAndView model = new ModelAndView("index");
//		model.addObject("gameList", games);
//		model.addObject("test","This is a test");
//		return model;
//	}
}
