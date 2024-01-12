package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
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
		
		while(true) {
			printMainMenu();
			System.out.println("How would you like to search?: ");
			int option = input.nextInt();
			switch (option) {
//			case 1 is for looking up a film by it's ID
			case 1:
				filmById(input);
				break;
//			case 2 is for looking up a film by a keyword
			case 2:
				System.out.println("Film by Keyword!");
//				filmByKeyword();
				break;
			case 3:
				System.out.println("Thank you for using the film lookup menu");
				System.out.println("Goodbye!");
				return;
			default:
				System.out.println("Please choose a valid menu option");
			}
		}
		
	}
	
	public void filmById(Scanner input) {
		System.out.println("Please provide a film ID for lookup: ");
		int filmId = input.nextInt();
		Film film = db.findFilmById(filmId);
		System.out.println("Title: " + film.getTitle()); 
		System.out.println("Year Released: " + film.getReleaseYear()); 
		System.out.println("Film Rating" + film.getRating()); 
		System.out.println("Synopsis: " + film.getDescription());
		for (Actor member : film.getCast()) {
			System.out.println(member);
		}
		System.out.println();
	}
	
	public void printMainMenu() {
		System.out.println("------------------------------------");
		System.out.println("----- 1: Look up a film by ID ------");
		System.out.println("--- 2: Look up a film by keyword ---");
		System.out.println("------------- 3: Exit --------------");
	}

}
