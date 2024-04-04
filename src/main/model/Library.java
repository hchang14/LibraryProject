package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//EFFECTS: Library class manages the collection of books
public class Library implements Writable {
    private List<Book> bookList;
    private Runnable changeListener;

    //EFFECTS: Initialize an empty arrayList
    //MODIFIES: this
    public Library() {
        this.bookList = new ArrayList<>();
    }

    //REQUIRES: !bookList.contains() the book we are trying to add
    //EFFECTS: Adds a new book to library's collection
    //MODIFIES: this
    public void addBook(Book book) {
        bookList.add(book);
        notifyListeners();
    }

    //REQUIRES: bookList.contains() the book we are trying to remove
    //EFFECTS: remove a book from the library's collection
    //MODIFIES: this
    public boolean removeBook(String name) {
        boolean isRemoved = bookList.removeIf(book -> book.getName().equals(name));
        if (isRemoved) {
            if (changeListener != null) {
                changeListener.run();
            }
        }
        return isRemoved;
    }

    //REQUIRES: bookList.contains() the book category we are trying to search
    //EFFECTS: Search book(s) from the library's collection with specific category
    //MODIFIES: this
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

    //REQUIRES: bookList.contains() the author we are trying to search
    //EFFECTS: Search book(s) from the library's collection with specific author
    //MODIFIES: this
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


    //EFFECTS: View the total number of books in the library's collection
    public int getTotalBookNum() {
        return bookList.size();
    }

    //EFFECTS: return the String type of books in the library
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

    //EFFECTS: get the list of books in the library
    public List<Book> getBook() {
        return bookList;
    }

    //Check whether the book has existed already
    public boolean isBookExist(Book book) {
        for (Book existingBook : bookList) {
            if (existingBook.getName().equals(book.getName())
                    && existingBook.getAuthor().equals(book.getAuthor())
                    && existingBook.getCategory().equals(book.getCategory())) {
                return true;
            }
        }
        return false; // not exist
    }

    //EFFECTS: return the library as a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: returns things in this log collection as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book book : this.bookList) {
            jsonArray.put(book.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: After execution, this instance will have its `changeListener` set to the provided listener.
    // This listener will be called upon the calling of `notifyListeners()`.
    public void setChangeListener(Runnable listener) {
        this.changeListener = listener;
    }

    //EFFECTS: If `changeListener` has been set (i.e., it is not null), then it is executed.
     // If `changeListener` is null, this method has no effect.
    private void notifyListeners() {
        if (changeListener != null) {
            changeListener.run();
        }
    }
}

