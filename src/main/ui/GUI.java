package ui;

import model.Expense;
import model.Category;
import model.ExpenseManager;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUI extends JFrame {
    private ExpenseManager expenses;
    private ExpenseListPanel tablePanel;
    private JButton addButton;

    /*
    Main graphical user interface for the app. This class represents the main window (JFrame), 
    manages user interactions through buttons and menus, and handles persistent data storage (saving/loading). 
    It organizes the layout by nesting sub-panels like the ExpenseListPanel, and handles top-level action events.
     */
    public GUI() {
        super("FinAlyze");
        expenses = new ExpenseManager("My Expense Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        
        setJMenuBar(createMenuBar());

        // initialize custom table panel
        tablePanel = new ExpenseListPanel();
        add(tablePanel, BorderLayout.CENTER);

        addButton = new JButton("Add New Expense(s)");
        addButton.addActionListener(e -> handleAddMultipleExpenses());
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File
        JMenu fileMenu = new JMenu("File");
        JMenu analyzeMenu = new JMenu("Analyze");

        JMenuItem saveItem = new JMenuItem("Save Data");
        JMenuItem loadItem = new JMenuItem("Load Data");
        JMenuItem categorySummary = new JMenuItem("Category Summary");
        JMenuItem monthlyCategorySummary = new JMenuItem("Monthly Category Summary");

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        analyzeMenu.add(categorySummary);
        analyzeMenu.add(monthlyCategorySummary);

        menuBar.add(fileMenu);
        menuBar.add(analyzeMenu);
        
        return menuBar;
    }

    private void handleAddMultipleExpenses() {
        AddExpenseDialog dialog = new AddExpenseDialog(this);
        Point mainLocation = this.getLocation();

        dialog.setLocation(mainLocation.x + 40, mainLocation.y + 50);

        List<Expense> addedExpenses = dialog.getExpenses();

        dialog.setVisible(true);
        
        if (!addedExpenses.isEmpty()) {
            for (Expense e : addedExpenses) {
                expenses.addExpense(e); 
                
                // update the table
                tablePanel.addExpenseToTable(e.getItem(), e.getDate(), e.getAmount(), e.getCategory());
            }
        }
    }
}
