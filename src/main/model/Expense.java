package model;

import java.time.LocalDate;

// A class representing a single expense of the item, amount, date, and category
public class Expense {

    private String item;
    private LocalDate date;
    private double amount;
    private Category category;
    
    // EFFECTS: constructs an Expense with given item, amount, date, and category
    public Expense(String item, LocalDate date, double amount, Category category) {
        this.item = item;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    // REQUIRES: item is non-empty
    // EFFECTS: returns the item name of this expense
    public String getItem() {
        return item;
    }

    // REQUIRES: amount > 0
    // EFFECTS: returns the amount of this expense
    public double getAmount() {
        return amount;
    }

    // REQUIRES: date is not null
    // EFFECTS: returns the date of this expense
    public LocalDate getDate() {
        return date;
    }

    // REQUIRES: category is not null
    // EFFECTS: returns the category of this expense
    public Category getCategory() {
        return category;
    }

    // This method tells Java how to turn the object into readable text
    @Override
    public String toString() {
        return item + " $" + amount;
    }
}
