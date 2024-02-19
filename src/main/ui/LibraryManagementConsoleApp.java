package ui;

import model.Book;
import model.Library;

import java.util.Scanner;

public class LibraryManagementConsoleApp {
    private Library library;
    private Scanner scanner;
    private boolean isRunning;
    private Book book;

    public LibraryManagementConsoleApp() {
        library = new Library();
        scanner = new Scanner(System.in);
        runApp();
    }

    public void runApp() {
        boolean isRunning = true;
        System.out.println("1. Add a new book");
        System.out.println("2. View all books");
        System.out.println("3. Remove a book");
        System.out.println("4. View books of specific category");
        System.out.println("5. View books of specific author");
//        System.out.println("5. View number of borrowed books");
//        System.out.println("6. Borrow a book");
//        System.out.println("7. Return a book");
        System.out.println("6. Exit");

        while (isRunning) {
            System.out.print("Enter your choice: ");

            console();
            System.out.println(" ");
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Remove a book");
            System.out.println("4. View books of specific category");
            System.out.println("5. View books of specific author");
//            System.out.println("5. View number of borrowed books");
//            System.out.println("6. Borrow a book");
//            System.out.println("7. Return a book");
            System.out.println("6. Exit");
        }
    }

    public void console() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        execute(choice);
    }

    public void execute(int choice) {
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
//        } else if (choice == 7) {
//            inputCase7();
//        } else if (choice == 8) {
//            inputCase7();
        } else {
            defaultCase();
        }
    }

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

    public void inputCase2() {
        System.out.println("In total, we have " + library.getTotalBookNum() + " books in the library.");
        System.out.println(library.getAllBook());

    }

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

//    public void inputCase4() {
//        System.out.println("Total Books: " + library.getTotalBookNum());
//    }

//    public void inputCase5() {
//        System.out.println("Borrowed Books: " + library.getBorrowedBook());
//    }
//
//    public void inputCase6() {
//        System.out.print("Enter title of book to borrow: ");
//        String titleToBorrow = scanner.nextLine();
//        boolean check = library.borrowBook(titleToBorrow);
//        if (check == true) {
//            System.out.println("Book borrowed successfully");
//        } else {
//            System.out.println("Book is not found, borrowed unsuccessfully");
//        }
//    }

    //    public void inputCase7() {
//        System.out.print("Enter title of book to borrow: ");
//        String titleToBorrow = scanner.nextLine();
//        boolean check = library.borrowBook(titleToBorrow);
//        if (check == true) {
//            System.out.println("Book returned successfully");
//        } else {
//            System.out.println("Book is not found, returned unsuccessfully");
//        }
//    }
    public void inputCase4() {
        System.out.print("Enter category of book: ");
        String titleToSearch = scanner.nextLine();
        System.out.println("All Books of " + titleToSearch);
        System.out.println(library.bookCategory(titleToSearch));
    }

    public void inputCase5() {
        System.out.print("Enter author of book: ");
        String authorToSearch = scanner.nextLine();
        System.out.println("All Books written by " + authorToSearch);
        System.out.println(library.bookAuthor(authorToSearch));
    }

    public void inputCase6() {
        isRunning = false;
        System.out.println("Exiting the Library Management System");
    }

    public void defaultCase() {
        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
    }
}

