package persistence;

import model.ExpenseManager;
import org.json.JSONObject;
import java.io.*;

/*
 CITATION: This class was adapted from the CPSC210's JsonSerializationDemo repo
 Source: https://github.com/stleary/JSON-java
*/

// Represents a writer that writes ExpenseManager from Java to JSON data
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of expense manager to file
    public void write(ExpenseManager em) {
        JSONObject json = em.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
