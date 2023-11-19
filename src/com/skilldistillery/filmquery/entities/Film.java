package com.skilldistillery.filmquery.entities;

import java.util.List;

// Film class representing a film entity
public class Film {
    // Fields
	private int id;              // Unique identifier for the film
	private String title;         // Title of the film
	private int releaseYear;      // Year the film was released
	private String rating;        // Rating of the film
	private String description;   // Description of the film
	private String languageName;  // Name of the language in which the film is available
	private List<Actor> actors;   // List of actors in the film

    // Constructors
	public Film() {}  // Default constructor

	public Film(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public Film(String title, int releaseYear, String rating, String description) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.description = description;
	}

	public Film(String title, int releaseYear, String rating, String description, String languageName, List<Actor> actors) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.description = description;
		this.languageName = languageName;
		this.actors = actors;
	}

    // Getter for film ID
	public int getFilmId() {
		return id;
	}

    // Setter for film ID
	public void setFilmId(int id) {
		this.id = id;
	}

    // Getter for film title
	public String getFilmTitle() {
		return title;
	}

    // Setter for film title
	public void setFilmTitle(String title) {
		this.title = title;
	}

    // HashCode method for generating hash values, required for collections
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

    // Equals method for comparing two Film objects for equality
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (languageName == null) {
			if (other.languageName != null)
				return false;
		} else if (!languageName.equals(other.languageName))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

    // ToString method for creating a string representation of the Film object
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Title: ").append(title).append("\n");
		sb.append("Release Year: ").append(releaseYear).append("\n");
		sb.append("Rating: ").append(rating).append("\n");
		sb.append("Description: ").append(description).append("\n");
		sb.append("Language: ").append(languageName).append("\n");
		sb.append("Actors: ").append("\n");

		for (Actor actor : actors) {
			sb.append(" - ").append(actor.getFirstName() + " ").append(actor.getLastName()).append("\n");
		}
		return sb.toString();
	}
}
