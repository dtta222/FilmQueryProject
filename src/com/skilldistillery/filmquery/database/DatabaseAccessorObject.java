package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.*;

// DatabaseAccessorObject class implementing the DatabaseAccessor interface
public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private final static String USER = "student";
	private final static String PWD = "student";

	// Constructor to register the JDBC driver
	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Method to find a film by its ID
	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		Connection conn = DriverManager.getConnection(URL, USER, PWD);

		String sql = "SELECT * FROM film WHERE id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet filmResult = stmt.executeQuery();

		if (filmResult.next()) {
			// Retrieve film information from the result set
			String title = filmResult.getString("title");
			int releaseYear = filmResult.getInt("release_year");
			String rating = filmResult.getString("rating");
			String description = filmResult.getString("description");

			// Retrieve language information using findLanguageById method
			int languageId = filmResult.getInt("language_id");
			Language language = findLanguageById(languageId);

			// Retrieve actors information using findActorsByFilmId method
			List<Actor> actors = findActorsByFilmId(filmId);

			String name = language.getName();

			// Create a Film object
			film = new Film(title, releaseYear, rating, description, name, actors);
		}
		conn.close();
		return film;
	}

	// Method to find an actor by their ID
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, USER, PWD);

		String sql = "SELECT * FROM actor WHERE id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			// Retrieve actor information from the result set
			String fn = actorResult.getString("first_name");
			String ln = actorResult.getString("last_name");

			// Create an Actor object
			actor = new Actor(fn, ln);
			actor.setId(actorResult.getInt("id"));
			/*actor.setFirstName(actorResult.getString("first_name"));
			actor.setFirstName(actorResult.getString("last_name"));*/
		}

		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	// Method to find films by actor ID
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT film.* "
					+ "FROM film JOIN film_actor ON film.id = film_actor.film_id "
					+ "WHERE film_actor.actor_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Retrieve film information from the result set
				int filmId = rs.getInt("id");
				String title = rs.getString("title");

				// Create a Film object and add it to the list
				Film film = new Film(filmId, title);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	// Method to find actors by film ID
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT actor.*\n" + "FROM actor\n" 
					+ "JOIN film_actor ON actor.id = film_actor.actor_id\n"
					+ "WHERE film_actor.film_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Retrieve actor information from the result set
				int actorId = rs.getInt("id");
				String fn = rs.getString("first_name");
				String ln = rs.getString("last_name");

				// Create an Actor object and add it to the list
				Actor actor = new Actor(actorId, fn, ln);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	// Method to find films by keyword
	@Override
	public List<Film> findFilmsByKeyWord(String word) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT *\n" + "FROM film\n" + "WHERE title LIKE ? OR description LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + word + "%");
			stmt.setString(2, "%" + word + "%");

			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				// Retrieve film information from the result set
				int filmId = filmResult.getInt("id");
				String title = filmResult.getString("title");
				int releaseYear = filmResult.getInt("release_year");
				String rating = filmResult.getString("rating");
				String description = filmResult.getString("description");
				int languageId = filmResult.getInt("language_id");

				// Retrieve language information using findLanguageById method
				Language language = findLanguageById(languageId);
				String name = language.getName();

				// Retrieve actors information using findActorsByFilmId method
				List<Actor> actors = findActorsByFilmId(filmId);

				// Create a Film object and add it to the list
				Film film = new Film(title, releaseYear, rating, description, name, actors);

				films.add(film);
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	// Method to find a language by its ID
	@Override
	public Language findLanguageById(int languageId) throws SQLException {
		Language language = null;

		Connection conn = DriverManager.getConnection(URL, USER, PWD);

		String sql = "SELECT * FROM language WHERE id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, languageId);

		ResultSet languageResult = stmt.executeQuery();

		if (languageResult.next()) {
			// Retrieve language information from the result set
			int id = languageResult.getInt("id");
			String name = languageResult.getString("name");

			// Create a Language object
			language = new Language(id, name);
		}
		languageResult.close();
		stmt.close();
		conn.close();
		return language;
	}
}
