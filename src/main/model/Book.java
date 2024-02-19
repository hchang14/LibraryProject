package model;

public class Book {
    private String name;
    private String author;
    private String category;
    private boolean available;

    //Book class represent a single book in the library
    public Book(String name, String author, String category) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.available = true; // Initial the book as available
    }


    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getCategory() {
        return this.category;
    }

//    public boolean isAvailable() {
//        return this.available;
//    }

    //EFFECTS: Marks the book as borrowed
    //MODIFIES: this
//    public void borrowBook() {
//        this.available = false;
//    }

    //EFFECTS: Marks the book as borrowed
//    //MODIFIES: this
//    public void returnBook() {
//        this.available = true;
//    }

    public String toString() {
        return "Title: " + this.name + ", Author: " + this.author + ", Category: " + this.category;
    }

}
