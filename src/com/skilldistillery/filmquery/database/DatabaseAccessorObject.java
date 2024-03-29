package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PWD = "student";
	
	public String convertLanguage(int languageId) {
		String lang = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT name FROM language WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, languageId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				lang = filmResult.getString("name");
			}
			
			
			filmResult.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			System.out.println("In convertLanguage");
			e.printStackTrace();
		}
		return lang;
	}
	
	@Override
	public Film findFilmByKeyword(String keyword) {
		Film film = null;
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT * FROM film WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
//				Not sure if there is a null risk here?
				List<Actor> cast = findActorsByFilmId(film.getId());
				film.setCast(cast);
			}
			
			
			filmResult.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			System.out.println("In findFilmByKeyword");
			e.printStackTrace();
		}
		
		
		
		return film;
	}
	
	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		
		List<Film> setOfFilms = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT * FROM film JOIN language lang ON film.language_id = lang.id WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				Film film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
//				Not sure if there is a null risk here?
				List<Actor> cast = findActorsByFilmId(film.getId());
				film.setCast(cast);
//				String language = convertLanguage(film.getLanguageId());
				film.setLanguage(filmResult.getString("lang.name"));
				setOfFilms.add(film);
			}
			
			
			filmResult.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			System.out.println("In findFilmByKeyword");
			e.printStackTrace();
		}
		
		
		
		return setOfFilms;
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

//			String sql = "SELECT * FROM film WHERE id = ?";
			String sql = "SELECT * FROM film JOIN language lang ON film.language_id = lang.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(filmResult.getInt("film.id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				List<Actor> cast = findActorsByFilmId(filmId);
				film.setCast(cast);
//				String language = convertLanguage(film.getLanguageId());
				film.setLanguage(filmResult.getString("lang.name"));
			}
			
			
			filmResult.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			System.out.println("In findFilmById");
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		// ...
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
			}
			actorResult.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			System.out.println("In findActorById");
			e.printStackTrace();
		}
		// ...
		return actor;
	}
	
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<>();
		//...
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT ac.id, ac.first_name, ac.last_name FROM actor ac JOIN film_actor fa\n"
					+ "ON ac.id = fa.actor_id WHERE fa.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				Actor actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				actorList.add(actor);
			}
			actorResult.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			System.out.println("In findActorsByFilmID");
			e.printStackTrace();
		}

		
		return actorList;
	}

}
