package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction");
    }

    @Test
    public void testGetName() {
        assertEquals("The Great Gatsby", book.getName());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Fiction", book.getCategory());
    }

    @Test
    public void testToString() {
        assertEquals("Title: The Great Gatsby, Author: F. Scott Fitzgerald, Category: Fiction", book.toString());
    }

    @Test
    public void testBookConstructor() {
        assertEquals("Book Name", book.getName());
        assertEquals("Author Name", book.getAuthor());
        assertEquals("Category", book.getCategory());
    }

    @Test
    public void testToJson() {
        JSONObject json = book.toJson();
        assertEquals("Book Name", json.getString("name"));
        assertEquals("Author Name", json.getString("author"));
        assertEquals("Category", json.getString("category"));
    }
}

