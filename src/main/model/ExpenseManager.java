package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Manages a collection of expenses and provides operations
// for adding expenses and generating summaries
public class ExpenseManager implements Writable {

    private String name;
    private List<Expense> expenses;

    // EFFECTS: constructs an empty expense manager
    public ExpenseManager(String name) {
        this.name = name;
        expenses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // REQUIRES: expense > 0
    // MODIFIES: this
    // EFFECTS: adds the given expense to the list of expenses
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // MODIFIES: this
    // EFFECTS: remove the given expense from the list of expenses
    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }

    // MODIFIES: this
    // EFFECTS: if expense is in the given category, remove it from the list of expenses
    public void removeExpenseFromCategory(Expense expense, Category category) {
        if (expense.getCategory() == category) {
            expenses.remove(expense);
        }
    }

    // EFFECTS: returns list of expenses
    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }

    // EFFECTS: returns list of expenses in the given category
    public List<Expense> getExpensesByCategory(Category category) {
        List<Expense> filteredExpenses = new ArrayList<>();

        for (Expense e : expenses) {
            if (e.getCategory() == category) {
                filteredExpenses.add(e);
            }
        }
        return filteredExpenses;
    }

    // REQUIRES: 1 <= month && month <= 12
    // EFFECTS: returns a MonthlySummary for the given month and year
    public MonthlySummary getMonthlySummary(int month, int year) {
        MonthlySummary summary = new MonthlySummary(month, year);

        for (Expense expense : expenses) {
            if (expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
                summary.addExpense(expense.getCategory(), expense.getAmount());
            }
        }
        return summary;
    }

    // EFFECTS: returns a CategorySummary for the given category
    public CategorySummary getCategorySummary(Category category) {
        CategorySummary summary = new CategorySummary(category);

        for (Expense expense : expenses) {
            if (expense.getCategory() == category) {
                summary.addExpense(expense.getAmount());
            }
        }
        return summary;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("expenses", expensesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : expenses) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
