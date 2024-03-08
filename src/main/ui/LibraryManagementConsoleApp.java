package ui;

import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

//Represents the App has library, scanner, book and running/not running status
public class LibraryManagementConsoleApp {
    private static final String JSON_STORE = "./data/LibraryFile.json";
    private Library library;
    private Scanner scanner;
    private boolean isRunning;
    private Book book;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: Initialize the library, scanner and App has is running.
    public LibraryManagementConsoleApp() {
        library = new Library();
        scanner = new Scanner(System.in);
        isRunning = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    //REQUIRES: App is running
    //MODIFIES: this
    //EFFECTS: Perform each of the actions specified in the App
    public void runApp() {
        System.out.println("1. Add a new book");
        System.out.println("2. View all books");
        System.out.println("3. Remove a book");
        System.out.println("4. View books of specific category");
        System.out.println("5. View books of specific author");
        System.out.println("6. Save library to the file");
        System.out.println("7. Load library to the file");
        System.out.println("8. Exit");
        console();

        while (isRunning) {
            System.out.println(" ");
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Remove a book");
            System.out.println("4. View books of specific category");
            System.out.println("5. View books of specific author");
            System.out.println("6. Save library to the file");
            System.out.println("7. Load library to the file");
//            System.out.println("7. Return a book");
            System.out.println("8. Exit");
            console();
        }
    }

    //REQUIRES: App is running
    //MODIFIES: this
    //EFFECTS: Reads user input from the console and executes the corresponding action.
    public void console() {
        try {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            execute(choice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 6.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
            e.printStackTrace();
        }
    }

    //REQUIRES: choice
    //MODIFIES: this
    //EFFECTS: input the action with specified choice
    public void execute(int choice) throws InputMismatchException {
        if (choice == 1) {
            inputCase1();
        } else if (choice == 2) {
            inputCase2();
        } else if (choice == 3) {
            inputCase3();
        } else if (choice == 4) {
            inputCase4();
        } else if (choice == 5) {
            inputCase5();
        } else if (choice == 6) {
            inputCase6();
        } else if (choice == 7) {
            inputCase7();
        } else if (choice == 8) {
            inputCase8();
        } else {
            defaultCase();
        }
    }

    // REQUIRES: App is running.
    // MODIFIES: this, library
    // EFFECTS: Reads user input for adding a new book to the library and adds the book to the library.
    public void inputCase1() {
        System.out.print("Enter title: ");
        String name = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        Book book = new Book(name, author, category);
        library.addBook(book);
        System.out.println("Book added successfully");
    }

    // REQUIRES: App is running.
    // MODIFIES: this, library
    // EFFECTS: Reads user input for viewing all books in the library and prints them.
    public void inputCase2() {
        System.out.println("In total, we have " + library.getTotalBookNum() + " books in the library.");
        System.out.println(library.getAllBook());

    }

    // REQUIRES: App is running.
    // MODIFIES: this, library
    // EFFECTS: Reads user input for removing a  book in the library and remove it if found.
    public void inputCase3() {
        System.out.print("Enter title of book to remove: ");
        String titleToRemove = scanner.nextLine();
        boolean check = library.removeBook(titleToRemove);
        if (check) {
            System.out.println("Book removed successfully");
        } else {
            System.out.println("Book is not found, removed unsuccessfully");
        }
    }

    // REQUIRES: App is running.
    // MODIFIES: this, library
    // EFFECTS: Reads user input for viewing books of specific category and prints them.
    public void inputCase4() {
        System.out.print("Enter category of book: ");
        String titleToSearch = scanner.nextLine();
        System.out.println("All Books of " + titleToSearch);
        System.out.println(library.bookCategory(titleToSearch));
    }

    // REQUIRES: App is running.
    // MODIFIES: this, library
    // EFFECTS: Reads user input for viewing books of specific author and prints them.
    public void inputCase5() {
        System.out.print("Enter author of book: ");
        String authorToSearch = scanner.nextLine();
        System.out.println("All Books written by " + authorToSearch);
        System.out.println(library.bookAuthor(authorToSearch));
    }

    // REQUIRES: App is running.
    // MODIFIES: this, isRunning
    // EFFECTS: Sets isRunning to false, indicating the exit of the application.
    public void inputCase8() {
        isRunning = false;
        System.out.println("Exiting the Library Management System");
    }

    // REQUIRES: App is running.
    // EFFECTS: Prints a message indicating that the user input is invalid.
    public void defaultCase() {
        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
    }

    // EFFECTS: saves the library to file
    private void inputCase6() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Saved library to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads library from file
    private void inputCase7() {
        try {
            library = jsonReader.read();
            System.out.println("Loaded library from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

