package persistence;

import model.Library;
import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(Book book, String name, String author, String category) {
        assertEquals(name, book.getName());
        assertEquals(author, book.getAuthor());
        assertEquals(category, book.getCategory());
    }
}
