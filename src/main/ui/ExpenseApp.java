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
}
