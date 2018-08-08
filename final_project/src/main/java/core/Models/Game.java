package core.Models;

public class Game {
	private int id;
	private String name;
	private String description;
	private int year_published;
	private double cost_of_game;
	private double average_rating;
	
	public Game() {
		super();
		this.id = 0;
		this.name = null;
		this.description = null;
		this.year_published = 0;
		this.cost_of_game = 0;
	}
	
	

	public Game(int id, String name, String description, int year_published, double cost_of_game,
			double average_rating) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.year_published = year_published;
		this.cost_of_game = cost_of_game;
		this.average_rating = average_rating;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear_published() {
		return year_published;
	}

	public void setYear_published(int year_published) {
		this.year_published = year_published;
	}

	public double getCost_of_game() {
		return cost_of_game;
	}

	public void setCost_of_game(double cost_of_game) {
		this.cost_of_game = cost_of_game;
	}

	public void setAverage_Rating(double average_rating) {
		this.average_rating = average_rating;;
		
	}
	
	public double getAverage_Rating() {
		return this.average_rating;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (Double.doubleToLongBits(average_rating) != Double.doubleToLongBits(other.average_rating))
			return false;
		if (Double.doubleToLongBits(cost_of_game) != Double.doubleToLongBits(other.cost_of_game))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year_published != other.year_published)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", description=" + description + ", year_published="
				+ year_published + ", cost_of_game=" + cost_of_game + ", average_rating=" + average_rating + "]";
	}
	
	
	
	
	
}
