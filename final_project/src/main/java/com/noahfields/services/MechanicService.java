package com.noahfields.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.MechanicDAO;
import com.noahfields.Models.Game;
import com.noahfields.Models.Mechanic;

@Service
public class MechanicService {

	@Autowired
	MechanicDAO mechanicDao;
	
	public List<Mechanic> getMechanicsForGame(Game game) {
		mechanicDao.connect();
		return mechanicDao.getMechanicsForGame(game);
	}

	public Mechanic getMechanicByID(int id) {
		mechanicDao.connect();
		return mechanicDao.getMechanicByID(id);
	}

	
}
