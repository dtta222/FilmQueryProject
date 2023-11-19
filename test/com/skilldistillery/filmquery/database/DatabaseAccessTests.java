package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

class DatabaseAccessTests {
	private DatabaseAccessor db;

	@BeforeEach
	void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}

	@Test
	void test_getFilmById_returns_film_with_id() throws SQLException {
		Film f = db.findFilmById(1);
		assertNotNull(f);
		assertEquals("ACADEMY DINOSAUR", f.getFilmTitle());
	}

	@Test
	void test_getFilmById_with_invalid_id_returns_null() throws SQLException {
		Film f = db.findFilmById(-42);
		assertNull(f);
	}

	@Test
	void test_getActorById_returns_actor_with_id() throws SQLException {
		Actor a = db.findActorById(1);
		//assertNotNull(a);
		assertEquals("Penelope Guiness", a.getFirstName() + " " + a.getLastName());
	}

	@Test
	void test_getActorById_with_invalid_id_returns_null() throws SQLException {
		Actor a = db.findActorById(-42);
		assertNull(a);
	}

	@Test
	void test_findFilmsByActorId_returns_list_of_films() {
		List<Film> films = db.findFilmsByActorId(1);
		assertNotNull(films);
		assertEquals(19, films.size());
	}

	@Test
	void test_findFilmsByActorId_with_invalid_id_returns_empty_list() {
		List<Film> films = db.findFilmsByActorId(-42);
		assertNotNull(films);
		assertEquals(0, films.size());
	}

	@Test
	void test_findActorsByFilmId_returns_list_of_actors() {
		List<Actor> actors = db.findActorsByFilmId(1);
		assertNotNull(actors);
		assertEquals(10, actors.size());
	}

	@Test
	void test_findActorsByFilmId_with_invalid_id_returns_empty_list() {
		List<Actor> actors = db.findActorsByFilmId(-42);
		assertNotNull(actors);
		assertEquals(0, actors.size());
	}

	@Test
	void test_findFilmsByKeyWord_returns_list_of_films() {
		List<Film> films = db.findFilmsByKeyWord("run");
		//assertNotNull(films);
		assertEquals(8, films.size());
	}

	@Test
	void test_findFilmsByKeyWord_with_invalid_keyword_returns_empty_list() {
		List<Film> films = db.findFilmsByKeyWord("invalidKeyword");
		assertNotNull(films);
		assertEquals(0, films.size());
	}

	@Test
	void test_findLanguageById_returns_language_with_id() throws SQLException {
		Language language = db.findLanguageById(1);
		assertNotNull(language);
		assertEquals("English", language.getName());
	}

	@Test
	void test_findLanguageById_with_invalid_id_returns_null() throws SQLException {
		Language language = db.findLanguageById(-42);
		assertNull(language);
	}
}
