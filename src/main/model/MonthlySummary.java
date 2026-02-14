package model;

import java.util.Map;

// Represents a summary of all spending for a given month and year, totalSpent, and categoryTotals
public class MonthlySummary {

    private int month;
    private int year;
    private double totalSpent;
    private Map<Category, Double> categoryTotals;

    // EFFECTS: constructs a summary for the given month and year wiht zero spending and empty category totals
    public MonthlySummary(int month, int year) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds the given amount to the total spent and updates the total for given category
    public void addExpense(Category category, double amount) {
        // stub
    }

    // EFFECTS: returns the total amount spent in this month
    public double getTotalSpent() {
        return 0; // stub
    }

    // EFFECTS: returns the month this summary represents
    public int getMonth() {
        return 0; // stub
    }

    // EFFECTS: returns the year this summary represents
    public int getYear() {
        return 0; // sutb
    }
}
