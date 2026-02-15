package model;

import java.util.Map;
import java.util.HashMap;

// Represents a summary of all spending for a given month and year, totalSpent, and categoryTotals
public class MonthlySummary {

    private int month;
    private int year;
    private double totalSpent;
    private Map<Category, Double> categoryTotals;

    // EFFECTS: constructs a summary for the given month and year wiht zero spending and empty category totals
    public MonthlySummary(int month, int year) {
        this.month = month;
        this.year = year;
        this.totalSpent = 0;
        this.categoryTotals = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given amount to the total spent and updates the total for given category
    public void addExpense(Category category, double amount) {
        totalSpent += amount;

        if (categoryTotals.containsKey(category)) {
            double currentCategory = categoryTotals.get(category);
            categoryTotals.put(category, currentCategory + amount);
        } else {
            categoryTotals.put(category, amount);
        }
    }

    // EFFECTS: returns the total amount spent in this month
    public double getTotalSpent() {
        return totalSpent;
    }

    // EFFECTS: returns the map of category totals for this month
    public Map<Category, Double> getCategoryTotals() {
        return categoryTotals;
    }

    // EFFECTS: returns the month this summary represents
    public int getMonth() {
        return month;
    }

    // EFFECTS: returns the year this summary represents
    public int getYear() {
        return year;
    }
}
