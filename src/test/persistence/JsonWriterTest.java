package persistence;

import model.Library;
import model.Book;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Library lb = new Library();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected, test passes
        }
    }

    @Test
    void testWriterEmptyLogCollection() {
        try {
            Library lb = new Library();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogCollection.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogCollection.json");
            lb = reader.read();
            List<Book> books = lb.getBook();
            assertEquals(0, books.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            Library lb = new Library();
            lb.addBook(new Book("Mathematics","Tanojo","Romantic"));
            lb.addBook(new Book("Man in Black","Hao","Action"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            lb = reader.read();
            List<Book> books = lb.getBook();
            assertEquals(2, books.size());
            checkBook(books.get(0), "Mathematics","Tanojo","Romantic");
            checkBook(books.get(1), "Man in Black","Hao","Action");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
