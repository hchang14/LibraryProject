package persistence;

import model.Event;
import model.EventLog;
import model.Library;
import model.Book;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads library from JSON data stored in file
//cite the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Library read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded previous book(s)."));
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        Library lb = new Library();
        addBooks(lb, jsonObject);
        return lb;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addBooks(Library lb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addBook(lb, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addBook(Library lb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        String category = jsonObject.getString("category");
        Book book = new Book(name, author, category);
        lb.addBook(book);
    }
}
