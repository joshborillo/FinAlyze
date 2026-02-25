package ui;

import model.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExpenseApp {
    
    private ExpenseManager manager;
    private Scanner input;

    public ExpenseApp() {
        manager = new ExpenseManager();
        input = new Scanner(System.in);
    }

    @SuppressWarnings("all")
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
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection");
            }

        }
        System.out.println("Thanks for using FinAlyze!");
    }

    private void printMenu() {
        System.out.print("\nSelect an option:");
        System.out.print("\n1: Add an expense");
        System.out.print("\n2: Remove an expense");
        System.out.print("\n3: View monthly summary");
        System.out.print("\n4: View category summary");
        System.out.print("\nq: Quit");
        System.out.print("\n");
        System.out.print("\n>");
    }

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

    private void doViewCategorySummary() {
        System.out.print("Enter category: ");
        Category category = Category.valueOf(input.nextLine().toUpperCase());

        CategorySummary cs = manager.getCategorySummary(category);

        System.out.println("\nCategory: " + category);
        System.out.println("Total spent: $" + cs.getTotalSpent());
        
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
}
