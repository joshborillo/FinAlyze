package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Builds a list of expenses based on user input
public class ExpenseApp {
    private static final String JSON_STORE = "./data/manager.json";    
    private Scanner input;
    private ExpenseManager manager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ExpenseApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        manager = new ExpenseManager("User's Expense List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Starts the app, displays the main menu, and processes user input
    @SuppressWarnings("methodLength")
    public void run() {
        System.out.println("Welcome to FinAlyze!");

        boolean running = true;

        while (running) {
            printMenu();

            String command = input.nextLine().trim().toLowerCase();

            switch (command) {
                case "1":
                    doAddExpense();
                    break;
                case "2":
                    doRemoveExpense();
                    break;
                case "3":
                    doViewMonthlySummary();
                    break;
                case "4":
                    doViewCategorySummary();
                    break;
                case "s":
                    saveManager();
                    break;
                case "l":
                    loadManager();
                    break;
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection");
            }

        }
        System.out.println("Thanks for using FinAlyze!");
    }

    // EFFECTS: Diplays the list of available commands (add, remove, view summary) to console
    private void printMenu() {
        System.out.print("\nSelect an option:");
        System.out.print("\n1: Add an expense");
        System.out.print("\n2: Remove an expense");
        System.out.print("\n3: View monthly summary");
        System.out.print("\n4: View category summary");
        System.out.print("\ns: Save manager to file");
        System.out.print("\nl: Load manager from file");
        System.out.print("\nq: Quit");
        System.out.print("\n");
        System.out.print("\n>");
    }

    // MODIFIES: this, manager
    // EFFECTS: Prompts user for item details, constructs a new Expense then adds it to the manager
    private void doAddExpense() {
        System.out.println("Enter item name: ");
        String item = input.nextLine();

        System.out.println("Enter amount: ");
        double amount = Double.parseDouble(input.nextLine());

        System.out.println("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(input.nextLine());

        System.out.println("Enter category (FOOD, TRAVEL, SCHOOL, etc.): ");
        Category category = Category.valueOf(input.nextLine().toUpperCase());

        Expense expense = new Expense(item, date, amount, category);
        manager.addExpense(expense);

        System.out.println("Expense added!");
    }

    // MODIFIES: this, manager
    // EFFECTS: Displays a numbered list of all expenses; prompts user for an index and removes the correspondig expense
    //          If list is empty, go back
    private void doRemoveExpense() {
        // expense list is empty
        if (manager.getExpenses().isEmpty()) {
            System.out.println("No expenses to remove.");
            return;
        }

        System.out.println("Select an expense to remove or '0' to return:");
        for (int i = 0; i < manager.getExpenses().size(); i++) {
            Expense expense = manager.getExpenses().get(i);
            System.out.println((i + 1) + ": " + expense.getItem() + "- $" + expense.getAmount());
        }

        System.out.print("> ");
        // parse input
        int selection = Integer.parseInt(input.nextLine());

        // handle "back"
        if (selection == 0) {
            System.out.println("Removal cancelled");
            return;
        }

        // Adjust for 0-based indexing
        int index = selection - 1;

        // perform removal if valid
        if (index >= 0 && index < manager.getExpenses().size()) {
            Expense removed = manager.getExpenses().get(index);
            manager.removeExpense(removed);
            System.out.println(removed.getItem() + " removed!");
        } else {
            System.out.println("Invalid selection. Returning to menu");
        }
    }

    // EFFECTS: prompts the user for a month and year,
    // then displays the total spending and category breakdon for the period
    private void doViewMonthlySummary() {
        System.out.print("Enter month (1-12): ");
        int month = Integer.parseInt(input.nextLine());

        System.out.print("Enter year: ");
        int year = Integer.parseInt(input.nextLine());

        MonthlySummary summary = manager.getMonthlySummary(month, year);

        String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        System.out.println("\nSummary for " + monthName + " " + year);
        System.out.println("Total spent: $" + summary.getTotalSpent());

        System.out.println("By category: ");
        for (Category cat : summary.getCategoryTotals().keySet()) {
            System.out.println(cat + " " +  summary.getCategoryTotals().get(cat));
        }
    }

    // EFFECTS: prompts user for a category name, and displays the total spent and number of items
    //          user is then given the option to remove an item from the category, or return
    private void doViewCategorySummary() {
        System.out.print("Enter category: ");
        Category category = Category.valueOf(input.nextLine().toUpperCase());

        CategorySummary cs = manager.getCategorySummary(category);

        System.out.println("\nCategory: " + category);
        System.out.println("Total spent: $" + cs.getTotalSpent());
        System.out.println("Total number of items: " + cs.getNumExpenses());
        
        List<Expense> filteredItems = manager.getExpensesByCategory(category);

        if (filteredItems.isEmpty()) {
            System.out.println("No expenses in this category.");
            return;
        }

        System.out.println("List of items:");
        for (int i = 0; i < filteredItems.size(); i++) {
            System.out.println((i + 1) + ": " + filteredItems.get(i));
        }
        
        // Give the user the option to remove an item in given category
        System.out.println("\nEnter the number of an item to remove (or '0' to go back): ");
        int choice = Integer.parseInt(input.nextLine());

        if (choice > 0 && choice <= filteredItems.size()) {
            Expense toRemove = filteredItems.get(choice - 1);

            manager.removeExpense(toRemove);
            System.out.println("Successfully removed: " + toRemove.getItem());
        }
    }
    
    // EFFECTS: saves the manager to file
    private void saveManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            System.out.println("Saved " + manager.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads manager from file
    private void loadManager() {
        try {
            manager = jsonReader.read();
            System.out.println("Loaded " + manager.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
