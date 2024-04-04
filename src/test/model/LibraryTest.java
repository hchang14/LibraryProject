package model;

import model.Book;
import model.Library;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("Happy Hour", "Jennifer", "Humor");
        book2 = new Book("Sad Time in Paris", "Jennifer", "Thriller");
        book3 = new Book("Golden feet", "Hao", "Thriller");
    }

    @Test
    public void testAddBook() {
        library.addBook(book1);
        assertEquals(book1, library.getBook().get(0));
        library.addBook(book2);
        assertEquals(book2, library.getBook().get(1));
        library.addBook(book3);
        assertEquals(book3, library.getBook().get(2));
    }

    @Test
    public void testRemoveBookSuccessfully() {
        library.addBook(book1);
        library.addBook(book2);
        assertTrue(library.removeBook("Happy Hour"));
        assertTrue(library.removeBook("Sad Time in Paris"));
    }

    @Test
    public void testRemoveBookUnSuccessfully() {
        library.addBook(book1);
        library.addBook(book2);
        assertFalse(library.removeBook("Chill hour"));
        assertFalse(library.removeBook("Sad Time in China"));
        assertFalse(library.removeBook("Golden feet"));
    }

    @Test
    public void testRemoveNonExistentBook() {
        assertFalse(library.removeBook("Non-Existent Book"));
    }

    @Test
    public void testChangeListenerTriggeredOnRemove() {
        final boolean[] listenerCalled = {false};
        Runnable changeListener = () -> listenerCalled[0] = true;
        library.setChangeListener(changeListener);
        library.addBook(book1);

        library.removeBook(book1.getName());
        assertTrue(listenerCalled[0]);
    }


    @Test
    public void testBookCategorySuccessfully() {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        assertEquals("Title: Happy Hour, Author: Jennifer, Category: Humor\n", library.bookCategory("Humor"));
        assertTrue(library.bookCategory("Thriller").contains("Title: Sad Time in Paris, Author: Jennifer, Category: Thriller\n" + "Title: Golden feet, Author: Hao, Category: Thriller"));
    }

    @Test
    public void testBookCategoryUnSuccessfully() {
        assertEquals("Unfortunately, no such category book is found.", library.bookCategory("Humor"));
        library.addBook(book1);
        assertEquals("Unfortunately, no such category book is found.", library.bookCategory("Thriller"));
    }

    @Test
    public void testBookAuthorSuccessfully() {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        assertTrue(library.bookAuthor("Jennifer").contains("Title: Happy Hour, Author: Jennifer, Category: Humor\n" + "Title: Sad Time in Paris, Author: Jennifer, Category: Thriller"));
        assertEquals("Title: Golden feet, Author: Hao, Category: Thriller\n", library.bookAuthor("Hao"));
    }

    @Test
    public void testBookAuthorUnSuccessfully() {
        assertEquals("Unfortunately, we don't have any books written by this author.", library.bookAuthor(""));
        library.addBook(book1);
        assertEquals("Unfortunately, we don't have any books written by this author.", library.bookAuthor("Hao"));
    }

    @Test
    public void testGetTotalBookNum() {
        library.addBook(book1);
        assertEquals(1, library.getTotalBookNum());
        library.addBook(book2);
        assertEquals(2, library.getTotalBookNum());
        library.addBook(book3);
        assertEquals(3, library.getTotalBookNum());
    }

    @Test
    public void testGetAllBookSuccessfully() {
        library.addBook(book1);
        assertTrue(library.getAllBook().contains("Title: Happy Hour, Author: Jennifer, Category: Humor"));
        library.addBook(book2);
        assertTrue(library.getAllBook().contains("Title: Happy Hour, Author: Jennifer, Category: Humor\n" + "Title: Sad Time in Paris, Author: Jennifer, Category: Thriller"));
    }

    @Test
    public void testGetAllBookUnSuccessfully() {
        assertEquals("No book been added yet", library.getAllBook());
    }

    @Test
    public void testGetBooksCategoryCountMultipleBooksSameCategory() {
        // Assuming book1 and book2 are of the same category "Thriller"
        library.addBook(book1);
        library.addBook(book2);
        Map<String, Double> categoryCount = library.getBooksCategoryCount();
        assertEquals(Double.valueOf(2), categoryCount.get("Thriller"));
    }

    @Test
    public void testGetBooksCategoryCountMultipleCategories() {
        // Assuming book1, book2, and book3 are all of different categories
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        Map<String, Double> categoryCount = library.getBooksCategoryCount();
        assertEquals(Double.valueOf(1), categoryCount.get(book1.getCategory()));
        assertEquals(Double.valueOf(1), categoryCount.get(book2.getCategory()));
        assertEquals(Double.valueOf(1), categoryCount.get(book3.getCategory()));
    }

    @Test
    public void testIsBookExist() {
        library.addBook(book1);
        Book bookWithSameDetails = new Book(book1.getName(), book1.getAuthor(), book1.getCategory());
        assertTrue(library.isBookExist(bookWithSameDetails));
        assertFalse(library.isBookExist(new Book("Non-Existent", "Author", "Category")));
    }

    @Test
    public void testBookDoesNotExist() {
        assertFalse(library.isBookExist(book1));
    }

    @Test
    public void testToJsonEmptyLibrary() {
        JSONObject json = library.toJson();
        assertTrue(json.getJSONArray("books").isEmpty());
    }

    @Test
    public void testToJsonWithBooks() {
        library.addBook(book1);
        JSONObject json = library.toJson();
        assertEquals(1, json.getJSONArray("books").length());
        // Further assertions can check the content of the JSON array
    }

    @Test
    public void testChangeListenerIsCalled() {
        final boolean[] listenerCalled = {false}; // Simple flag to track listener call
        library.setChangeListener(() -> listenerCalled[0] = true);

        library.addBook(book1); // This should trigger the listener
        assertTrue(listenerCalled[0]);
    }


}