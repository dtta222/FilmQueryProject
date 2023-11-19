package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.*;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId) throws SQLException;

	public Actor findActorById(int actorId) throws SQLException;
	
	public Language findLanguageById(int languageId) throws SQLException;
	
	public List<Actor> findActorsByFilmId(int filmId);
	
	public List<Film> findFilmsByActorId(int actorId);
	
	public List<Film> findFilmsByKeyWord(String word);
}
