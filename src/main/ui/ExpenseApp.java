package ui;

import model.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseApp {
    
    private ExpenseManager manager;
    private Scanner input;

    public ExpenseApp() {
        manager = new ExpenseManager();
        input = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to your FinAlyze!");

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
        System.out.print("1: Add an expense");
        System.out.print("2: Remove an expense");
        System.out.print("3: View monthly summary");
        System.out.print("4: View category summary");
        System.out.print("q: Quit");
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
        // expenses list is empty
        if (manager.getExpenses().isEmpty()) {
            System.out.println("No expenses to remove.");
            return;
        }

        System.out.println("Select an expense to remove:");
        for (int i = 0; i < manager.getExpenses().size(); i++) {
            Expense expense = manager.getExpenses().get(i);
            System.out.println((i + 1) + ": " + expense.getItem() + "- $" + expense.getAmount());
        }

        System.out.print("> ");
        int index = Integer.parseInt(input.nextLine()) - 1;

        if (index >= 0 && index < manager.getExpenses().size()) {
            Expense removed = manager.getExpenses().get(index);
            manager.removeExpense(removed);
            System.out.println("Expense removed!");
        } else {
            System.out.println("Invalid selection");
        }
    }

    private void doViewMonthlySummary() {
        System.out.print("Enter month (1-12): ");
        int month = Integer.parseInt(input.nextLine());

        System.out.print("Enter year: ");
        int year = Integer.parseInt(input.nextLine());

        MonthlySummary summary = manager.getMonthlySummary(month, year);

        System.out.println("\nSummary for " + month + " " + year);
        System.out.println("Total spent: $" + summary.getTotalSpent());

        System.out.println("By category: ");
        for (Category cat : summary.getCategoryTotals().keySet()) {
            System.out.println(" " + cat + summary.getCategoryTotals().get(cat));
        }
    }


}
