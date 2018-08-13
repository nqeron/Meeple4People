package com.noahfields.Models;

public class Designer {

	private int id;
	private String first_name;
	private String last_name;
	private String website;
	
	public Designer() {
		super();
	}

	public Designer(int id, String first_name, String last_name, String website) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.website = website;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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
		if (!(obj instanceof Designer))
			return false;
		Designer other = (Designer) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (id != other.id)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	
	
}
