package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;

// Actor class representing an actor entity
public class Actor {
    // Fields
	private int id;              // Unique identifier for the actor
	private String firstName;    // First name of the actor
	private String lastName;     // Last name of the actor
	private List<Film> films;    // List of films in which the actor has appeared

    // Constructors
	public Actor() {}  // Default constructor

	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Actor(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

    // Getter for actor ID
	public int getId() {
		return id;
	}

    // Setter for actor ID
	public void setId(int id) {
		this.id = id;
	}

    // Getter for first name
	public String getFirstName() {
		return firstName;
	}

    // Setter for first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    // Getter for last name
	public String getLastName() {
		return lastName;
	}

    // Setter for last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    // Getter for films
	public List<Film> getFilms() {
		List<Film> copy = new ArrayList<>(films);  // Creating a defensive copy of the films list
		return copy;
	}

    // Setter for films
	public void setFilms(List<Film> films) {
		this.films = films;
	}

    // HashCode method for generating hash values, required for collections
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

    // Equals method for comparing two Actor objects for equality
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
    // ToString method for creating a string representation of the Actor object
	@Override
	public String toString() {
		return "Actor Information\n" +
            "ID: " + id + "\n" +
            "Name: " + firstName + " " + lastName;
	}
}
