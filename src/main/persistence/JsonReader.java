package persistence;

import model.Expense;
import model.Category;
import model.ExpenseManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.json.*;

/*
 CITATION: This class was adapted from the CPSC210's JsonSerializationDemo repo
 Source: https://github.com/stleary/JSON-java
*/

// Represents a reader that reads ExpenseManager from JSON data stored in file

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads manager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExpenseManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses manager from JSON object and returns it
    private ExpenseManager parseExpenseManager(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ExpenseManager em = new ExpenseManager(name);
        addExpenses(em, jsonObject);
        return em;
    }

    // MODIFIES: em
    // EFFECTS: parses expenses from JSON object and adds them to manager
    private void addExpenses(ExpenseManager em, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(em, nextExpense);
        }
    }

    // MODIFIES: em
    // EFFECTS: parses expense from JSON object and adds it to manager
    private void addExpense(ExpenseManager em, JSONObject jsonObject) {
        String item = jsonObject.getString("name");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        double amount = jsonObject.getDouble("amount");
        Category category = Category.valueOf(jsonObject.getString("category"));
        Expense expense = new Expense(item, date, amount, category);
        em.addExpense(expense);
    }

}
