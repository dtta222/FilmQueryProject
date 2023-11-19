package com.skilldistillery.filmquery.entities;

// Language class representing a language entity
public class Language {
    // Fields
	private int id;        // Unique identifier for the language
	private String name;   // Name of the language
	
    // Constructors
	public Language() {}  // Default constructor
    
	public Language(int id, String name) {
		this.id = id;
		this.name = name;
	}

    // Getter for id
	public int getId() {
		return id;
	}

    // Setter for id
	public void setId(int id) {
		this.id = id;
	}

    // Getter for name
	public String getName() {
		return name;
	}

    // Setter for name
	public void setName(String name) {
		this.name = name;
	}

    // HashCode method for generating hash values, required for collections
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

    // Equals method for comparing two Language objects for equality
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

    // ToString method for creating a string representation of the Language object
	@Override
	public String toString() {
		return "Language [id=" + id + ", name=" + name + "]";
	}
}
