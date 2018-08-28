package com.noahfields.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahfields.DAO.CommentDAO;
import com.noahfields.Models.Comment;

@Service
public class CommentService {

	@Autowired
	CommentDAO commentDao;

	public List<Comment> getCommentsForGame(int id, int start) {
		return commentDao.getCommentsForGame(id, start);
	}

	public boolean customerHasCommentsForGame(int customerId, int gameId) {
		// TODO Auto-generated method stub
		return commentDao.customerHasCommentsForGame(customerId, gameId);
	}

	public boolean addReviewForCustomerToGame(int customerId, int gameId, double ratingVal, String commentText) {
		// TODO Auto-generated method stub
		return commentDao.addReviewForCustomerToGame(customerId, gameId, ratingVal, commentText);
	}
	
	
}
