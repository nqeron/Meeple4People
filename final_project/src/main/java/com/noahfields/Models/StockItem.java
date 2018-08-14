package com.noahfields.Models;

import java.sql.Date;

public class StockItem {

	private int item_id;
	private int game_id;
	private int status_id;
	private String serial_number;
	private Date acquisition_date;
	private int condition_id;
	private Date last_examined;
	
	public StockItem() {
		super();
	}

	public StockItem(int item_id, int game_id, int status_id, String serial_number, Date acquisition_date,
			int condition_id, Date last_examined) {
		super();
		this.item_id = item_id;
		this.game_id = game_id;
		this.status_id = status_id;
		this.serial_number = serial_number;
		this.acquisition_date = acquisition_date;
		this.condition_id = condition_id;
		this.last_examined = last_examined;
	}

	/**
	 * @return the item_id
	 */
	public int getItem_id() {
		return item_id;
	}

	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	/**
	 * @return the game_id
	 */
	public int getGame_id() {
		return game_id;
	}

	/**
	 * @param game_id the game_id to set
	 */
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	/**
	 * @return the status_id
	 */
	public int getStatus_id() {
		return status_id;
	}

	/**
	 * @param status_id the status_id to set
	 */
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	/**
	 * @return the serial_number
	 */
	public String getSerial_number() {
		return serial_number;
	}

	/**
	 * @param serial_number the serial_number to set
	 */
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	/**
	 * @return the acquisition_date
	 */
	public Date getAcquisition_date() {
		return acquisition_date;
	}

	/**
	 * @param acquisition_date the acquisition_date to set
	 */
	public void setAcquisition_date(Date acquisition_date) {
		this.acquisition_date = acquisition_date;
	}

	/**
	 * @return the condition_id
	 */
	public int getCondition_id() {
		return condition_id;
	}

	/**
	 * @param condition_id the condition_id to set
	 */
	public void setCondition_id(int condition_id) {
		this.condition_id = condition_id;
	}

	/**
	 * @return the last_examined
	 */
	public Date getLast_examined() {
		return last_examined;
	}

	/**
	 * @param last_examined the last_examined to set
	 */
	public void setLast_examined(Date last_examined) {
		this.last_examined = last_examined;
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
		if (!(obj instanceof StockItem))
			return false;
		StockItem other = (StockItem) obj;
		if (acquisition_date == null) {
			if (other.acquisition_date != null)
				return false;
		} else if (!acquisition_date.equals(other.acquisition_date))
			return false;
		if (condition_id != other.condition_id)
			return false;
		if (game_id != other.game_id)
			return false;
		if (item_id != other.item_id)
			return false;
		if (last_examined == null) {
			if (other.last_examined != null)
				return false;
		} else if (!last_examined.equals(other.last_examined))
			return false;
		if (serial_number == null) {
			if (other.serial_number != null)
				return false;
		} else if (!serial_number.equals(other.serial_number))
			return false;
		if (status_id != other.status_id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StockItem [item_id=" + item_id + ", game_id=" + game_id + ", status_id=" + status_id
				+ ", serial_number=" + serial_number + ", acquisition_date=" + acquisition_date + ", condition_id="
				+ condition_id + ", last_examined=" + last_examined + "]";
	}
	
	
	
}
