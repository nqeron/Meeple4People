package com.noahfields.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.noahfields.DAO.MechanicDAO;
import com.noahfields.Models.Mechanic;
import com.noahfields.exceptions.NoMechanicFoundException;
import com.noahfields.services.MechanicService;

@Controller
public class MechanicController {

	@Autowired
	MechanicService mechanicService;
	
	// getMechanicDetails generates a detail page for the given mechanic
	@GetMapping("mechanics/{id}")
	public String getMechanicDetails(@PathVariable int id, Model m) throws NoMechanicFoundException {
		Mechanic mechanic = mechanicService.getMechanicByID(id);
		
		if(mechanic == null || mechanic.equals(null)) {
			throw new NoMechanicFoundException();
		}
		
		m.addAttribute("mechanic", mechanic);
		return "mechanicDetail";
	}
}
