package com.noahfields.Models;

public class Picture {

	private int id;
	private int game_id;
	private int size;
	private String uri;
	
	public Picture() {
		super();
	}

	public Picture(int id, int game_id, int size, String uri) {
		super();
		this.id = id;
		this.game_id = game_id;
		this.size = size;
		this.uri = uri;
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
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public String getUriResource() {
		return "/resources/images/"+uri;
	}
	
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Picture [game_id=" + game_id + ", size=" + size + ", uri=" + uri + "]";
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
		if (!(obj instanceof Picture))
			return false;
		Picture other = (Picture) obj;
		if(id != other.id)
			return false;
		if (game_id != other.game_id)
			return false;
		if (size != other.size)
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
	
}
