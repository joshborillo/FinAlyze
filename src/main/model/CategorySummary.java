package model;

// Represents a summary of spending for a single category, including totalSpent and numExpenses
public class CategorySummary {

    private Category category;
    private double totalSpent;
    private int numExpenses;

    // EFFECTS: constrcuts a summary for a given category with zero spending and zero number of expenses
    public CategorySummary(Category category) {
        this.category = category;
        this.totalSpent = 0;
        this.numExpenses = 0;

    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds a given amount to the totalSpent and increments numExpenses
    public void addExpense(double amount) {
        if (amount > 0) {
            totalSpent += amount;
            numExpenses++;
        }
    }

    // EFFECTS: returns the category this summary is for
    public Category getCategory() {
        return category;
    }

    // EFFECTS: returns the total amount spent in this category
    public double getTotalSpent() {
        return totalSpent;
    }

    // EFFECTS: returns the number of expenses in this category
    public int getNumExpenses() {
        return numExpenses;
    }
}
