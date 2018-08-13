package com.noahfields.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.DesignerDAO;
import com.noahfields.Models.Designer;
import com.noahfields.Models.Game;

@Service
public class DesignerService {
	
	@Autowired
	DesignerDAO designerDao;

	public List<Designer> getDesignersForGame(Game game) {
		return designerDao.getDesignersForGame(game);
	}
	
	

}
