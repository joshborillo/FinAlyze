package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.util.Locale.Category;

/*
Represents a panel that displays all tracked expenses in a JTable.
This class is responsible for for the visual representation of the expense list,
providing a scrollable view and allowing the user to sort data by clicking on the column headers.
It acts as the bridge between  the ExpenseList model and the JTable view.
 */
public class ExpenseListPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public ExpenseListPanel() {
        setLayout(new BorderLayout());

        // columns
        String[] columnNames = {"Item", "Expense ($)", "Date", "Category"};

        // inititialze model and table
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // enable "click header to sort" function
        table.setAutoCreateRowSorter(true);

        // put table in a ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // add expense to the GUI
    public void addExpenseToTable(String item, double amount, Category category, LocalDate date) {
        Object[] row = {item, amount, category, date};
        model.addRow(row);
    }
}
