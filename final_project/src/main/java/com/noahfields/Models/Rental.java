package com.noahfields.Models;

import java.sql.Date;
import java.util.Calendar;

public class Rental {

	private int id;
	private int customer_id;
	private int item_id;
	private Date date_rented;
	private Date due_date;
	
	public Rental() {
		super();
	}

	public Rental(int id, int customer_id, int item_id, Date date_rented, Date due_date) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.date_rented = date_rented;
		this.due_date = due_date;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the customer_id
	 */
	public int getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
	 * @return the date_rented
	 */
	public Date getDate_rented() {
		return date_rented;
	}

	/**
	 * @param date_rented the date_rented to set
	 */
	public void setDate_rented(Date date_rented) {
		this.date_rented = date_rented;
	}

	/**
	 * @return the due_date
	 */
	public Date getDue_date() {
		return due_date;
	}

	/**
	 * @param due_date the due_date to set
	 */
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	
	public boolean isOverdue() {
		int comparison = due_date.compareTo(Calendar.getInstance().getTime());
		return comparison < 0;
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
		if (!(obj instanceof Rental))
			return false;
		Rental other = (Rental) obj;
		if (customer_id != other.customer_id)
			return false;
		if (date_rented == null) {
			if (other.date_rented != null)
				return false;
		} else if (!date_rented.equals(other.date_rented))
			return false;
		if (due_date == null) {
			if (other.due_date != null)
				return false;
		} else if (!due_date.equals(other.due_date))
			return false;
		if (id != other.id)
			return false;
		if (item_id != other.item_id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rental [id=" + id + ", customer_id=" + customer_id + ", item_id=" + item_id + ", date_rented="
				+ date_rented + ", due_date=" + due_date + "]";
	}
	
	
}
