package com.noahfields.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.noahfields.Models.Zipcode;
import com.noahfields.message.Response;
import com.noahfields.services.ZipcodeService;

@RestController
public class ZipcodeController {

	@Autowired
	ZipcodeService zipcodeService;
	
	@PostMapping("/getZipcode")
	public Response getZipcode(@RequestBody Zipcode zip){
		zip = zipcodeService.getZipcode(zip.getZipcode());
		Response response = null;
		if(zip == null || zip.equals(null)) {
			response = new Response("Fail", null);
		}else {
			response = new Response("Done", zip);
		}
		return response;
		
	}
}
