package com.noahfields.Models;

import java.sql.Date;

public class Comment {
	
	private int gameId;
	private int customerId;
	private String comment_text;
	private double rating;
	private Date comment_date;
	
	public Comment() {
		super();
	}

	public Comment(int gameId, int customerId, String comment_text, double rating, Date comment_date) {
		super();
		this.gameId = gameId;
		this.customerId = customerId;
		this.comment_text = comment_text;
		this.rating = rating;
		this.comment_date = comment_date;
	}

	/**
	 * @return the gameId
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the userId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the userId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the comment_text
	 */
	public String getComment_text() {
		return comment_text;
	}

	/**
	 * @param comment_text the comment_text to set
	 */
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @return the comment_date
	 */
	public Date getComment_date() {
		return comment_date;
	}

	/**
	 * @param comment_date the comment_date to set
	 */
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Comment))
			return false;
		Comment other = (Comment) obj;
		if (comment_date == null) {
			if (other.comment_date != null)
				return false;
		} else if (!comment_date.equals(other.comment_date))
			return false;
		if (comment_text == null) {
			if (other.comment_text != null)
				return false;
		} else if (!comment_text.equals(other.comment_text))
			return false;
		if (gameId != other.gameId)
			return false;
		if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
			return false;
		if (customerId != other.customerId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [gameId=" + gameId + ", userId=" + customerId + ", comment_text=" + comment_text + ", rating="
				+ rating + ", comment_date=" + comment_date + "]";
	}
	
	

}
