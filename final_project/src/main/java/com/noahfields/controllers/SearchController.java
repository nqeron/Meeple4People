package com.noahfields.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noahfields.Models.Designer;
import com.noahfields.Models.Game;
import com.noahfields.Models.Mechanic;
import com.noahfields.Models.Picture;
import com.noahfields.Models.Publisher;
import com.noahfields.services.DesignerService;
import com.noahfields.services.GameService;
import com.noahfields.services.MechanicService;
import com.noahfields.services.PictureService;
import com.noahfields.services.PublisherService;

@Controller
public class SearchController {

	private static final Set<String> SEARCHPARAMETERS = new HashSet<String>(Arrays.asList("name","year_published", "rating", "designers", "mechanics", "publishers", "cost"));
	
	@Autowired
	GameService gameService;
	
	@Autowired
	DesignerService designerService;
	
	@Autowired
	PublisherService publisherService;
	
	@Autowired
	MechanicService mechanicService;
	
	@Autowired
	PictureService pictureService;
	
	@GetMapping("/browseGames")
	public String browseAllGames(Model m) {
		return searchFor("",1,m);
	}
	
	@GetMapping("/search")
	public String searchFor(@RequestParam("searchString") String search, @RequestParam("page") int page, Model m) {
		String[] valuePairs = search.split(";\\s?");
		Map<String, String> searchTerms = new HashMap<String, String>();
		
		for(String valuePair : valuePairs) {
			String[] pairs = valuePair.split(":");
			String key;
			String value;
			if(pairs.length == 1) {
				key = "name";
				value = pairs[0];
			} else {
				key = pairs[0];
				value = pairs[1];
			}
			if(SEARCHPARAMETERS.contains(key)) {
				searchTerms.put(key, value);
			}
		}
		
		String name = searchTerms.getOrDefault("name", null);
		String[] yearsPublished = searchTerms.getOrDefault("year_published", "").split(",");
		String[] ratings = searchTerms.getOrDefault("rating", "").split("[,-]\\s?");
		String[] designers = searchTerms.getOrDefault("designers", "").split(",\\s?");
		String[] publishers = searchTerms.getOrDefault("publishers", "").split(",\\s?");
		String[] mechanics = searchTerms.getOrDefault("mechanics", "").split(",\\s?");
		String[] cost = searchTerms.getOrDefault("cost", "").split("[,-]\\s?");
		List<Game> games = gameService.searchForGames(name, yearsPublished, ratings, designers, publishers, mechanics, cost);
		
		Map<Game, List<Designer>> gameDesignerMap = new HashMap<Game, List<Designer>>();
		for(Game game : games) {
			List<Designer> gameDesigners =  designerService.getDesignersForGame(game);
			gameDesignerMap.put(game, gameDesigners);
		}
		
		Map<Game, List<Publisher>> gamePublisherMap = new HashMap<Game, List<Publisher>>();
		for(Game game : games) {
			List<Publisher> gamePublishers = publisherService.getPublisherForGameID(game.getId());
			gamePublisherMap.put(game, gamePublishers);
		}
		
		Map<Game, List<Mechanic>> gameMechanicMap = new HashMap<Game, List<Mechanic>>();
		for(Game game : games) {
			List<Mechanic> gameMechanics = mechanicService.getMechanicsForGame(game);
			gameMechanicMap.put(game, gameMechanics);
		}
		//List<Game> games = gameService.searchForGamesByName(name);
		Map<Game, Picture> gamePictures = pictureService.getPicturesForGamesofSize(games, 2);
		
		m.addAttribute("games", games);
		m.addAttribute("gamePictures", gamePictures);
		m.addAttribute("designers", gameDesignerMap);
		m.addAttribute("publishers", gamePublisherMap);
		m.addAttribute("mechanics", gameMechanicMap);
		m.addAttribute("searchString", search);
		m.addAttribute("page", page);
		return "searchResultPage";
	}
}
