package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import core.DAO.MechanicDAO;
import core.Models.Mechanic;

@Controller
public class MechanicController {

	
	@GetMapping("mechanics/{id}")
	public String getMechanicDetails(@PathVariable int id, Model m) {
		Mechanic mechanic = new MechanicDAO().getMechanicByID(id);
		
		if(mechanic == null || mechanic.equals(null)) {
			return null; //TODO redirect to an error page
		}
		
		m.addAttribute("mechanic", mechanic);
		return "mechanicDetail";
	}
}
