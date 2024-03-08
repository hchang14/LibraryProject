package persistence;

import model.Library;
import model.Book;
import model.LibraryTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library lc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected, test passes
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json.json");
        try {
            Library lb = reader.read();
            List<Book> book = lb.getBook();
            assertEquals(0, book.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json.json");
        try {
            Library lb = reader.read();
            List<Book> book = lb.getBook();
            assertEquals(1, book.size());
            checkBook(book.get(0), "SAD", "Naonao", "Humor");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
