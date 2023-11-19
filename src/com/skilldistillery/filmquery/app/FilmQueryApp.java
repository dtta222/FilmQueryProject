package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.*;

// FilmQueryApp class representing the main application
public class FilmQueryApp {

    // Instantiate the DatabaseAccessorObject
    DatabaseAccessor db = new DatabaseAccessorObject();

    // Main method to start the application
    public static void main(String[] args) throws SQLException {
        // Create an instance of FilmQueryApp
        FilmQueryApp app = new FilmQueryApp();

        // Launch the user interface
        app.launch();
    }

    // Method to launch the user interface
    private void launch() throws SQLException {
        // Create a Scanner for user input
        Scanner input = new Scanner(System.in);

        // Start the user interface
        startUserInterface(input);

        // Close the Scanner
        input.close();
    }

    // Method to start the user interface
    private void startUserInterface(Scanner input) throws SQLException {
        int choice;

        do {
            // Display the menu
            System.out.println("Menu:");
            System.out.println("1. Look up a film by its id");
            System.out.println("2. Look up a film by a search keyword");
            System.out.println("3. Exit");

            // Prompt the user for their choice, handle InputMismatchException
            try {
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                input.nextLine(); // Clear the input buffer
                choice = -1; // Set an invalid choice to trigger the default case
            }

            // Process the user's choice
            switch (choice) {
                case 1:
                    lookUpFilmById(input);
                    break;
                case 2:
                    lookUpFilmByKeyword(input);
                    break;
                case 3:
                    System.out.println("\nExiting the application. Goodbye!");
                    break;
                default:
                    // Display an error message for an invalid choice
                    System.err.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 3);
    }

    // Method to look up a film by its ID
    private void lookUpFilmById(Scanner input) throws SQLException {
        int filmId = -1; // Initialize filmId to an invalid value

        // Prompt the user for the film ID until a valid integer is entered
        while (filmId == -1) {
            try {
                System.out.print("Enter the film ID: ");
                filmId = input.nextInt();
                input.nextLine(); // Consume the newline character
            } catch (java.util.InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid integer for the film ID.");
                input.nextLine(); // Clear the input buffer
            }
        }

        // Look up the film by ID
        Film film = db.findFilmById(filmId);
        if (film != null) {
            // Display the film information
            System.out.println("\nFilm Information:");
            System.out.println("----------------------------------------");
            System.out.println(film);
        } else {
            // Display an error message if the film is not found
            System.err.println("\nError: Film not found for ID #" + filmId + "\n");
        }
    }

    // Method to look up a film by a search keyword
    private void lookUpFilmByKeyword(Scanner input) {
        String keyword = null; // Initialize keyword to null

        // Prompt the user for the search keyword until a non-empty string is entered
        while (keyword == null || keyword.trim().isEmpty()) {
            try {
                System.out.print("Enter the search keyword: ");
                input.nextLine(); // Consume the newline character left by previous nextInt()
                keyword = input.nextLine();

                if (keyword.trim().isEmpty()) {
                    System.err.println("Error: Keyword cannot be empty. Please enter a valid keyword.");
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid string for the search keyword.");
                input.nextLine(); // Clear the input buffer
            }
        }

        // Look up films by keyword
        List<Film> films = db.findFilmsByKeyWord(keyword);
        if (films.isEmpty()) {
            // Display a message if no films are found
            System.out.println("\nNo films found for the keyword: " + keyword + "\n");
        } else {
            // Display films found for the keyword
            System.out.println("\nFilms found for the keyword '" + keyword + "':");
            System.out.println("----------------------------------------");
            for (Film film : films) {
                System.out.println(film);
            }
        }
    }
}
