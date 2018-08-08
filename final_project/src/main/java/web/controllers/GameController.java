package web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.DAO.GameDAO;
import core.Models.Game;

@Controller
public class GameController {

	//private final static org.slj
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model m) {
		m.addAttribute("test", "A test");
		GameDAO gameDao = new GameDAO();
		List<Game> games = gameDao.getRecommendedGames(1);
		m.addAttribute("gameList", games);
		m.addAttribute("recommended",7);
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postIndex(@RequestParam("nextRecommended") String nextRecommended, Model m) {
		m.addAttribute("test", "A test");
		GameDAO gameDao = new GameDAO();
		int next = Integer.parseInt(nextRecommended);
		List<Game> games = gameDao.getRecommendedGames(next);
		m.addAttribute("size", games.size());
		if (games.size() < 6) {
			List<Game> initialGames = gameDao.getRecommendedGames(1);
			int sz = games.size();
			for(int i = 0; i < 6 - sz; ++i) {
				games.add(initialGames.get(i));
			}
			next = sz + 1;
		}
		m.addAttribute("gameList", games);
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
