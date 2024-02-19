package model;

import java.util.ArrayList;
import java.util.List;

//Library class manages the collection of books
public class Library {
    private List<Book> bookList;

    public Library() {
        bookList = new ArrayList<>();
    }

    //EFFECTS: Adds a new book to library's collection
    //MODIFIES: this
    public void addBook(Book book) {
        bookList.add(book);
    }

    //EFFECTS: remove a book from the library's collection
    public boolean removeBook(String name) {
        for (Book book : bookList) {
            if (book.getName().equals(name)) {
                bookList.remove(book);
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Search book(s) from the library's collection with specific category
    public String bookCategory(String category) {
        String bookCategory = "";
        for (Book book : bookList) {
            if (book.getCategory().equals(category)) {
                bookCategory += book.toString() + "\n";
            }
        }
        if (bookCategory.equals("")) {
            bookCategory += "Unfortunately, no such category book is found.";
        }
        return bookCategory;
    }

    //EFFECTS: remove a book from the library's collection
    public String bookAuthor(String author) {
        String bookAuthor = "";
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                bookAuthor += book.toString() + "\n";
            }
        }
        if (bookAuthor.equals("")) {
            bookAuthor += "Unfortunately, we don't have any books written by this author.";
        }
        return bookAuthor;
    }

    //EFFECTS: View the list of all books currently available in the library
//    public String viewAvailableBooks() {
//        String bookAvailable = "";
//        for (Book book : bookList) {
//            if (book.isAvailable()) {
//                bookAvailable += book.toString() + "\n";
//            } else {
//                bookAvailable += "Unfortunately, no book is available right now.";
//            }
//        }
//        return bookAvailable;
//    }

    //EFFECTS: View the total number of books in the library's collection
    public int getTotalBookNum() {
        return bookList.size();
    }

    public String getAllBook() {
        String bookName = "";
        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                bookName += book.toString() + "\n";
            }
        } else {
            bookName += "No book been added yet";
        }
        return bookName;
    }

    public List<Book> getBook() {
        return bookList;
    }
}

        //EFFECTS: Get the number of books which have been borrowed
//    public int getBorrowedBook() {
//        int borrowedCount = 0;
//        for (Book book : bookList) {
//            if (!book.isAvailable()) {
//                borrowedCount++;
//            }
//        }
//        return borrowedCount;
//    }
//}

