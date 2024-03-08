package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a book has name, author and category
public class Book implements Writable {
    private String name;
    private String author;
    private String category;


    //Constructs the book(name, author, category)
    public Book(String name, String author, String category) {
        this.name = name;
        this.author = author;
        this.category = category;
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

    //EFFECTS: return the string type of the book description
    public String toString() {
        return "Title: " + this.name + ", Author: " + this.author + ", Category: " + this.category;
    }

    // EFFECTS: returns this book as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("author", this.author);
        json.put("category", this.category);
        //json.put("available", this.available);
        return json;
    }

}
