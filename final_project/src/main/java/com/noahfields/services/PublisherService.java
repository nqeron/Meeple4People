package com.noahfields.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.PublisherDAO;
import com.noahfields.Models.Publisher;

@Service
public class PublisherService {

	@Autowired
	PublisherDAO publisherDao;

	public List<Publisher> getPublisherForGameID(int id) {
		publisherDao.connect();
		return publisherDao.getPublisherForGameID(id);
	}
	
	
}
