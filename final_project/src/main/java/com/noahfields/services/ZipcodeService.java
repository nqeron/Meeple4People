package com.noahfields.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.ZipcodeDAO;
import com.noahfields.Models.Zipcode;

@Service
public class ZipcodeService {

	@Autowired
	ZipcodeDAO zipcodeDao;
	
	public Zipcode getZipcode(int zipcode) {
		return zipcodeDao.getZipcode(zipcode);
	}

	public List<Zipcode> getAllZips() {
		return zipcodeDao.getAllZips();
	}

}
