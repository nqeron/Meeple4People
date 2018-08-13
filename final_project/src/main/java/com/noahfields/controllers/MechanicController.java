package com.noahfields.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.noahfields.DAO.MechanicDAO;
import com.noahfields.Models.Mechanic;
import com.noahfields.services.MechanicService;

@Controller
public class MechanicController {

	@Autowired
	MechanicService mechanicService;
	
	@GetMapping("mechanics/{id}")
	public String getMechanicDetails(@PathVariable int id, Model m) {
		Mechanic mechanic = mechanicService.getMechanicByID(id);
		
		if(mechanic == null || mechanic.equals(null)) {
			return null; //TODO redirect to an error page
		}
		
		m.addAttribute("mechanic", mechanic);
		return "mechanicDetail";
	}
}
