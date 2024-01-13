package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
		for (Actor member : film.getCast()) {
			System.out.println(member);
		}
//
//		Actor actor = db.findActorById(1);
//		System.out.println(actor);

//		List<Actor> cast = db.findActorsByFilmId(1);
//		for (Actor member : cast) {
//			System.out.println(member);
//		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		int option = 0;
		while (true) {
			try {
				printMainMenu();
				System.out.println("How would you like to search?: ");
				option = input.nextInt();
				switch (option) {
//			case 1 is for looking up a film by it's ID
				case 1:
					filmById(input);
					break;
//			case 2 is for looking up a film by a keyword
				case 2:
					filmByKeyword(input);
					break;
				case 3:
					System.out.println("Thank you for using the film lookup menu");
					System.out.println("Goodbye!");
					return;
				default:
					System.out.println("Please choose a valid menu option");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Not a valid input");
				clearBuffer(input);
			}
		}

	}

	public void filmByKeyword(Scanner input) {
		System.out.println("Please provide a keyword for lookup: ");
		clearBuffer(input);
		String keyword = input.nextLine();
		List<Film> films = db.findFilmsByKeyword(keyword);
		if (films.isEmpty() == true) {
			System.out.println("No films found that match your keyword");
		}
		else {
			for (Film film : films) {
				System.out.println("Title: " + film.getTitle());
				System.out.println("Year Released: " + film.getReleaseYear());
				System.out.println("Film Rating: " + film.getRating());
				System.out.println("Synopsis: " + film.getDescription());
				System.out.println("Language: " + film.getLanguage());
				System.out.println("--- Cast Members --- ");
				for (Actor member : film.getCast()) {
					System.out.println(member.getFirstName() + " " + member.getLastName());
				}
				System.out.println();
			}
			System.out.println("query returnted: " + films.size() + " films");
		}
	}

	public void filmById(Scanner input) {
		System.out.println("Please provide a film ID for lookup: ");
		clearBuffer(input);
		int filmId = input.nextInt();
		Film film = db.findFilmById(filmId);
		if (film == null) {
			System.out.println("No film found with that ID");
		}
		else {
			System.out.println("Title: " + film.getTitle());
			System.out.println("Year Released: " + film.getReleaseYear());
			System.out.println("Film Rating: " + film.getRating());
			System.out.println("Synopsis: " + film.getDescription());
			System.out.println("Language: " + film.getLanguage());
			System.out.println("--- Cast Members ---");
			for (Actor member : film.getCast()) {
				System.out.println(member.getFirstName() + " " + member.getLastName());
			}
			System.out.println();
		}
	}

	public void printMainMenu() {
		System.out.println("------------------------------------");
		System.out.println("----- 1: Look up a film by ID ------");
		System.out.println("--- 2: Look up a film by keyword ---");
		System.out.println("------------- 3: Exit --------------");
	}

	public void clearBuffer(Scanner input) {
		input.nextLine();
	}

}
