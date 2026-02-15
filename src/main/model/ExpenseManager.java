package model;

import java.util.List;
import java.util.ArrayList;

// Manages a collection of expenses and provides operations
// for adding expenses and generating summaries
public class ExpenseManager {

    private List<Expense> expenses;

    // EFFECTS: constructs an empty expense manager
    public ExpenseManager() {
        // stub
    }

    // REQUIRES: expense > 0
    // MODIFIES: this
    // EFFECTS: adds the given expense to the list of expenses
    public void addExpense(Expense expense) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: remove the given expense from the list of expenses
    public void removeExpense(Expense expense) {
        // stub
    }

    // REQUIRES: 1 <= month && month <= 12
    // EFFECTS: returns a MonthlySummary for the given month and year
    public MonthlySummary getMonthlySummary(int month, int year) {
        return null; // stub
    }

    // EFFECTS: returns a CategorySummary for the given category
    public CategorySummary getCategorySummary(Category category) {
        return null; // stub
    }

}
